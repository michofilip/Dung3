package scripts

import scripts.Instruction.{EXECUTE, GOTO, LABEL, TEST}
import scripts.Statement.{Block, ChooseVariants, ChooseVariantsOtherwise, Execute, LoopBody, MultiWhenTherefore, MultiWhenThereforeOtherwise, WhenTherefore}

import scala.language.implicitConversions

object StatementCompiler {
    implicit private def inst2Vec(instruction: Instruction): Vector[Instruction] = Vector(instruction)
    
    def compile(statement: Statement): Vector[Instruction] = {
        val (instructions, _) = compile(statement, Vector.empty, 0)
        instructions
    }
    
    private def compile(statement: Statement, instructions: Vector[Instruction], label: Int): (Vector[Instruction], Int) = {
        statement match {
            case st: Execute => compileExecute(st, instructions, label)
            case st: Block => compileBlock(st, instructions, label)
            case st: MultiWhenTherefore => compileMultiWhenTherefore(st, instructions, label)
            case st: MultiWhenThereforeOtherwise => compileMultiWhenThereforeOtherwise(st, instructions, label)
            case st: LoopBody => compileLoopBody(st, instructions, label)
            case ChooseVariants(value, variants) => ???
            case ChooseVariantsOtherwise(value, variants, otherwise) => ???
        }
    }
    
    private def compileExecute(execute: Execute, instructions: Vector[Instruction], label: Int): (Vector[Instruction], Int) = {
        (instructions :+ EXECUTE(execute.events), label)
    }
    
    private def compileBlock(block: Block, instructions: Vector[Instruction], label: Int): (Vector[Instruction], Int) = {
        val (blockInstructions, afterBlockLabel) = block.statements.foldLeft((instructions, label)) {
            case ((instructions, labelId), statement) => compile(statement, instructions, labelId)
        }
        (instructions ++ blockInstructions, afterBlockLabel)
    }
    
    private def compileWhenThereforeSeq(whenThereforeSeq: Vector[WhenTherefore], otherwiseStatement: Statement,
                                        label: Int): (Vector[Instruction], Int) = {
        whenThereforeSeq match {
            case WhenTherefore(condition, thereforeStatement) +: tail =>
                val elseLabel = label
                val exitLabel = label + 1
                
                val (thenInstructions, afterThenLabel) = compile(thereforeStatement, Vector.empty, exitLabel + 1)
                val (elseInstructions, afterElseLabel) = compileWhenThereforeSeq(tail, otherwiseStatement, afterThenLabel + 1)
                
                val newInstructions = TEST(condition) ++
                        GOTO(elseLabel) ++
                        thenInstructions ++
                        GOTO(exitLabel) ++
                        LABEL(elseLabel) ++
                        elseInstructions ++
                        LABEL(exitLabel)
                
                (newInstructions, afterElseLabel)
            case _ => compile(otherwiseStatement, Vector.empty, label)
        }
    }
    
    private def compileMultiWhenThereforeOtherwise(multiWhenThereforeOtherwise: MultiWhenThereforeOtherwise,
                                                   instructions: Vector[Instruction], label: Int): (Vector[Instruction], Int) = {
        val (whenInstructions, afterWhenLabel) =
            compileWhenThereforeSeq(multiWhenThereforeOtherwise.whenThereforeSeq, multiWhenThereforeOtherwise.otherwiseStatement, label)
        
        (instructions ++ whenInstructions, afterWhenLabel)
    }
    
    private def compileMultiWhenTherefore(multiWhenTherefore: MultiWhenTherefore,
                                          instructions: Vector[Instruction], label: Int): (Vector[Instruction], Int) = {
        val (whenInstructions, afterWhenLabel) =
            compileWhenThereforeSeq(multiWhenTherefore.whenThereforeSeq, Block(Vector.empty), label)
        
        (instructions ++ whenInstructions, afterWhenLabel)
    }
    
    protected def compileLoopBody(loopBody: LoopBody, instructions: Vector[Instruction], label: Int): (Vector[Instruction], Int) = {
        val loopLabel = label
        val exitLabel = label + 1
        
        val (loopedInstructions, afterLoopLabelId) = compile(loopBody.body, Vector.empty, exitLabel + 1)
        
        val newInstructions = LABEL(loopLabel) ++
                TEST(loopBody.condition) ++
                GOTO(exitLabel) ++
                loopedInstructions ++
                GOTO(loopLabel) ++
                LABEL(exitLabel)
        
        (instructions ++ newInstructions, afterLoopLabelId)
    }
}

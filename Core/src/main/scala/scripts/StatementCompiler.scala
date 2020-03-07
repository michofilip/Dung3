package scripts

import events.Event.Events
import scripts.Instruction.{EXECUTE, GOTO, LABEL, TEST}
import scripts.Statement.{Block, ChooseVariants, ChooseVariantsOtherwise, Execute, LoopBody, MultiWhenTherefore, MultiWhenThereforeOtherwise, VariantWhenTherefore, WhenTherefore}
import value.Value
import value.basic.BooleanValue

import scala.language.implicitConversions

object StatementCompiler {
    implicit private def inst2Vec(instruction: Instruction): Vector[Instruction] = Vector(instruction)
    
    def compile(statement: Statement): Vector[Instruction] = {
        val (instructions, _) = compile(statement, Vector.empty, 0)
        instructions
    }
    
    private def compile(statement: Statement, instructions: Vector[Instruction], label: Int): (Vector[Instruction], Int) = {
        statement match {
            case Execute(events) =>
                compileExecute(events, instructions, label)
            case Block(statements) =>
                compileBlock(statements, instructions, label)
            case MultiWhenTherefore(whenThereforeSeq) =>
                compileWhen(whenThereforeSeq, Block(Vector.empty), instructions, label)
            case MultiWhenThereforeOtherwise(whenThereforeSeq, otherwiseStatement) =>
                compileWhen(whenThereforeSeq, otherwiseStatement, instructions, label)
            case LoopBody(condition, body) =>
                compileLoop(condition, body, instructions, label)
            case ChooseVariants(value, variants) =>
                compileChoose(value, variants, Block(Vector.empty), instructions, label)
            case ChooseVariantsOtherwise(value, variants, otherwise) =>
                compileChoose(value, variants, otherwise, instructions, label)
        }
    }
    
    private def compileExecute(events: Events,
                               instructions: Vector[Instruction], label: Int): (Vector[Instruction], Int) = {
        (instructions :+ EXECUTE(events), label)
    }
    
    private def compileBlock(statements: Vector[Statement],
                             instructions: Vector[Instruction], label: Int): (Vector[Instruction], Int) = {
        val (newInstructions, afterBlockLabel) = statements.foldLeft((instructions, label)) {
            case ((instructions, label), statement) => compile(statement, instructions, label)
        }
        
        (instructions ++ newInstructions, afterBlockLabel)
    }
    
    private def compileWhenThereforeSeq(whenThereforeSeq: Vector[WhenTherefore], otherwiseStatement: Statement,
                                        label: Int): (Vector[Instruction], Int) = {
        whenThereforeSeq match {
            case WhenTherefore(condition, thereforeStatement) +: rest =>
                val elseLabel = label
                val exitLabel = label + 1
                
                val (thenInstructions, afterThenLabel) = compile(thereforeStatement, Vector.empty, exitLabel + 1)
                val (elseInstructions, afterElseLabel) = compileWhenThereforeSeq(rest, otherwiseStatement, afterThenLabel + 1)
                
                val newInstructions = TEST(condition) ++
                        GOTO(elseLabel) ++
                        thenInstructions ++
                        GOTO(exitLabel) ++
                        LABEL(elseLabel) ++
                        elseInstructions ++
                        LABEL(exitLabel)
                
                (newInstructions, afterElseLabel)
            case _ =>
                compile(otherwiseStatement, Vector.empty, label)
        }
    }
    
    private def compileWhen(whenThereforeSeq: Vector[WhenTherefore], otherwiseStatement: Statement,
                            instructions: Vector[Instruction], label: Int): (Vector[Instruction], Int) = {
        val (newInstructions, afterWhenLabel) =
            compileWhenThereforeSeq(whenThereforeSeq, otherwiseStatement, label)
        
        (instructions ++ newInstructions, afterWhenLabel)
    }
    
    private def compileLoop(condition: BooleanValue, body: Statement,
                            instructions: Vector[Instruction], label: Int): (Vector[Instruction], Int) = {
        val loopLabel = label
        val exitLabel = label + 1
        
        val (loopedInstructions, afterLoopLabelId) = compile(body, Vector.empty, exitLabel + 1)
        
        val newInstructions = LABEL(loopLabel) ++
                TEST(condition) ++
                GOTO(exitLabel) ++
                loopedInstructions ++
                GOTO(loopLabel) ++
                LABEL(exitLabel)
        
        (instructions ++ newInstructions, afterLoopLabelId)
    }
    
    private def compileVariant(variantWhenTherefore: VariantWhenTherefore, chooseValue: Value, exitLabel: Int,
                               label: Int): (Vector[Instruction], Int) = {
        val variantExitLabel = label
        val (variantInstructions, afterVariantLabel) = compile(variantWhenTherefore.therefore, Vector.empty, variantExitLabel + 1)
        
        val condition = variantWhenTherefore.values
                .map(value => chooseValue === value)
                .reduceLeft(_ || _) && variantWhenTherefore.condition
        
        val newInstructions = TEST(condition) ++
                GOTO(variantExitLabel) ++
                variantInstructions ++
                GOTO(exitLabel) ++
                LABEL(variantExitLabel)
        
        (newInstructions, afterVariantLabel)
    }
    
    private def compileChoose(value: Value, variants: Vector[VariantWhenTherefore], otherwise: Statement,
                              instructions: Vector[Instruction], label: Int): (Vector[Instruction], Int) = {
        val exitLabel = label
        
        val (variantInstructions, afterVariantLabel) = variants.foldLeft((Vector.empty[Instruction], exitLabel + 1)) {
            case ((instructions, label), variant) =>
                val (newInstructions, afterVariantLabel) = compileVariant(variant, value, exitLabel, label)
                (instructions ++ newInstructions, afterVariantLabel)
        }
        val (otherwiseInstructions, afterOtherwiseLabel) = compile(otherwise, Vector.empty, afterVariantLabel)
        
        val newInstructions = variantInstructions ++
                otherwiseInstructions ++
                LABEL(exitLabel)
        
        (instructions ++ newInstructions, afterOtherwiseLabel)
    }
}

package statement

import events.Event.Events
import model.script.Instruction.{EXECUTE, EXIT, GOTO, LABEL, TEST}
import model.script.{Instruction, Script}
import statement.Statement.{Block, ChooseVariants, ChooseVariantsOtherwise, Execute, LoopBody, MultiWhenTherefore, MultiWhenThereforeOtherwise, VariantWhenTherefore, WhenTherefore}
import value.Value
import value.basic.BooleanValue

import scala.language.implicitConversions

object StatementCompiler {
    implicit private def inst2Vec(instruction: Instruction): Vector[Instruction] = Vector(instruction)
    
    def compileToScript(statement: Statement): Script = {
        Script(compile(statement) :+ EXIT(0))
    }
    
    def compile(statement: Statement): Vector[Instruction] = {
        val (instructions, _) = compile(statement, Vector.empty, 0)
        instructions
    }
    
    private def compile(statement: Statement, instructions: Vector[Instruction], labelId: Int): (Vector[Instruction], Int) = {
        statement match {
            case Execute(events) =>
                compileExecute(events, instructions, labelId)
            case Block(statements) =>
                compileBlock(statements, instructions, labelId)
            case MultiWhenTherefore(whenThereforeSeq) =>
                compileWhen(whenThereforeSeq, Block(Vector.empty), instructions, labelId)
            case MultiWhenThereforeOtherwise(whenThereforeSeq, otherwiseStatement) =>
                compileWhen(whenThereforeSeq, otherwiseStatement, instructions, labelId)
            case LoopBody(condition, body) =>
                compileLoop(condition, body, instructions, labelId)
            case ChooseVariants(value, variants) =>
                compileChoose(value, variants, Block(Vector.empty), instructions, labelId)
            case ChooseVariantsOtherwise(value, variants, otherwise) =>
                compileChoose(value, variants, otherwise, instructions, labelId)
        }
    }
    
    private def compileExecute(events: Events,
                               instructions: Vector[Instruction], labelId: Int): (Vector[Instruction], Int) = {
        (instructions :+ EXECUTE(events), labelId)
    }
    
    private def compileBlock(statements: Vector[Statement],
                             instructions: Vector[Instruction], labelId: Int): (Vector[Instruction], Int) = {
        val (newInstructions, afterBlockLabelId) = statements.foldLeft((instructions, labelId)) {
            case ((instructions, labelId), statement) => compile(statement, instructions, labelId)
        }
        
        (instructions ++ newInstructions, afterBlockLabelId)
    }
    
    private def compileWhenThereforeSeq(whenThereforeSeq: Vector[WhenTherefore], otherwiseStatement: Statement,
                                        labelId: Int): (Vector[Instruction], Int) = {
        whenThereforeSeq match {
            case WhenTherefore(condition, thereforeStatement) +: rest =>
                val elseLabelId = labelId
                val exitLabelId = labelId + 1
                
                val (thenInstructions, afterThenLabelId) = compile(thereforeStatement, Vector.empty, exitLabelId + 1)
                val (elseInstructions, afterElseLabelId) = compileWhenThereforeSeq(rest, otherwiseStatement, afterThenLabelId + 1)
                
                val newInstructions = TEST(condition) ++
                        GOTO(elseLabelId) ++
                        thenInstructions ++
                        GOTO(exitLabelId) ++
                        LABEL(elseLabelId) ++
                        elseInstructions ++
                        LABEL(exitLabelId)
                
                (newInstructions, afterElseLabelId)
            case _ =>
                compile(otherwiseStatement, Vector.empty, labelId)
        }
    }
    
    private def compileWhen(whenThereforeSeq: Vector[WhenTherefore], otherwiseStatement: Statement,
                            instructions: Vector[Instruction], labelId: Int): (Vector[Instruction], Int) = {
        val (newInstructions, afterWhenLabelId) =
            compileWhenThereforeSeq(whenThereforeSeq, otherwiseStatement, labelId)
        
        (instructions ++ newInstructions, afterWhenLabelId)
    }
    
    private def compileLoop(condition: BooleanValue, body: Statement,
                            instructions: Vector[Instruction], labelId: Int): (Vector[Instruction], Int) = {
        val loopLabelId = labelId
        val exitLabelId = labelId + 1
        
        val (loopedInstructions, afterLoopLabelIdId) = compile(body, Vector.empty, exitLabelId + 1)
        
        val newInstructions = LABEL(loopLabelId) ++
                TEST(condition) ++
                GOTO(exitLabelId) ++
                loopedInstructions ++
                GOTO(loopLabelId) ++
                LABEL(exitLabelId)
        
        (instructions ++ newInstructions, afterLoopLabelIdId)
    }
    
    private def compileVariant(variantWhenTherefore: VariantWhenTherefore, chooseValue: Value, exitLabelId: Int,
                               labelId: Int): (Vector[Instruction], Int) = {
        val variantExitLabelId = labelId
        val (variantInstructions, afterVariantLabelId) = compile(variantWhenTherefore.therefore, Vector.empty, variantExitLabelId + 1)
        
        val valueCondition = variantWhenTherefore.values
                .map(value => chooseValue === value)
                .reduceLeft(_ || _)
        
        val condition = variantWhenTherefore.conditionOpt match {
            case Some(condition) => valueCondition && condition
            case None => valueCondition
        }
        
        val newInstructions = TEST(condition) ++
                GOTO(variantExitLabelId) ++
                variantInstructions ++
                GOTO(exitLabelId) ++
                LABEL(variantExitLabelId)
        
        (newInstructions, afterVariantLabelId)
    }
    
    private def compileChoose(value: Value, variants: Vector[VariantWhenTherefore], otherwise: Statement,
                              instructions: Vector[Instruction], labelId: Int): (Vector[Instruction], Int) = {
        val exitLabelId = labelId
        
        val (variantInstructions, afterVariantLabelId) = variants.foldLeft((Vector.empty[Instruction], exitLabelId + 1)) {
            case ((instructions, labelId), variant) =>
                val (newInstructions, afterVariantLabelId) = compileVariant(variant, value, exitLabelId, labelId)
                (instructions ++ newInstructions, afterVariantLabelId)
        }
        val (otherwiseInstructions, afterOtherwiseLabelId) = compile(otherwise, Vector.empty, afterVariantLabelId)
        
        val newInstructions = variantInstructions ++
                otherwiseInstructions ++
                LABEL(exitLabelId)
        
        (instructions ++ newInstructions, afterOtherwiseLabelId)
    }
}

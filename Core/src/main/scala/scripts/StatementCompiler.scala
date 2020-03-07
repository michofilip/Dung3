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
                compileMultiWhenTherefore(whenThereforeSeq, instructions, label)
            case MultiWhenThereforeOtherwise(whenThereforeSeq, otherwiseStatement) =>
                compileMultiWhenThereforeOtherwise(whenThereforeSeq, otherwiseStatement, instructions, label)
            case LoopBody(condition, body) =>
                compileLoopBody(condition, body, instructions, label)
            case ChooseVariants(value, variants) =>
                compileChooseVariantsOtherwise(value, variants, Block(Vector.empty), instructions, label)
            case ChooseVariantsOtherwise(value, variants, otherwise) =>
                compileChooseVariantsOtherwise(value, variants, otherwise, instructions, label)
        }
        
        //        statement match {
        //            case st: Execute => compileExecute(st, instructions, label)
        //            case st: Block => compileBlock(st, instructions, label)
        //            case st: MultiWhenTherefore => compileMultiWhenTherefore(st, instructions, label)
        //            case st: MultiWhenThereforeOtherwise => compileMultiWhenThereforeOtherwise(st, instructions, label)
        //            case st: LoopBody => compileLoopBody(st, instructions, label)
        //            case ChooseVariants(value, variants) => ???
        //            case st: ChooseVariantsOtherwise => compileChooseVariantsOtherwise(st, instructions, label)
        //        }
    }
    
    private def compileExecute(events: Events,
                               instructions: Vector[Instruction], label: Int): (Vector[Instruction], Int) = {
        (instructions :+ EXECUTE(events), label)
    }
    
    private def compileBlock(statements: Vector[Statement],
                             instructions: Vector[Instruction], label: Int): (Vector[Instruction], Int) = {
        val (blockInstructions, afterBlockLabel) = statements.foldLeft((instructions, label)) {
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
    
    private def compileMultiWhenThereforeOtherwise(whenThereforeSeq: Vector[WhenTherefore], otherwiseStatement: Statement,
                                                   instructions: Vector[Instruction], label: Int): (Vector[Instruction], Int) = {
        val (whenInstructions, afterWhenLabel) =
            compileWhenThereforeSeq(whenThereforeSeq, otherwiseStatement, label)
        
        (instructions ++ whenInstructions, afterWhenLabel)
    }
    
    
    //    private def compileMultiWhenThereforeOtherwise(multiWhenThereforeOtherwise: MultiWhenThereforeOtherwise,
    //                                                   instructions: Vector[Instruction], label: Int): (Vector[Instruction], Int) = {
    //        val (whenInstructions, afterWhenLabel) =
    //            compileWhenThereforeSeq(multiWhenThereforeOtherwise.whenThereforeSeq, multiWhenThereforeOtherwise.otherwiseStatement, label)
    //
    //        (instructions ++ whenInstructions, afterWhenLabel)
    //    }
    
    private def compileMultiWhenTherefore(whenThereforeSeq: Vector[WhenTherefore],
                                          instructions: Vector[Instruction], label: Int): (Vector[Instruction], Int) = {
        val (whenInstructions, afterWhenLabel) =
            compileWhenThereforeSeq(whenThereforeSeq, Block(Vector.empty), label)
        (instructions ++ whenInstructions, afterWhenLabel)
    }
    
    //    private def compileMultiWhenTherefore(multiWhenTherefore: MultiWhenTherefore,
    //                                          instructions: Vector[Instruction], label: Int): (Vector[Instruction], Int) = {
    //        val (whenInstructions, afterWhenLabel) =
    //            compileWhenThereforeSeq(multiWhenTherefore.whenThereforeSeq, Block(Vector.empty), label)
    //        (instructions ++ whenInstructions, afterWhenLabel)
    //    }
    
    private def compileLoopBody(condition: BooleanValue, body: Statement, instructions: Vector[Instruction], label: Int): (Vector[Instruction], Int) = {
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
    
    //    private def compileLoopBody(loopBody: LoopBody, instructions: Vector[Instruction], label: Int): (Vector[Instruction], Int) = {
    //        val loopLabel = label
    //        val exitLabel = label + 1
    //
    //        val (loopedInstructions, afterLoopLabelId) = compile(loopBody.body, Vector.empty, exitLabel + 1)
    //
    //        val newInstructions = LABEL(loopLabel) ++
    //                TEST(loopBody.condition) ++
    //                GOTO(exitLabel) ++
    //                loopedInstructions ++
    //                GOTO(loopLabel) ++
    //                LABEL(exitLabel)
    //
    //        (instructions ++ newInstructions, afterLoopLabelId)
    //    }
    
    private def compileVariant(variantWhenTherefore: VariantWhenTherefore, chooseValue: Value, exitLabel: Int, label: Int): (Vector[Instruction], Int) = {
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
    
    private def compileChooseVariantsOtherwise(value: Value, variants: Vector[VariantWhenTherefore], otherwise: Statement,
                                               instructions: Vector[Instruction], label: Int): (Vector[Instruction], Int) = {
        val exitLabel = label
        
        val (variantInstructions, afterVariantLabel) = variants.foldLeft((Vector.empty[Instruction], exitLabel + 1)) {
            case ((instructions, labelId), variant) =>
                val (newInstructions, afterVariantLabel) = compileVariant(variant, value, exitLabel, labelId)
                (instructions ++ newInstructions, afterVariantLabel)
        }
        val (otherwiseInstructions, afterOtherwiseLabel) = compile(otherwise, Vector.empty, afterVariantLabel)
        
        val newInstructions = variantInstructions ++
                otherwiseInstructions ++
                LABEL(exitLabel)
        
        (instructions ++ newInstructions, afterOtherwiseLabel)
        
    }
    
    //    private def compileChooseVariantsOtherwise(chooseVariantsOtherwise: ChooseVariantsOtherwise,
    //                                               instructions: Vector[Instruction], label: Int): (Vector[Instruction], Int) = {
    //        val exitLabel = label
    //
    //        val (variantInstructions, afterVariantLabel) = chooseVariantsOtherwise.variants.foldLeft((Vector.empty[Instruction], exitLabel + 1)) {
    //            case ((instructions, labelId), variant) =>
    //                val (newInstructions, afterVariantLabel) = compileVariant(variant, chooseVariantsOtherwise.value, exitLabel, labelId)
    //                (instructions ++ newInstructions, afterVariantLabel)
    //        }
    //        val (otherwiseInstructions, afterOtherwiseLabel) = compile(chooseVariantsOtherwise.otherwise, Vector.empty, afterVariantLabel)
    //
    //        val newInstructions = variantInstructions ++
    //                otherwiseInstructions ++
    //                LABEL(exitLabel)
    //
    //        (instructions ++ newInstructions, afterOtherwiseLabel)
    //
    //    }
    
    
    //    case class Variant(variantTest: Value, variantStatement: Statement) {
    //        private[Statement] def compile(initialInstructions: Vector[Instruction], switchTest: Value, exitLabelId: Int, initialLabelId: Int): (Vector[Instruction], Int) = {
    //            val variantExitLabelId = initialLabelId
    //            val (variantInstructions, afterVariantLabelId) = variantStatement.compile(Vector.empty, variantExitLabelId + 1)
    //
    //            val instructions = TEST(switchTest === variantTest) ++
    //                    GOTO(variantExitLabelId) ++
    //                    variantInstructions ++
    //                    GOTO(exitLabelId) ++
    //                    LABEL(variantExitLabelId)
    //
    //            (initialInstructions ++ instructions, afterVariantLabelId)
    //        }
    //    }
    //
    //    case class Choose(switchTest: Value, variants: Vector[Variant], defaultStatement: Statement) extends Statement {
    //        override protected def compile(initialInstructions: Vector[Instruction], initialLabelId: Int): (Vector[Instruction], Int) = {
    //            val exitLabelId = initialLabelId
    //
    //            val (variantInstructions, afterVariantLabelId) = variants.foldLeft((Vector.empty[Instruction], exitLabelId + 1)) {
    //                case ((previousVariantInstructions, labelId), variant) =>
    //                    variant.compile(previousVariantInstructions, switchTest, exitLabelId, labelId)
    //            }
    //            val (defaultInstructions, afterDefaultLabelId) = defaultStatement.compile(Vector.empty, afterVariantLabelId)
    //
    //            val instructions = variantInstructions ++
    //                    defaultInstructions ++
    //                    LABEL(exitLabelId)
    //
    //            (initialInstructions ++ instructions, afterDefaultLabelId)
    //        }
    //    }
}

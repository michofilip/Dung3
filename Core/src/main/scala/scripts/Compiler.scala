package scripts

import scripts.Instruction.EXECUTE
import scripts.Statement.{Block, ChooseVariants, ChooseVariantsOtherwise, Execute, LoopBody, MultiWhenTherefore, MultiWhenThereforeOtherwise, WhenTherefore}

object Compiler {
    def compile(statement: Statement): Vector[Instruction] = {
        val (instructions, _) = compile(statement, Vector.empty, 0)
        instructions
    }

    private def compile(statement: Statement, instructions: Vector[Instruction], label: Int): (Vector[Instruction], Int) = {
        statement match {
            case st: Execute => compile(st, instructions, label)
            case st: Block => compile(st, instructions, label)
            case WhenTherefore(condition, thereforeStatement) => ???
            case MultiWhenTherefore(whenThereforeSeq) => ???
            case MultiWhenThereforeOtherwise(whenThereforeSeq, otherwiseStatement) => ???
            case LoopBody(condition, body) => ???
            case ChooseVariants(value, variants) => ???
            case ChooseVariantsOtherwise(value, variants, otherwise) => ???
        }
    }

    private def compile(execute: Execute, instructions: Vector[Instruction], label: Int): (Vector[Instruction], Int) = {
        (instructions :+ EXECUTE(execute.events), label)
    }

    private def compile(block: Block, instructions: Vector[Instruction], label: Int): (Vector[Instruction], Int) = {
        val (blockInstructions, afterBlockLabel) = block.statements.foldLeft((instructions, label)) {
            case ((instructions, labelId), statement) => compile(statement, instructions, labelId)
        }
        (instructions ++ blockInstructions, afterBlockLabel)
    }
}

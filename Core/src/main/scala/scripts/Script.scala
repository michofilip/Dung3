package scripts

import scripts.Instruction.{EXIT, GOTO, LABEL}

case class Script(instructions: Vector[Instruction]) {
    private val scriptLength = instructions.length
    
    private val labelMap: Map[Int, Int] =
        instructions.zipWithIndex.foldLeft(Map.empty[Int, Int]) {
            case (labelMap, (LABEL(labelId), lineNo)) => labelMap + (labelId -> lineNo)
            case (labelMap, _) => labelMap
        }
    
    private def getInstruction(lineNo: Int): Instruction =
        if (0 <= lineNo && lineNo < scriptLength)
            instructions(lineNo)
        else
            EXIT(1)
    
    def getNextInstruction(lineNo: Int): Instruction = {
        getInstruction(lineNo) match {
            case LABEL(_) => getNextInstruction(lineNo + 1)
            case GOTO(labelId) => labelMap.get(labelId) match {
                case Some(lineNo) => getNextInstruction(lineNo + 1)
                case None => EXIT(2)
            }
            case instruction: _ => instruction
        }
    }
}

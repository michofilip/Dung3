package engine.entity.parts.script

import engine.value.basic.BooleanValue

sealed abstract class Instruction

object Instruction {

    final case class EXIT(code: Int) extends Instruction

    //fixme move events
    //    final case class EXECUTE(events: Vector[Event]) extends Instruction

    final case class LABEL(labelId: Int) extends Instruction

    final case class GOTO(labelId: Int) extends Instruction

    final case class TEST(condition: BooleanValue) extends Instruction

}

package engine.entity.parts.script

import engine.events.Event.Events
import engine.value.basic.BooleanValue

sealed abstract class Instruction

object Instruction {

    final case class EXIT(code: Int) extends Instruction

    final case class EXECUTE(events: Events) extends Instruction

    final case class LABEL(labelId: Int) extends Instruction

    final case class GOTO(labelId: Int) extends Instruction

    final case class TEST(condition: BooleanValue) extends Instruction

}

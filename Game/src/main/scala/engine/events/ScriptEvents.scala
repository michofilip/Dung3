package engine.events

import engine.GameContext
import engine.entity.Entity
import engine.entity.EntityServices._
import engine.entity.parts.script.Instruction.{EXECUTE, EXIT, TEST}
import engine.entity.parts.script.Script
import engine.events.Event._

object ScriptEvents {

    final case class RunScript(override val entityId: Long, scriptName: String) extends Event {
        override def applyTo(entity: Entity)(implicit gc: GameContext): EventResponse = {
            val responseEvents = entity.getScript(scriptName) match {
                case Some(script) => Vector(ExecuteScriptLine(entityId, script, 0))
                case None => Vector.empty
            }

            (Vector(entity), responseEvents)
        }
    }

    final case class ExecuteScriptLine(override val entityId: Long, script: Script, lineNo: Int) extends Event {
        override def applyTo(entity: Entity)(implicit gc: GameContext): EventResponse = {
            val responseEvents = script.getNext(lineNo) match {
                case (_, EXIT(_)) => Vector.empty
                case (nextLineNo, EXECUTE(events)) => events :+ ExecuteScriptLine(entityId, script, nextLineNo + 1)
                case (nextLineNo, TEST(condition)) => condition.get match {
                    case Some(true) => Vector(ExecuteScriptLine(entityId, script, nextLineNo + 2))
                    case Some(false) => Vector(ExecuteScriptLine(entityId, script, nextLineNo + 1))
                    case None => Vector.empty
                }
                case _ => Vector.empty
            }

            (Vector(entity), responseEvents)
        }
    }

}

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
            entity.getScript(scriptName) match {
                case Some(script) => (entity, ExecuteScriptLine(entityId, script, 0))
                case None => entity
            }
        }
    }

    final case class ExecuteScriptLine(override val entityId: Long, script: Script, lineNo: Int) extends Event {
        override def applyTo(entity: Entity)(implicit gc: GameContext): EventResponse = {
            script.getNext(lineNo) match {
                case (_, EXIT(_)) => entity
                case (nextLinNo, EXECUTE(events)) => (entity, events ++ ExecuteScriptLine(entityId, script, nextLinNo + 1))
                case (nextLinNo, TEST(condition)) => condition.get match {
                    case Some(true) => (entity, ExecuteScriptLine(entityId, script, nextLinNo + 2))
                    case Some(false) => (entity, ExecuteScriptLine(entityId, script, nextLinNo + 1))
                    case None => entity
                }
                case _ => entity
            }
        }
    }

}

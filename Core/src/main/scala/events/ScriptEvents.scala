package events

import entity.Entity
import events.Event.{EventResponse, _}
import scripts.Instruction.{EXECUTE, EXIT, TEST}
import scripts.Script
import world.WorldFrameContext

object ScriptEvents {
    
    // script
    final case class RunScript(override val entityId: Long, scriptName: String) extends Event {
        override def applyTo(entity: Entity)(implicit wfc: WorldFrameContext): EventResponse = {
            entity match {
                //            case ent: ScriptProperty => (entity, ExecuteScriptLine(entityId, ent.getScript(scriptName), 0))
                case _ => entity
            }
        }
    }
    
    final case class ExecuteScriptLine(override val entityId: Long, script: Script, lineNo: Int) extends Event {
        override def applyTo(entity: Entity)(implicit wfc: WorldFrameContext): EventResponse = {
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

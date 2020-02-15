package events

import commons.utils.FunctionUtils._
import entity.Entity
import entity.EntityServices._
import events.Event._
import model.position.{Direction, PositionMappers}
import model.state.{State, StateMappers}
import world.WorldFrameContext

object PositionEvents {
    
    final case class MoveTo(override val entityId: Long, x: Int, y: Int) extends Event {
        override def applyTo(entity: Entity)(implicit wfc: WorldFrameContext): EventResponse = {
            entity.updatePosition(PositionMappers.moveTo(x, y), wfc.timestamp)
        }
    }
    
    final case class MoveBy(override val entityId: Long, dx: Int, dy: Int) extends Event {
        override def applyTo(entity: Entity)(implicit wfc: WorldFrameContext): EventResponse = {
            entity.updatePosition(PositionMappers.moveBy(dx, dy), wfc.timestamp)
        }
    }
    
    final case class Step(override val entityId: Long, direction: Direction) extends Event {
        override def applyTo(entity: Entity)(implicit wfc: WorldFrameContext): EventResponse = {
            import PositionMappers._
            entity.getState match {
                case Some(State.Standing) => entity
                        .updatePosition(step(direction) --> rotateTo(direction), wfc.timestamp)
                        .updateState(StateMappers.movement, wfc.timestamp)
                        .updateAnimation()
                case _ => entity
            }
        }
    }
    
}

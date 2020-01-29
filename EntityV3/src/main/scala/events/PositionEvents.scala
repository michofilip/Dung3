package events

import entity.Entity
import events.Event._
import model.position.{Direction, Position}
import model.state.State
import world.WorldFrameContext

object PositionEvents {
    
    final case class MoveTo(override val entityId: Long, x: Int, y: Int) extends Event {
        override def applyTo(entity: Entity)(implicit wfc: WorldFrameContext): (Vector[Entity], Vector[Event]) = {
            val newEntity = entity.updatePosition(Position.moveTo(x, y), wfc.timestamp)
            (newEntity, Vector.empty)
        }
    }
    
    final case class MoveBy(override val entityId: Long, dx: Int, dy: Int) extends Event {
        override def applyTo(entity: Entity)(implicit wfc: WorldFrameContext): (Vector[Entity], Vector[Event]) = {
            val newEntity = entity.updatePosition(Position.moveBy(dx, dy), wfc.timestamp)
            (newEntity, Vector.empty)
        }
    }
    
    final case class Step(override val entityId: Long, direction: Direction) extends Event {
        override def applyTo(entity: Entity)(implicit wfc: WorldFrameContext): (Vector[Entity], Vector[Event]) = {
            val newEntity = entity
                    .updatePosition(Position.step(direction), wfc.timestamp)
                    .updatePosition(Position.rotateTo(direction), wfc.timestamp)
                    .updateState(State.movement, wfc.timestamp)
            (newEntity, Vector.empty)
        }
    }
    
}

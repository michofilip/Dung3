package events

import entity.Entity
import events.Event._
import model.position.{Direction, Position}
import model.state.State
import world.WorldFrameContext

object PositionEvents {
    
    final case class MoveTo(override val entityId: Long, x: Int, y: Int) extends Event {
        override def applyTo(entity: Entity)(implicit wfc: WorldFrameContext): (Vector[Entity], Vector[Event]) = {
            val newEntity = entity.updatePositionContainer(Position.moveTo(x, y), wfc.timestamp)
            (newEntity, Vector.empty)
        }
    }
    
    final case class MoveBy(override val entityId: Long, dx: Int, dy: Int) extends Event {
        override def applyTo(entity: Entity)(implicit wfc: WorldFrameContext): (Vector[Entity], Vector[Event]) = {
            val newEntity = entity.updatePositionContainer(Position.moveBy(dx, dy), wfc.timestamp)
            (newEntity, Vector.empty)
        }
    }
    
    final case class Step(override val entityId: Long, direction: Direction) extends Event {
        override def applyTo(entity: Entity)(implicit wfc: WorldFrameContext): (Vector[Entity], Vector[Event]) = {
            val newEntity = entity
                    .updatePositionContainer(Position.step(direction), wfc.timestamp)
                    .updatePositionContainer(Position.rotateTo(direction), wfc.timestamp)
                    .updateState(State.movement, wfc.timestamp)
            (newEntity, Vector.empty)
        }
    }
    
}

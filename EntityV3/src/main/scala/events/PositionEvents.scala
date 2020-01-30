package events

import commons.utils.FunctionUtils._
import entity.Entity
import events.Event._
import model.position.{Direction, PositionMappers}
import model.state.State
import world.WorldFrameContext

object PositionEvents {
    
    final case class MoveTo(override val entityId: Long, x: Int, y: Int) extends Event {
        override def applyTo(entity: Entity)(implicit wfc: WorldFrameContext): (Vector[Entity], Vector[Event]) = {
            val newEntity = entity.updatePositionContainer(PositionMappers.moveTo(x, y), wfc.timestamp)
            (newEntity, Vector.empty)
        }
    }
    
    final case class MoveBy(override val entityId: Long, dx: Int, dy: Int) extends Event {
        override def applyTo(entity: Entity)(implicit wfc: WorldFrameContext): (Vector[Entity], Vector[Event]) = {
            val newEntity = entity.updatePositionContainer(PositionMappers.moveBy(dx, dy), wfc.timestamp)
            (newEntity, Vector.empty)
        }
    }
    
    final case class Step(override val entityId: Long, direction: Direction) extends Event {
        override def applyTo(entity: Entity)(implicit wfc: WorldFrameContext): (Vector[Entity], Vector[Event]) = {
            val positionMapper = PositionMappers.step(direction) --> PositionMappers.rotateTo(direction)
            
            val newEntity = entity
                    .updatePositionContainer(positionMapper, wfc.timestamp)
                    .updateState(State.movement, wfc.timestamp)
            (newEntity, Vector.empty)
        }
    }
    
}

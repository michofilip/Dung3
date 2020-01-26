package events

import entity.Entity
import events.EventImplicits._
import model.position.Position
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
    
}

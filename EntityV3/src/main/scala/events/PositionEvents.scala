package events

import entity.Entity
import events.EventImplicits._
import model.position.Coordinates
import world.WorldFrameContext

object PositionEvents {
    
    final case class MoveTo(override val entityId: Long, x: Int, y: Int) extends Event {
        override def applyTo(entity: Entity)(implicit wfc: WorldFrameContext): (Vector[Entity], Vector[Event]) = {
            val newEntity = entity.updateCoordinates(_ => Coordinates(x, y), wfc.timestamp)
            (newEntity, Vector.empty)
        }
    }
    
    final case class MoveBy(override val entityId: Long, dx: Int, dy: Int) extends Event {
        override def applyTo(entity: Entity)(implicit wfc: WorldFrameContext): (Vector[Entity], Vector[Event]) = {
            val newEntity = entity.updateCoordinates(_.shift(dx, dy), wfc.timestamp)
            (newEntity, Vector.empty)
        }
    }
    
}

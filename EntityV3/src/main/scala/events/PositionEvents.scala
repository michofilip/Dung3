package events

import commons.temporal.Timestamp
import entity.Entity
import events.EventImplicits._
import model.position.Coordinates

object PositionEvents {
    
    final case class MoveTo(override val entityId: Long, x: Int, y: Int) extends Event {
        override def applyTo(entity: Entity): (Vector[Entity], Vector[Event]) = {
            val newEntity = entity.updateCoordinates(_ => Coordinates(x, y), Timestamp.now)
            (newEntity, Vector.empty)
        }
    }
    
    final case class MoveBy(override val entityId: Long, dx: Int, dy: Int) extends Event {
        override def applyTo(entity: Entity): (Vector[Entity], Vector[Event]) = {
            val newEntity = entity.updateCoordinates(_.shift(dx, dy), Timestamp.now)
            (newEntity, Vector.empty)
        }
    }
    
}

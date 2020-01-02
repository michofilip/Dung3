package events

import entity.Entity

import scala.language.implicitConversions

object EventImplicits {
    implicit def en2Vector(entity: Entity): Vector[Entity] = Vector[Entity](entity)
    
    implicit def ev2Vector(event: Event): Vector[Event] = Vector(event)
}

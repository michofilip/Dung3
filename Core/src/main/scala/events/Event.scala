package events

import entity.Entity
import world.WorldFrameContext

import scala.language.implicitConversions

abstract class Event {
    val entityId: Long
    
    def applyTo(entity: Entity)(implicit wfc: WorldFrameContext): (Vector[Entity], Vector[Event])
}

object Event {
    implicit def en2Vector(entity: Entity): Vector[Entity] = Vector[Entity](entity)
    
    implicit def ev2Vector(event: Event): Vector[Event] = Vector(event)
}
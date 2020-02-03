package events

import entity.Entity
import events.Event.EventResponse
import world.WorldFrameContext

import scala.language.implicitConversions

abstract class Event {
    val entityId: Long
    
    def applyTo(entity: Entity)(implicit wfc: WorldFrameContext): EventResponse
}

object Event {
    type EventResponse = (Vector[Entity], Vector[Event])
    
    implicit def en2Vector(entity: Entity): Vector[Entity] = Vector[Entity](entity)
    
    implicit def ev2Vector(event: Event): Vector[Event] = Vector(event)
    
    implicit def en2EventResponse(entity: Entity): EventResponse = (entity, Vector.empty)
    
    implicit def ens2EventResponse(entities: Vector[Entity]): EventResponse = (entities, Vector.empty)
    
    implicit def ev2EventResponse(event: Event): EventResponse = (Vector.empty, event)
    
    implicit def evs2EventResponse(events: Vector[Event]): EventResponse = (Vector.empty, events)
    
    
}
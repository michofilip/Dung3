package engine.events

import engine.GameContext
import engine.entity.Entity
import engine.events.EventResponse

import scala.language.implicitConversions

abstract class Event {
    val entityId: Long

    def applyTo(entity: Entity)(implicit gc: GameContext): EventResponse
}

object Event {
    type Entities = Vector[Entity]
    type Events = Vector[Event]
////    type EventResponse = (Entities, Events)
//
//    def emptyEntities: Entities = Vector.empty
//
//    def emptyEvents: Events = Vector.empty
//
//    def emptyResponse: EventResponse = (emptyEntities, emptyEvents)
//
//    implicit def en2Vector(entity: Entity): Entities = Vector(entity)
//
//    implicit def ev2Vector(event: Event): Events = Vector(event)
//
//    implicit def en2EventResponse(entity: Entity): EventResponse = (entity, emptyEvents)
//
//    implicit def ens2EventResponse(entities: Entities): EventResponse = (entities, emptyEvents)
//
//    implicit def ev2EventResponse(event: Event): EventResponse = (emptyEntities, event)
//
//    implicit def evs2EventResponse(events: Events): EventResponse = (emptyEntities, events)


}
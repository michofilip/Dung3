package engine.events

import engine.entity.Entity

case class EventResponse(entities: Vector[Entity] = Vector.empty, events: Vector[Event] = Vector.empty) {
    def withEntity(entity: Entity): EventResponse =
        EventResponse(this.entities :+ entity, events)

    def withEntities(entities: Seq[Entity]): EventResponse =
        EventResponse(this.entities ++ entities, events)

    def withEvent(event: Event): EventResponse =
        EventResponse(entities, this.events :+ event)

    def withEvents(events: Seq[Event]): EventResponse =
        EventResponse(entities, this.events ++ events)
}

package world

import commons.temporal.{Timer, Timestamp}
import events.Event
import repository.EntityRepository

case class WorldFrame(timestamp: Timestamp, turn: Int, entities: EntityRepository, events: Vector[Event]) {
    
    def nextFrame(externalEvents: Vector[Event] = Vector.empty)(implicit timer: Timer): WorldFrame = {
        val allEvents = events ++ externalEvents
        val newTimestamp = timer.getTimestamp
        
        implicit val wfc: WorldFrameContext = WorldFrameContext(newTimestamp, turn, entities)
        
        val (newEntities, newEvents) = allEvents.foldLeft(entities, Vector.empty[Event]) {
            case ((entities, events), event) => entities.getById(event.entityId).map(entity => {
                val (resultEntities, resultEvents) = event.applyTo(entity)
                val newEntityRepository = entities - entity ++ resultEntities
                val newEvents = events ++ resultEvents
                (newEntityRepository, newEvents)
            }).getOrElse((entities, events))
        }
        
        WorldFrame(newTimestamp, turn, newEntities, newEvents)
    }
}

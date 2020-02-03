package world

import commons.temporal.Timestamp
import events.Event
import repository.EntityRepository

case class WorldFrame(timestamp: Timestamp, turn: Int, entities: EntityRepository, events: Vector[Event]) {
    def nextTurn(): WorldFrame =
        copy(turn = turn + 1)
    
    def nextFrame(externalEvents: Vector[Event], timestamp: Timestamp): WorldFrame = {
        implicit val wfc: WorldFrameContext = WorldFrameContext(timestamp, turn, entities)
        
        val allEvents = events ++ externalEvents
        val (newEntities, newEvents) = allEvents.foldLeft(entities, Vector.empty[Event]) {
            case ((entities, events), event) => entities.getById(event.entityId).map(entity => {
                val (resultEntities, resultEvents) = event.applyTo(entity)
                val newEntityRepository = entities - entity ++ resultEntities
                val newEvents = events ++ resultEvents
                (newEntityRepository, newEvents)
            }).getOrElse((entities, events))
        }
        
        WorldFrame(timestamp, turn, newEntities, newEvents)
    }
}
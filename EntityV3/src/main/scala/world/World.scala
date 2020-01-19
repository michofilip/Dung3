//package world
//
//import commons.temporal.{Timer, Timestamp}
//import events.Event
//import repository.EntityRepository
//
//class World(val timestamp: Timestamp, val turn: Int,
//            private val entityRepository: EntityRepository,
//            private val events: Vector[Event]) {
//
//    def nextFrame(externalEvents: Vector[Event] = Vector.empty)
//                 (implicit timer: Timer): World = {
//        val (newEntityRepository, newEvents) = events.foldLeft(entityRepository, externalEvents) {
//            case ((entityRepository, events), event) => entityRepository.getById(event.entityId).map(entity => {
//                val (resultEntities, resultEvents) = event.applyTo(entity)
//                val newEntityRepository = entityRepository - entity ++ resultEntities
//                val newEvents = events ++ resultEvents
//                (newEntityRepository, newEvents)
//            }).getOrElse((entityRepository, events))
//        }
//
//        new World(timestamp, turn, newEntityRepository, newEvents)
//    }
//}
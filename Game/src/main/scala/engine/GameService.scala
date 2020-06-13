package engine

import engine.events.Event
import engine.events.Event.Events
import engine.temporal.Timestamp

class GameService {

    implicit class GameServiceImplicit(gameContext: GameContext) {
        def nextTurn(): GameContext =
            gameContext.copy(turn = gameContext.turn + 1)

        def nextFrame(externalEvents: Events, timestamp: Timestamp): GameContext = {
            implicit val gc: GameContext = gameContext

            val allEvents = gc.events ++ externalEvents
            val (newEntities, newEvents) = allEvents.foldLeft(gc.entities, Vector.empty[Event]) {
                case ((entities, events), event) => entities.getById(event.entityId).map(
                    entity => {
                        val (resultEntities, resultEvents) = event.applyTo(entity)
                        val newEntityRepository = entities - entity ++ resultEntities
                        val newEvents = events ++ resultEvents
                        (newEntityRepository, newEvents)
                    }
                ).getOrElse((entities, events))
            }

            GameContext(timestamp, gc.turn, newEntities, newEvents)
        }
    }


}

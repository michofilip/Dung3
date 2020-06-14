package engine

import engine.events.Event
import engine.temporal.Timestamp

class GameService {

    def nextTurn(implicit gameContext: GameContext): GameContext =
        gameContext.copy(turn = gameContext.turn + 1)

    def nextFrame(externalEvents: Vector[Event], timestamp: Timestamp)
                 (implicit gameContext: GameContext): GameContext = {

        val allEvents = gameContext.events ++ externalEvents
        val (newEntities, newEvents) = allEvents.foldLeft(gameContext.entities, Vector.empty[Event]) {
            case ((entities, events), event) => entities.getById(event.entityId).map(
                entity => {
                    val eventResponse = event.applyTo(entity)
                    val newEntityRepository = entities - entity ++ eventResponse.entities
                    val newEvents = events ++ eventResponse.events
                    (newEntityRepository, newEvents)
                }
            ).getOrElse((entities, events))
        }

        GameContext(timestamp, gameContext.turn, newEntities, newEvents)
    }

}

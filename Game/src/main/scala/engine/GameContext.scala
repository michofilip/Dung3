package engine

import engine.events.Event.Events
import engine.repository.EntityRepository
import engine.temporal.Timestamp
import engine.value.ValueContext

case class GameContext(timestamp: Timestamp, turn: Int, entities: EntityRepository, events: Events) extends ValueContext
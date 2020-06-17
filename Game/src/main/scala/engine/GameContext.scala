package engine

import engine.events.Event
import engine.repository.EntityRepository
import engine.temporal.Timestamp

case class GameContext(timestamp: Timestamp, turn: Int, entities: EntityRepository, events: Vector[Event])
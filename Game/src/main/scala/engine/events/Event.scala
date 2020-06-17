package engine.events

import engine.GameContext
import engine.entity.Entity
import engine.events.Event.EventResponse

import scala.language.implicitConversions

abstract class Event {
    val entityId: Long

    def applyTo(entity: Entity)(implicit gc: GameContext): EventResponse
}

object Event {
    type EventResponse = (Vector[Entity], Vector[Event])
}
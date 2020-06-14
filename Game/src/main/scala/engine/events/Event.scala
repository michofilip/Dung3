package engine.events

import engine.GameContext
import engine.entity.Entity

import scala.language.implicitConversions

abstract class Event {
    val entityId: Long

    def applyTo(entity: Entity)(implicit gc: GameContext): EventResponse
}
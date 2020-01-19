package events

import entity.Entity
import world.WorldFrameContext

import scala.language.implicitConversions

abstract class Event {
    val entityId: Long
    
    def applyTo(entity: Entity)(implicit wfc: WorldFrameContext): (Vector[Entity], Vector[Event])
}
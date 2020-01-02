package events

import entity.Entity

import scala.language.implicitConversions

 abstract class Event {
    val entityId: Long
    
    def applyTo(entity: Entity): (Vector[Entity], Vector[Event])
}
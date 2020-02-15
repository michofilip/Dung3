package events

import commons.temporal.{Duration, Timestamp}
import entity.Entity
import events.Event.{EventResponse, Events, emptyResponse}
import world.WorldFrameContext

object ControlEvents {
    
    final case class Chain(override val entityId: Long, eventsSeq: Seq[Events]) extends Event {
        override def applyTo(entity: Entity)(implicit wfc: WorldFrameContext): EventResponse = {
            eventsSeq match {
                case Nil => emptyResponse
                case events +: Nil => events
                case events +: rest => events :+ Chain(entityId, rest)
            }
        }
    }
    
    final case class Repeat(override val entityId: Long, repetitions: Int, events: Events) extends Event {
        override def applyTo(entity: Entity)(implicit wfc: WorldFrameContext): EventResponse = {
            if (repetitions >= 2)
                events :+ Repeat(entityId, repetitions - 1, events)
            else if (repetitions == 1)
                events
            else
                emptyResponse
        }
    }
    
    final case class ScheduleTime(override val entityId: Long, timestamp: Timestamp, events: Events) extends Event {
        override def applyTo(entity: Entity)(implicit wfc: WorldFrameContext): EventResponse = {
            if (wfc.timestamp >= timestamp)
                events
            else
                this
        }
    }
    
    final case class ScheduleTurn(override val entityId: Long, turn: Int, events: Events) extends Event {
        override def applyTo(entity: Entity)(implicit wfc: WorldFrameContext): EventResponse = {
            if (wfc.turn >= turn)
                events
            else
                this
        }
    }
    
    final case class DelayTime(override val entityId: Long, duration: Duration, events: Events) extends Event {
        override def applyTo(entity: Entity)(implicit wfc: WorldFrameContext): EventResponse = {
            ScheduleTime(entityId, wfc.timestamp + duration, events)
        }
    }
    
    final case class DelayTurn(override val entityId: Long, turns: Int, events: Events) extends Event {
        override def applyTo(entity: Entity)(implicit wfc: WorldFrameContext): EventResponse = {
            ScheduleTurn(entityId, wfc.turn + turns, events)
        }
    }
    
}

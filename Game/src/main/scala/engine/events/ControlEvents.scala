package engine.events

import engine.GameContext
import engine.entity.Entity
import engine.temporal.{Duration, Timestamp}

object ControlEvents {

    final case class Chain(override val entityId: Long, eventsSeq: Seq[Vector[Event]]) extends Event {
        override def applyTo(entity: Entity)(implicit gc: GameContext): EventResponse = {
            EventResponse()
                .withEntity(entity)
                .withEvents {
                    eventsSeq match {
                        case Nil => Vector.empty
                        case events +: Nil => events
                        case events +: rest => events :+ Chain(entityId, rest)
                    }
                }
        }
    }

    final case class Repeat(override val entityId: Long, repetitions: Int, events: Vector[Event]) extends Event {
        override def applyTo(entity: Entity)(implicit gc: GameContext): EventResponse = {
            EventResponse()
                .withEntity(entity)
                .withEvents {
                    if (repetitions >= 2)
                        events :+ Repeat(entityId, repetitions - 1, events)
                    else if (repetitions == 1)
                        events
                    else
                        Vector.empty
                }
        }
    }

    final case class ScheduleTime(override val entityId: Long, timestamp: Timestamp, events: Vector[Event]) extends Event {
        override def applyTo(entity: Entity)(implicit gc: GameContext): EventResponse = {
            EventResponse()
                .withEntity(entity)
                .withEvents {
                    if (gc.timestamp >= timestamp)
                        events
                    else
                        Vector(this)
                }
        }
    }

    final case class ScheduleTurn(override val entityId: Long, turn: Int, events: Vector[Event]) extends Event {
        override def applyTo(entity: Entity)(implicit gc: GameContext): EventResponse = {
            EventResponse()
                .withEntity(entity)
                .withEvents {
                    if (gc.turn >= turn)
                        events
                    else
                        Vector(this)
                }
        }
    }

    final case class DelayTime(override val entityId: Long, duration: Duration, events: Vector[Event]) extends Event {
        override def applyTo(entity: Entity)(implicit gc: GameContext): EventResponse = {
            EventResponse()
                .withEntity(entity)
                .withEvent {
                    ScheduleTime(entityId, gc.timestamp + duration, events)
                }
        }
    }

    final case class DelayTurn(override val entityId: Long, turns: Int, events: Vector[Event]) extends Event {
        override def applyTo(entity: Entity)(implicit gc: GameContext): EventResponse = {
            EventResponse()
                .withEntity(entity)
                .withEvent {
                    ScheduleTurn(entityId, gc.turn + turns, events)
                }
        }
    }

}

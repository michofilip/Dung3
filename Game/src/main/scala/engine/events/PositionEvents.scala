package engine.events

import engine.GameContext
import engine.entity.Entity
import engine.entity.EntityServices._
import engine.entity.parts.position.PositionMappers._
import engine.entity.parts.position.{Direction, PositionMappers}
import engine.entity.parts.state.State
import engine.entity.parts.state.StateMappers._
import engine.temporal.Duration

object PositionEvents {

    final case class MoveTo(override val entityId: Long, x: Int, y: Int) extends Event {
        override def applyTo(entity: Entity)(implicit gc: GameContext): EventResponse = {
            EventResponse().withEntity {
                entity.updatePosition(PositionMappers.moveTo(x, y), gc.timestamp)
            }
        }
    }

    final case class MoveBy(override val entityId: Long, dx: Int, dy: Int) extends Event {
        override def applyTo(entity: Entity)(implicit gc: GameContext): EventResponse = {
            EventResponse().withEntity {
                entity.updatePosition(PositionMappers.moveBy(dx, dy), gc.timestamp)
            }
        }
    }

    final case class Step(override val entityId: Long, direction: Direction) extends Event {
        override def applyTo(entity: Entity)(implicit gc: GameContext): EventResponse = {
            EventResponse()
                .withEntity {
                    entity.getState match {
                        case Some(State.Standing) => entity
                            .updatePosition(step(direction) andThen rotateTo(direction), gc.timestamp)
                            .updateState(movement, gc.timestamp)
                            .updateAnimation()
                        case _ => entity
                    }
                }
                .withEvent {
                    entity.getAnimationDuration match {
                        case Some(duration) if duration > Duration.zero =>
                            ControlEvents.DelayTime(entityId, duration, Vector(FinishMovement(entityId)))
                        case _ => FinishMovement(entityId)
                    }
                }
        }
    }

    final case class FinishMovement(override val entityId: Long) extends Event {
        override def applyTo(entity: Entity)(implicit gc: GameContext): EventResponse = {
            EventResponse().withEntity {
                entity.getState match {
                    case Some(State.Walking) => entity
                        .updateState(movement, gc.timestamp)
                        .updateAnimation()
                    case _ => entity
                }
            }
        }
    }

}

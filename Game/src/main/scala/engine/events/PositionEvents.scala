package engine.events

import engine.GameContext
import engine.entity.Entity
import engine.entity.EntityServices._
import engine.entity.parts.position.{Direction, PositionTransformer}
import engine.entity.parts.state.{State, StateTransformer}
import engine.events.Event._
import engine.temporal.Duration

object PositionEvents {

    final case class MoveTo(override val entityId: Long, x: Int, y: Int) extends Event {
        override def applyTo(entity: Entity)(implicit gc: GameContext): EventResponse = {
            val responseEntity = entity.updatePosition(PositionTransformer.moveTo(x, y))
            (Vector(responseEntity), Vector.empty)
        }
    }

    final case class MoveBy(override val entityId: Long, dx: Int, dy: Int) extends Event {
        override def applyTo(entity: Entity)(implicit gc: GameContext): EventResponse = {
            val responseEntity = entity.updatePosition(PositionTransformer.moveBy(dx, dy))
            (Vector(responseEntity), Vector.empty)
        }
    }

    final case class Step(override val entityId: Long, direction: Direction) extends Event {
        override def applyTo(entity: Entity)(implicit gc: GameContext): EventResponse = {

            val responseEntity = entity.getState match {
                case Some(State.Standing) => entity
                    .updatePosition(PositionTransformer.rotateTo(direction) andThen PositionTransformer.step(direction))
                    .updateState(StateTransformer.movementStateTransformer)
                    .updateAnimation()
                case _ => entity
            }

            val responseEvent = entity.getAnimationDuration match {
                case Some(duration) if duration > Duration.zero =>
                    ControlEvents.DelayTime(entityId, duration, Vector(FinishMovement(entityId)))
                case _ => FinishMovement(entityId)
            }

            (Vector(responseEntity), Vector(responseEvent))
        }
    }

    final case class FinishMovement(override val entityId: Long) extends Event {
        override def applyTo(entity: Entity)(implicit gc: GameContext): EventResponse = {
            val responseEntity = entity.getState match {
                case Some(State.Walking) => entity
                    .updateState(StateTransformer.movementStateTransformer)
                    .updateAnimation()
                case _ => entity
            }

            (Vector(responseEntity), Vector.empty)
        }
    }

}

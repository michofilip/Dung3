package engine.entity

import engine.GameContext
import engine.entity.parts.animation.{Animation, AnimationContainer, Frame}
import engine.entity.parts.physics.{Physics, PhysicsContainer}
import engine.entity.parts.position.{Coordinates, Direction, Position, PositionContainer, PositionTransformer}
import engine.entity.parts.script.{Script, ScriptContainer}
import engine.entity.parts.state.{State, StateContainer, StateTransformer}
import engine.entity.parts.value.ValueContainer
import engine.temporal.{Duration, Timestamp}
import engine.value.Value
import engine.value.basic.{BooleanValue, ByteValue, CharValue, DoubleValue, FloatValue, IntValue, LongValue, ShortValue, StringValue}

object EntityServices {

    implicit class StateService(entity: Entity) {
        def setStateContainerOpt(stateContainerOpt: Option[StateContainer]): Entity =
            entity.copy(stateContainerOpt = stateContainerOpt)

        def updateState(stateMapper: StateTransformer)(implicit gc: GameContext): Entity =
            entity.setStateContainerOpt(entity.stateContainerOpt.map(StateContainer.update(stateMapper, gc.timestamp)))

        def getState: Option[State] =
            entity.stateContainerOpt.map(_.state)

        def getStateTimestamp: Option[Timestamp] =
            entity.stateContainerOpt.map(_.stateTimestamp)
    }

    implicit class PositionService(entity: Entity) {
        def setPositionContainerOpt(positionContainerOpt: Option[PositionContainer]): Entity =
            entity.copy(positionContainerOpt = positionContainerOpt)

        def updatePosition(positionMapper: PositionTransformer)(implicit gc: GameContext): Entity =
            entity.setPositionContainerOpt(entity.positionContainerOpt.map(PositionContainer.update(positionMapper, gc.timestamp)))

        def getPosition: Option[Position] =
            entity.positionContainerOpt.map(_.position)

        def getCoordinates: Option[Coordinates] =
            getPosition.map(_.coordinates)

        def getDirection: Option[Direction] =
            getPosition.map(_.direction)

        def getPositionTimestamp: Option[Timestamp] =
            entity.positionContainerOpt.map(_.positionTimestamp)
    }

    implicit class PhysicsService(entity: Entity) {
        def setPhysicsContainerOpt(physicsContainerOpt: Option[PhysicsContainer]): Entity =
            entity.copy(physicsContainerOpt = physicsContainerOpt)

        def updatePhysics(): Entity =
            entity.setPhysicsContainerOpt {
                entity.physicsContainerOpt.map {
                    PhysicsContainer.update(entity.getState)
                }
            }

        def getPhysics: Option[Physics] =
            entity.physicsContainerOpt.map(_.physics)

        def isSolid: Option[Boolean] =
            getPhysics.map(_.solid)

        def isOpaque: Option[Boolean] =
            getPhysics.map(_.opaque)
    }

    implicit class AnimationService(entity: Entity) {
        def setAnimationContainerOpt(animationContainerOpt: Option[AnimationContainer]): Entity =
            entity.copy(animationContainerOpt = animationContainerOpt)

        def updateAnimation(): Entity = {
            val animationTimestamp = entity.getStateTimestamp.getOrElse(entity.initialTimestamp)

            entity.setAnimationContainerOpt {
                entity.animationContainerOpt.map {
                    AnimationContainer.selectAnimationFor(entity.getState, entity.getDirection, animationTimestamp)
                }
            }
        }

        def getAnimation: Option[Animation] =
            entity.animationContainerOpt.map(_.animation)

        def getAnimationDuration: Option[Duration] =
            entity.animationContainerOpt.map(_.animation.duration)

        def getAnimationTimestamp: Option[Timestamp] =
            entity.animationContainerOpt.map(_.animationTimestamp)

        def getFrame(timestamp: Timestamp): Option[Frame] =
            entity.animationContainerOpt.map(_.getFrame(timestamp))
    }

    implicit class ValueService(entity: Entity) {
        def setValueContainer(valueContainerOpt: Option[ValueContainer]): Entity =
            entity.copy(valueContainerOpt = valueContainerOpt)

        def setValue(name: String, value: Value[_]): Entity =
            entity.valueContainerOpt match {
                case Some(valueContainer) => setValueContainer(Some(valueContainer.setValue(name, value)))
                case None => entity
            }

        def removeValue(name: String): Entity =
            entity.valueContainerOpt match {
                case Some(valueContainer) => setValueContainer(Some(valueContainer.removeValue(name)))
                case None => entity
            }

        def getBooleanValue(name: String): Option[BooleanValue] =
            entity.valueContainerOpt.map(_.getBooleanValue(name))

        def getByteValue(name: String): Option[ByteValue] =
            entity.valueContainerOpt.map(_.getByteValue(name))

        def getShortValue(name: String): Option[ShortValue] =
            entity.valueContainerOpt.map(_.getShortValue(name))

        def getIntValue(name: String): Option[IntValue] =
            entity.valueContainerOpt.map(_.getIntValue(name))

        def getLongValue(name: String): Option[LongValue] =
            entity.valueContainerOpt.map(_.getLongValue(name))

        def getFloatValue(name: String): Option[FloatValue] =
            entity.valueContainerOpt.map(_.getFloatValue(name))

        def getDoubleValue(name: String): Option[DoubleValue] =
            entity.valueContainerOpt.map(_.getDoubleValue(name))

        def getCharValue(name: String): Option[CharValue] =
            entity.valueContainerOpt.map(_.getCharValue(name))

        def getStringValue(name: String): Option[StringValue] =
            entity.valueContainerOpt.map(_.getStringValue(name))
    }

    implicit class ScriptService(entity: Entity) {
        def setScriptContainer(scriptContainer: Option[ScriptContainer]): Entity =
            entity.copy(scriptContainerOpt = scriptContainer)

        def getScript(scriptName: String): Option[Script] =
            entity.scriptContainerOpt.flatMap(_.getScript(scriptName))
    }

}

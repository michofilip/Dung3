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
        def setStateContainer(stateContainer: Option[StateContainer]): Entity =
            entity.copy(stateContainer = stateContainer)

        def updateState(stateMapper: StateTransformer)(implicit gc: GameContext): Entity =
            entity.setStateContainer(entity.stateContainer.map(StateContainer.update(stateMapper, gc.timestamp)))

        def getState: Option[State] =
            entity.stateContainer.map(_.state)

        def getStateTimestamp: Option[Timestamp] =
            entity.stateContainer.map(_.stateTimestamp)
    }

    implicit class PositionService(entity: Entity) {
        def setPositionContainer(positionContainer: Option[PositionContainer]): Entity =
            entity.copy(positionContainer = positionContainer)

        def updatePosition(positionMapper: PositionTransformer)(implicit gc: GameContext): Entity =
            entity.setPositionContainer(entity.positionContainer.map(PositionContainer.update(positionMapper, gc.timestamp)))

        def getPosition: Option[Position] =
            entity.positionContainer.map(_.position)

        def getCoordinates: Option[Coordinates] =
            getPosition.map(_.coordinates)

        def getDirection: Option[Direction] =
            getPosition.map(_.direction)

        def getPositionTimestamp: Option[Timestamp] =
            entity.positionContainer.map(_.positionTimestamp)
    }

    implicit class PhysicsService(entity: Entity) {
        def setPhysicsContainer(physicsContainer: Option[PhysicsContainer]): Entity =
            entity.copy(physicsContainer = physicsContainer)

        def updatePhysics(): Entity =
            entity.setPhysicsContainer {
                entity.physicsContainer.map {
                    PhysicsContainer.update(entity.getState)
                }
            }

        def getPhysics: Option[Physics] =
            entity.physicsContainer.map(_.physics)

        def isSolid: Option[Boolean] =
            getPhysics.map(_.solid)

        def isOpaque: Option[Boolean] =
            getPhysics.map(_.opaque)
    }

    implicit class AnimationService(entity: Entity) {
        def setAnimationContainer(animationContainer: Option[AnimationContainer]): Entity =
            entity.copy(animationContainer = animationContainer)

        def updateAnimation(): Entity = {
            val animationTimestamp = entity.getStateTimestamp.getOrElse(entity.initialTimestamp)

            entity.setAnimationContainer {
                entity.animationContainer.map {
                    AnimationContainer.selectAnimationFor(entity.getState, entity.getDirection, animationTimestamp)
                }
            }
        }

        def getAnimation: Option[Animation] =
            entity.animationContainer.map(_.animation)

        def getAnimationDuration: Option[Duration] =
            entity.animationContainer.map(_.animation.duration)

        def getAnimationTimestamp: Option[Timestamp] =
            entity.animationContainer.map(_.animationTimestamp)

        def getFrame(timestamp: Timestamp): Option[Frame] =
            entity.animationContainer.map(_.getFrame(timestamp))
    }

    implicit class ValueService(entity: Entity) {
        def setValueContainer(valueContainer: Option[ValueContainer]): Entity =
            entity.copy(valueContainer = valueContainer)

        def setValue(name: String, value: Value[_]): Entity =
            entity.valueContainer match {
                case Some(valueContainer) => setValueContainer(Some(valueContainer.setValue(name, value)))
                case None => entity
            }

        def removeValue(name: String): Entity =
            entity.valueContainer match {
                case Some(valueContainer) => setValueContainer(Some(valueContainer.removeValue(name)))
                case None => entity
            }

        def getBooleanValue(name: String): Option[BooleanValue] =
            entity.valueContainer.map(_.getBooleanValue(name))

        def getByteValue(name: String): Option[ByteValue] =
            entity.valueContainer.map(_.getByteValue(name))

        def getShortValue(name: String): Option[ShortValue] =
            entity.valueContainer.map(_.getShortValue(name))

        def getIntValue(name: String): Option[IntValue] =
            entity.valueContainer.map(_.getIntValue(name))

        def getLongValue(name: String): Option[LongValue] =
            entity.valueContainer.map(_.getLongValue(name))

        def getFloatValue(name: String): Option[FloatValue] =
            entity.valueContainer.map(_.getFloatValue(name))

        def getDoubleValue(name: String): Option[DoubleValue] =
            entity.valueContainer.map(_.getDoubleValue(name))

        def getCharValue(name: String): Option[CharValue] =
            entity.valueContainer.map(_.getCharValue(name))

        def getStringValue(name: String): Option[StringValue] =
            entity.valueContainer.map(_.getStringValue(name))
    }

    implicit class ScriptService(entity: Entity) {
        def setScriptContainer(scriptContainer: Option[ScriptContainer]): Entity =
            entity.copy(scriptContainer = scriptContainer)

        def getScript(scriptName: String): Option[Script] =
            entity.scriptContainer.flatMap(_.getScript(scriptName))
    }

}

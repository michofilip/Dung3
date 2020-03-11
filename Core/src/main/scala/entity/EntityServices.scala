package entity

import commons.temporal.{Duration, Timestamp}
import model.animation.{Animation, AnimationContainer, Frame}
import model.physics.{Physics, PhysicsContainer}
import model.position.PositionMappers.PositionMapper
import model.position.{Coordinates, Direction, Position, PositionContainer}
import model.script.{Script, ScriptContainer}
import model.state.StateMappers.StateMapper
import model.state.{State, StateContainer}
import model.value.ValueContainer
import value.Value
import value.basic.{BooleanValue, ByteValue, CharValue, DoubleValue, FloatValue, IntValue, LongValue, ShortValue, StringValue}

object EntityServices {
    
    implicit class StateService(entity: Entity) {
        def setStateContainerOpt(stateContainerOpt: Option[StateContainer]): Entity =
            entity.copy(stateContainerOpt = stateContainerOpt)
        
        def updateState(stateMapper: StateMapper, timestamp: Timestamp): Entity =
            entity.setStateContainerOpt(entity.stateContainerOpt.map(StateContainer.update(stateMapper, timestamp)))
        
        def getState: Option[State] =
            entity.stateContainerOpt.map(_.state)
        
        def getStateTimestamp: Option[Timestamp] =
            entity.stateContainerOpt.map(_.stateTimestamp)
    }
    
    implicit class PositionService(entity: Entity) {
        def setPositionContainerOpt(positionContainerOpt: Option[PositionContainer]): Entity =
            entity.copy(positionContainerOpt = positionContainerOpt)
        
        def updatePosition(positionMapper: PositionMapper, timestamp: Timestamp): Entity =
            entity.setPositionContainerOpt(entity.positionContainerOpt.map(PositionContainer.update(positionMapper, timestamp)))
        
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
        
        def getValue(name: String): Option[Value] =
            entity.valueContainerOpt.map(_.getValue(name))
        
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

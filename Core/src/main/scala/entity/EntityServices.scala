package entity

import commons.temporal.{Duration, Timestamp}
import model.animation.{Animation, AnimationContainer, Frame}
import model.physics.{Physics, PhysicsContainer}
import model.position.PositionMappers.PositionMapper
import model.position.{Coordinates, Direction, Position, PositionContainer}
import model.state.StateMappers.StateMapper
import model.state.{State, StateContainer}

object EntityServices {
    
    implicit class StateService(entity: Entity) {
        def setStateContainer(stateContainer: Option[StateContainer]): Entity =
            entity.copy(stateContainer = stateContainer)
        
        def updateState(stateMapper: StateMapper, timestamp: Timestamp): Entity =
            entity.setStateContainer(entity.stateContainer.map(StateContainer.update(stateMapper, timestamp)))
        
        def getState: Option[State] =
            entity.stateContainer.map(_.state)
        
        def getStateTimestamp: Option[Timestamp] =
            entity.stateContainer.map(_.stateTimestamp)
    }
    
    implicit class PositionService(entity: Entity) {
        def setPositionContainer(positionContainer: Option[PositionContainer]): Entity =
            entity.copy(positionContainer = positionContainer)
        
        def updatePosition(positionMapper: PositionMapper, timestamp: Timestamp): Entity =
            entity.setPositionContainer(entity.positionContainer.map(PositionContainer.update(positionMapper, timestamp)))
        
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
    
}

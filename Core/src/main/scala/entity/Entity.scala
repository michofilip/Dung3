package entity

import commons.temporal.Timestamp
import model.graphics.{Animation, AnimationContainer, Frame}
import model.physics.{Physics, PhysicsContainer}
import model.position.PositionMappers.PositionMapper
import model.position.{Coordinates, Direction, Position, PositionContainer}
import model.state.StateMappers.StateMapper
import model.state.{State, StateContainer}

case class Entity(id: Long, name: String, initialTimestamp: Timestamp,
                  stateContainerOpt: Option[StateContainer] = None,
                  positionContainerOpt: Option[PositionContainer] = None,
                  physicsContainerOpt: Option[PhysicsContainer] = None,
                  animationContainerOpt: Option[AnimationContainer] = None
                 )

object Entity {
    
    implicit class StateService(entity: Entity) {
        def setStateContainer(stateContainer: StateContainer): Entity = entity.copy(stateContainerOpt = Some(stateContainer))
        
        def removeStateContainer(): Entity = entity.copy(stateContainerOpt = None)
        
        def updateState(stateMapper: StateMapper, timestamp: Timestamp): Entity =
            entity.stateContainerOpt
                    .map(StateContainer.update(stateMapper, timestamp))
                    .map(setStateContainer)
                    .getOrElse(entity)
        
        def getState: Option[State] = entity.stateContainerOpt.map(_.state)
        
        def getStateTimestamp: Option[Timestamp] = entity.stateContainerOpt.map(_.stateTimestamp)
    }
    
    implicit class PositionService(entity: Entity) {
        def setPositionContainer(position: PositionContainer): Entity = entity.copy(positionContainerOpt = Some(position))
        
        def removePositionContainer(): Entity = entity.copy(positionContainerOpt = None)
        
        def updatePosition(positionMapper: PositionMapper, timestamp: Timestamp): Entity =
            entity.positionContainerOpt
                    .map(PositionContainer.update(positionMapper, timestamp))
                    .map(setPositionContainer)
                    .getOrElse(entity)
        
        def getCoordinates: Option[Coordinates] = entity.positionContainerOpt.map(_.position.coordinates)
        
        def getDirection: Option[Direction] = entity.positionContainerOpt.map(_.position.direction)
        
        def getPosition: Option[Position] = entity.positionContainerOpt.map(_.position)
        
        def getPositionTimestamp: Option[Timestamp] = entity.positionContainerOpt.map(_.positionTimestamp)
    }
    
    implicit class PhysicsService(entity: Entity) {
        def setPhysicsContainer(physicsContainer: PhysicsContainer): Entity = entity.copy(physicsContainerOpt = Some(physicsContainer))
        
        def removePhysicsContainer(): Entity = entity.copy(physicsContainerOpt = None)
        
        def updatePhysics(): Entity =
            entity.physicsContainerOpt
                    .map(PhysicsContainer.updatePhysics(entity))
                    .map(setPhysicsContainer)
                    .getOrElse(entity)
        
        def getPhysics: Option[Physics] = entity.physicsContainerOpt.map(_.physics)
        
        def isSolid: Option[Boolean] = entity.physicsContainerOpt.map(_.physics.solid)
        
        def isOpaque: Option[Boolean] = entity.physicsContainerOpt.map(_.physics.opaque)
    }
    
    implicit class AnimationService(entity: Entity) {
        def setAnimationContainer(graphics: AnimationContainer): Entity = entity.copy(animationContainerOpt = Some(graphics))
        
        def removeAnimationContainer(): Entity = entity.copy(animationContainerOpt = None)
        
        def updateAnimation(): Entity =
            entity.animationContainerOpt
                    .map(AnimationContainer.selectAnimation(entity))
                    .map(setAnimationContainer)
                    .getOrElse(entity)
        
        def getAnimation: Option[Animation] = entity.animationContainerOpt.map(_.animation)
        
        def getAnimationTimestamp: Option[Timestamp] = entity.animationContainerOpt.map(_.animationTimestamp)
        
        def getFrame(timestamp: Timestamp): Option[Frame] = entity.animationContainerOpt.map(_.getFrame(timestamp))
    }
    
}
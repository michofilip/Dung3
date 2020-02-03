package entity

import commons.temporal.Timestamp
import model.graphics.AnimationContainer
import model.physics.PhysicsContainer
import model.position.PositionContainer
import model.position.PositionMappers.PositionMapper
import model.state.StateContainer
import model.state.StateMappers.StateMapper

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
    }
    
    implicit class PositionService(entity: Entity) {
        def setPositionContainer(position: PositionContainer): Entity = entity.copy(positionContainerOpt = Some(position))
        
        def removePositionContainer(): Entity = entity.copy(positionContainerOpt = None)
        
        def updatePosition(positionMapper: PositionMapper, timestamp: Timestamp): Entity =
            entity.positionContainerOpt
                    .map(PositionContainer.update(positionMapper, timestamp))
                    .map(setPositionContainer)
                    .getOrElse(entity)
    }
    
    implicit class PhysicsService(entity: Entity) {
        def setPhysicsContainer(physicsContainer: PhysicsContainer): Entity = entity.copy(physicsContainerOpt = Some(physicsContainer))
        
        def removePhysicsContainer(): Entity = entity.copy(physicsContainerOpt = None)
        
        def updatePhysics(): Entity =
            entity.physicsContainerOpt
                    .map(PhysicsContainer.updatePhysics(entity))
                    .map(setPhysicsContainer)
                    .getOrElse(entity)
    }
    
    implicit class AnimationService(entity: Entity) {
        def setAnimationContainer(graphics: AnimationContainer): Entity = entity.copy(animationContainerOpt = Some(graphics))
        
        def removeAnimationContainer(): Entity = entity.copy(animationContainerOpt = None)
        
        def updateAnimation(): Entity =
            entity.animationContainerOpt
                    .map(AnimationContainer.selectGraphics(entity))
                    .map(setAnimationContainer)
                    .getOrElse(entity)
    }
    
}
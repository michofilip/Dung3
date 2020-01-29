package entity

import commons.temporal.Timestamp
import model.graphics.Graphics
import model.physics.PhysicsContainer
import model.position.PositionContainer
import model.position.PositionMappers.PositionMapper
import model.state.{State, StateContainer}

case class Entity(id: Long, name: String, initialTimestamp: Timestamp,
                  stateContainerOpt: Option[StateContainer] = None,
                  positionContainerOpt: Option[PositionContainer] = None,
                  physicsContainerOpt: Option[PhysicsContainer] = None,
                  graphicsOpt: Option[Graphics] = None
                 )

object Entity {
    
    implicit class StateService(entity: Entity) {
        def setState(stateContainer: StateContainer): Entity = entity.copy(stateContainerOpt = Some(stateContainer))
        
        def removeState(): Entity = entity.copy(stateContainerOpt = None)
        
        def updateState(stateMapper: State => State, timestamp: Timestamp): Entity =
            entity.stateContainerOpt
                    .map(StateContainer.update(stateMapper, timestamp))
                    .map(setState)
                    .getOrElse(entity)
    }
    
    implicit class PositionService(entity: Entity) {
        def setPositionContainer(position: PositionContainer): Entity = entity.copy(positionContainerOpt = Some(position))
        
        def removePositionContainer(): Entity = entity.copy(positionContainerOpt = None)
        
        def updatePositionContainer(positionMapper: PositionMapper, timestamp: Timestamp): Entity =
            entity.positionContainerOpt
                    .map(PositionContainer.update(positionMapper, timestamp))
                    .map(setPositionContainer)
                    .getOrElse(entity)
    }
    
    implicit class PhysicsService(entity: Entity) {
        def setPhysicsContainer(physicsContainer: PhysicsContainer): Entity = entity.copy(physicsContainerOpt = Some(physicsContainer))
        
        def removePhysicsContainer(): Entity = entity.copy(physicsContainerOpt = None)
        
        def selectPhysics(): Entity =
            entity.physicsContainerOpt
                    .map(PhysicsContainer.selectPhysics(entity))
                    .map(setPhysicsContainer)
                    .getOrElse(entity)
    }
    
    implicit class GraphicsService(entity: Entity) {
        def setGraphics(graphics: Graphics): Entity = entity.copy(graphicsOpt = Some(graphics))
        
        def removeGraphics(): Entity = entity.copy(graphicsOpt = None)
        
        def selectAnimation(): Entity =
            entity.graphicsOpt
                    .map(Graphics.selectGraphics(entity))
                    .map(setGraphics)
                    .getOrElse(entity)
    }
    
}
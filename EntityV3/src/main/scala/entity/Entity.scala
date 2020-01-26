package entity

import commons.temporal.Timestamp
import model.graphics.Graphics
import model.physics.PhysicsContainer
import model.position.{Coordinates, Direction, Position}
import model.state.{State, StateContainer}

case class Entity(id: Long, name: String, initialTimestamp: Timestamp,
                  stateContainerOpt: Option[StateContainer] = None,
                  positionOpt: Option[Position] = None,
                  physicsContainerOpt: Option[PhysicsContainer] = None,
                  graphicsOpt: Option[Graphics] = None
                 ) {
    def setState(stateContainer: StateContainer): Entity = copy(stateContainerOpt = Some(stateContainer))
    
    def setPosition(position: Position): Entity = copy(positionOpt = Some(position))
    
    def setPhysicsContainer(physicsContainer: PhysicsContainer): Entity = copy(physicsContainerOpt = Some(physicsContainer))
    
    def setGraphics(graphics: Graphics): Entity = copy(graphicsOpt = Some(graphics))
    
    
    def removeState(): Entity = copy(stateContainerOpt = None)
    
    def removePosition(): Entity = copy(positionOpt = None)
    
    def removePhysicsContainer(): Entity = copy(physicsContainerOpt = None)
    
    def removeGraphics(): Entity = copy(graphicsOpt = None)
    
    
    def updateState(stateMapper: State => State, timestamp: Timestamp): Entity =
        stateContainerOpt
                .map(StateContainer.update(stateMapper, timestamp))
                .map(setState)
                .getOrElse(this)
    
    def updatePosition(positionMapper: (Coordinates, Direction) => (Coordinates, Direction), timestamp: Timestamp): Entity =
        positionOpt
                .map(Position.update(positionMapper, timestamp))
                .map(setPosition)
                .getOrElse(this)
    
    
    def selectPhysics(): Entity =
        physicsContainerOpt
                .map(PhysicsContainer.selectPhysics(this))
                .map(setPhysicsContainer)
                .getOrElse(this)
    
    def selectAnimation(): Entity =
        graphicsOpt
                .map(Graphics.selectGraphics(this))
                .map(setGraphics)
                .getOrElse(this)
    
}
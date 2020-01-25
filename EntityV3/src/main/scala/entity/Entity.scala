package entity

import commons.temporal.Timestamp
import model.graphics.Graphics
import model.physics.PhysicsContainer
import model.position.{Coordinates, Direction, Position}
import model.state.{State, StateContainer}

case class Entity(id: Long, name: String, initialTimestamp: Timestamp,
                  stateContainerOpt: Option[StateContainer] = None,
                  positionOpt: Option[Position],
                  physicsContainerOpt: Option[PhysicsContainer],
                  graphicsOpt: Option[Graphics]
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
                .map(_.updateState(stateMapper, timestamp))
                .map(setState)
                .getOrElse(this)
    
    //todo fix it
    def updateCoordinates(coordinatesMapper: Coordinates => Coordinates, timestamp: Timestamp): Entity =
        positionOpt
                .map(position => Position(coordinatesMapper(position.coordinates), position.direction, timestamp))
                .map(setPosition)
                .getOrElse(this)
    
    //todo fix it
    def updateDirection(directionMapper: Direction => Direction, timestamp: Timestamp): Entity =
        positionOpt
                .map(position => Position(position.coordinates, directionMapper(position.direction), timestamp))
                .map(setPosition)
                .getOrElse(this)
    
    
    def selectPhysics(): Entity =
        physicsContainerOpt
                .map(_.select(this))
                .map(setPhysicsContainer)
                .getOrElse(this)
    
    def selectAnimation(): Entity =
        graphicsOpt
                .map(_.select(this))
                .map(setGraphics)
                .getOrElse(this)
    
}
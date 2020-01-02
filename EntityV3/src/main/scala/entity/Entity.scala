package entity

import commons.temporal.Timestamp
import model.graphics.Graphics
import model.physics.PhysicsHolder
import model.position.{Coordinates, Direction, Position}
import model.state.{State, StateHolder}

case class Entity(id: Long, name: String, timestamp: Timestamp,
                  stateHolderOpt: Option[StateHolder] = None,
                  positionOpt: Option[Position],
                  physicsHolderOpt: Option[PhysicsHolder],
                  graphicsOpt: Option[Graphics]
                 ) {
    def setState(stateHolder: StateHolder): Entity = copy(stateHolderOpt = Some(stateHolder))
    
    def setPosition(position: Position): Entity = copy(positionOpt = Some(position))
    
    def setPhysicsHolder(physicsHolder: PhysicsHolder): Entity = copy(physicsHolderOpt = Some(physicsHolder))
    
    def setGraphics(graphics: Graphics): Entity = copy(graphicsOpt = Some(graphics))
    
    
    def removeState(): Entity = copy(stateHolderOpt = None)
    
    def removePosition(): Entity = copy(positionOpt = None)
    
    def removePhysicsHolder(): Entity = copy(physicsHolderOpt = None)
    
    def removeGraphics(): Entity = copy(graphicsOpt = None)
    
    
    def updateState(stateMapper: State => State, timestamp: Timestamp): Entity =
        stateHolderOpt.map(stateHolder => StateHolder(stateMapper(stateHolder.state), timestamp))
                .map(setState).getOrElse(this)
    
    def updateCoordinates(coordinatesMapper: Coordinates => Coordinates, timestamp: Timestamp): Entity =
        positionOpt.map(position => Position(coordinatesMapper(position.coordinates), position.direction, timestamp))
                .map(setPosition).getOrElse(this)
    
    def updateDirection(directionMapper: Direction => Direction, timestamp: Timestamp): Entity =
        positionOpt.map(position => Position(position.coordinates, directionMapper(position.direction), timestamp))
                .map(setPosition).getOrElse(this)
    
    
    def selectPhysics(timestamp: Timestamp): Entity =
        physicsHolderOpt.map(_.select(stateHolderOpt, timestamp))
                .map(setPhysicsHolder).getOrElse(this)
    
    def selectAnimation(): Entity =
        graphicsOpt.map(_.select(stateHolderOpt, positionOpt, timestamp))
                .map(setGraphics).getOrElse(this)
    
}
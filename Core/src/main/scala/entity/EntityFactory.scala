package entity

import commons.temporal.Timestamp
import entity.EntityServices._
import model.NameKey
import model.physics.PhysicsContainer
import model.position.{Position, PositionContainer}
import model.state.{State, StateContainer}
import repository.PhysicsSelectorRepository

class EntityFactory(implicit physicsSelectorRepository: PhysicsSelectorRepository) {
    
    def makePlayer(id: Long, timestamp: Timestamp, position: Position): Entity = {
        val nameKey = NameKey.Player
        val state = State.Standing
        
        val stateContainer = Some(StateContainer(state, timestamp))
        val positionContainer = Some(PositionContainer(position, timestamp))
        val physicsContainer = PhysicsContainer(Some(state), physicsSelectorRepository.getPhysicsSelector(nameKey))
        
        Entity(id = id, nameKey = nameKey, initialTimestamp = timestamp)
                .setStateContainer(stateContainer)
                .setPositionContainer(positionContainer)
                .setPhysicsContainer(physicsContainer)
                .updatePhysics()
                .updateAnimation()
    }
}

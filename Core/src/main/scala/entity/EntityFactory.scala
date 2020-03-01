package entity

import commons.temporal.Timestamp
import entity.EntityServices._
import model.NameKey
import model.animation.AnimationContainer
import model.physics.PhysicsContainer
import model.position.{Position, PositionContainer}
import model.state.{State, StateContainer}
import repository.{AnimationSelectorRepository, PhysicsSelectorRepository}

class EntityFactory(implicit physicsSelectorRepository: PhysicsSelectorRepository,
                    animationSelectorRepository: AnimationSelectorRepository) {
    
    def makePlayer(id: Long, timestamp: Timestamp, position: Position): Entity = {
        val nameKey = NameKey.Player
        val state = State.Standing
        
        val stateContainer = Some(StateContainer(state, timestamp))
        val positionContainer = Some(PositionContainer(position, timestamp))
        val physicsContainer = PhysicsContainer(Some(state), physicsSelectorRepository.getPhysicsSelector(nameKey))
        val animationContainer = AnimationContainer(Some(state), Some(position.direction), timestamp, animationSelectorRepository.getAnimationSelector(nameKey))
        
        Entity(id = id, nameKey = nameKey, initialTimestamp = timestamp)
                .setStateContainer(stateContainer)
                .setPositionContainer(positionContainer)
                .setPhysicsContainer(physicsContainer)
                .setAnimationContainer(animationContainer)
                .updatePhysics()
                .updateAnimation()
    }
}

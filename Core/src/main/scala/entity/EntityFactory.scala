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
        
        val stateContainerOpt = Some(StateContainer(state, timestamp))
        val positionContainerOpt = Some(PositionContainer(position, timestamp))
        val physicsContainerOpt = physicsSelectorRepository.getPhysicsSelector(nameKey)
                .flatMap(PhysicsContainer.initialise(Some(state)))
        val animationContainerOpt = animationSelectorRepository.getAnimationSelector(nameKey)
                .flatMap(AnimationContainer.initialize(Some(state), Some(position.direction), timestamp))
        
        Entity(id = id, nameKey = nameKey, initialTimestamp = timestamp)
                .setStateContainerOpt(stateContainerOpt)
                .setPositionContainerOpt(positionContainerOpt)
                .setPhysicsContainerOpt(physicsContainerOpt)
                .setAnimationContainerOpt(animationContainerOpt)
                .updatePhysics()
                .updateAnimation()
    }
}

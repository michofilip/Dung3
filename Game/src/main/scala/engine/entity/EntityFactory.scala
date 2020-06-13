package engine.entity

import engine.entity.EntityServices._
import engine.entity.parts.NameKey
import engine.entity.parts.animation.AnimationContainer
import engine.entity.parts.physics.PhysicsContainer
import engine.entity.parts.position.{Position, PositionContainer}
import engine.entity.parts.state.{State, StateContainer}
import engine.repository.{AnimationSelectorRepository, PhysicsSelectorRepository}
import engine.temporal.Timestamp

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

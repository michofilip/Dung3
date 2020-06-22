package engine.entity.parts.physics

import engine.entity.parts.state.State

case class PhysicsContainer(physics: Physics, physicsSelector: PhysicsSelector)

object PhysicsContainer {
    
    def initialise(state: Option[State])(physicsSelector: PhysicsSelector): Option[PhysicsContainer] =
        physicsSelector.select(state)
                .map(physics => PhysicsContainer(physics, physicsSelector))
    
    def update(state: Option[State])(physicsContainer: PhysicsContainer): PhysicsContainer =
        physicsContainer.physicsSelector.select(state)
                .map(physics => physicsContainer.copy(physics = physics))
                .getOrElse(physicsContainer)
    
}

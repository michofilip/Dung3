package engine.entity.parts.physics

import engine.entity.parts.state.State

case class PhysicsContainer(physics: Physics, physicsSelector: PhysicsSelector)

object PhysicsContainer {
    
    def initialise(stateOpt: Option[State])(physicsSelector: PhysicsSelector): Option[PhysicsContainer] =
        physicsSelector.select(stateOpt)
                .map(physics => PhysicsContainer(physics, physicsSelector))
    
    def update(stateOpt: Option[State])(physicsContainer: PhysicsContainer): PhysicsContainer =
        physicsContainer.physicsSelector.select(stateOpt)
                .map(physics => physicsContainer.copy(physics = physics))
                .getOrElse(physicsContainer)
    
}

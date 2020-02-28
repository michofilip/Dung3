package model.physics

import entity.Entity
import model.state.State

case class PhysicsContainer(physics: Physics, physicsSelector: PhysicsSelector)

object PhysicsContainer {
    def apply(stateOpt: Option[State], physicsSelectorOpt: Option[PhysicsSelector]): Option[PhysicsContainer] =
        physicsSelectorOpt.flatMap { physicsSelector =>
            physicsSelector.select(stateOpt)
                    .map(physics => PhysicsContainer(physics, physicsSelector))
        }
    
    def selectPhysics(entity: Entity)(physicsContainer: PhysicsContainer): PhysicsContainer = {
        val stateOpt = entity.stateContainer.map(_.state)
        
        physicsContainer.physicsSelector.select(stateOpt)
                .map(physics => PhysicsContainer(physics, physicsContainer.physicsSelector))
                .getOrElse(physicsContainer)
    }
}

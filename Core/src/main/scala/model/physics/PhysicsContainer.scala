package model.physics

import entity.Entity

case class PhysicsContainer(physics: Physics, physicsSelector: PhysicsSelector)

object PhysicsContainer {
    def updatePhysics(entity: Entity)(physicsContainer: PhysicsContainer): PhysicsContainer = {
        val stateOpt = entity.stateContainerOpt.map(_.state)
        
        physicsContainer.physicsSelector.select(stateOpt)
                .map(physics => PhysicsContainer(physics, physicsContainer.physicsSelector))
                .getOrElse(physicsContainer)
    }
}

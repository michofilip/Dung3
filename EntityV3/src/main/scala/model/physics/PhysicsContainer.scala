package model.physics

import entity.Entity

case class PhysicsContainer(physics: Physics, physicsSelector: PhysicsSelector) {
    def select(entity: Entity): PhysicsContainer = {
        val stateOpt = entity.stateContainerOpt.map(_.state)
        
        physicsSelector.select(stateOpt)
                .map(physics => PhysicsContainer(physics, physicsSelector))
                .getOrElse(this)
    }
}

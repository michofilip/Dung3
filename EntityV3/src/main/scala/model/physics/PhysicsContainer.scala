package model.physics

import commons.temporal.Timestamp
import model.state.StateHolder

case class PhysicsContainer(physics: Physics, physicsSelector: PhysicsSelector) {
    def select(stateHolderOpt: Option[StateHolder], timestamp: Timestamp): PhysicsContainer =
        physicsSelector.select(stateHolderOpt.map(_.state))
                .map(physics => PhysicsContainer(physics, physicsSelector))
                .getOrElse(this)
}

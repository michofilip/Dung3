package model.physics

import commons.temporal.Timestamp
import model.state.StateHolder

case class PhysicsHolder(physics: Physics, physicsSelector: PhysicsSelector, physicsTimestamp: Timestamp) {
    def select(stateHolderOpt: Option[StateHolder], timestamp: Timestamp): PhysicsHolder =
        physicsSelector.select(stateHolderOpt.map(_.state)).map(physics =>
            PhysicsHolder(physics, physicsSelector, timestamp)).getOrElse(this)
}

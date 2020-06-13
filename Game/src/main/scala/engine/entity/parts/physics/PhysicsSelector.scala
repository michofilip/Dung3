package engine.entity.parts.physics

import engine.entity.parts.state.State

case class PhysicsSelector(physics: Map[Option[State], Physics]) {
    def select(stateOpt: Option[State]): Option[Physics] = physics.get(stateOpt)
}

object PhysicsSelector {
    def apply(physics: (Option[State], Physics)*): PhysicsSelector =
        new PhysicsSelector(physics.toMap)
}

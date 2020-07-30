package engine.entity.parts.physics

import engine.entity.parts.state.State

case class PhysicsSelector(physics: Map[Option[State], Physics]) {
    def select(state: Option[State]): Option[Physics] = physics.get(state)
}

object PhysicsSelector {
    def apply(physics: (Option[State], Physics)*): PhysicsSelector =
        new PhysicsSelector(physics.toMap)
}

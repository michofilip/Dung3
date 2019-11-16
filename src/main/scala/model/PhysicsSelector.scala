package model

case class PhysicsSelector(physics: Map[Option[EntityState], Physics]) {
    def select(stateOpt: Option[EntityState]): Option[Physics] = physics.get(stateOpt)
}

object PhysicsSelector {
    def apply(physics: (Option[EntityState], Physics)*): PhysicsSelector =
        new PhysicsSelector(physics.toMap)
}
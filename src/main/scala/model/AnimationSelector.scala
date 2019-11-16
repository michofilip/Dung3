package model

case class AnimationSelector(animations: Map[(Option[EntityState], Option[Direction]), Animation]) {
    def select(stateOpt: Option[EntityState], directionOpt: Option[Direction]): Option[Animation] =
        animations.get((stateOpt, directionOpt))
}

object AnimationSelector {
    def apply(animations: ((Option[EntityState], Option[Direction]), Animation)*): AnimationSelector =
        new AnimationSelector(animations.toMap)
}

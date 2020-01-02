package model.graphics

import model.position.Direction
import model.state.State

case class AnimationSelector(animations: Map[(Option[State], Option[Direction]), Animation]) {
    def select(stateOpt: Option[State], directionOpt: Option[Direction]): Option[Animation] =
        animations.get((stateOpt, directionOpt))
}

object AnimationSelector {
    def apply(animations: ((Option[State], Option[Direction]), Animation)*): AnimationSelector =
        new AnimationSelector(animations.toMap)
}
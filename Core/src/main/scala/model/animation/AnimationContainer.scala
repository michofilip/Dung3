package model.animation

import commons.temporal.Timestamp
import model.position.Direction
import model.state.State

case class AnimationContainer(animation: Animation, animationSelector: AnimationSelector, animationTimestamp: Timestamp) {
    def getFrame(timestamp: Timestamp): Frame =
        animation.frame(timestamp - animationTimestamp)
}

object AnimationContainer {
    
    def initialize(stateOpt: Option[State], directionOpt: Option[Direction], animationTimestamp: Timestamp)
                  (animationSelector: AnimationSelector): Option[AnimationContainer] =
        animationSelector.select(stateOpt, directionOpt)
                .map(animation => AnimationContainer(animation, animationSelector, animationTimestamp))
    
    def selectAnimationFor(stateOpt: Option[State], directionOpt: Option[Direction], animationTimestamp: Timestamp)
                          (animationContainer: AnimationContainer): AnimationContainer =
        animationContainer.animationSelector.select(stateOpt, directionOpt)
                .map(animation => animationContainer.copy(animation = animation, animationTimestamp = animationTimestamp))
                .getOrElse(animationContainer)
}

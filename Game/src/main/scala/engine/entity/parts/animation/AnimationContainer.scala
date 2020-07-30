package engine.entity.parts.animation

import engine.entity.parts.position.Direction
import engine.entity.parts.state.State
import engine.temporal.Timestamp

case class AnimationContainer(animation: Animation, animationSelector: AnimationSelector, animationTimestamp: Timestamp) {
    def getFrame(timestamp: Timestamp): Frame =
        animation.frame(timestamp - animationTimestamp)
}

object AnimationContainer {

    def initialize(state: Option[State], direction: Option[Direction], animationTimestamp: Timestamp)
                  (animationSelector: AnimationSelector): Option[AnimationContainer] =
        animationSelector.select(state, direction)
            .map(animation => AnimationContainer(animation, animationSelector, animationTimestamp))

    def selectAnimationFor(state: Option[State], direction: Option[Direction], animationTimestamp: Timestamp)
                          (animationContainer: AnimationContainer): AnimationContainer =
        animationContainer.animationSelector.select(state, direction)
            .map(animation => animationContainer.copy(animation = animation, animationTimestamp = animationTimestamp))
            .getOrElse(animationContainer)
}

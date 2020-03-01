package model.animation

import commons.temporal.Timestamp
import entity.Entity
import model.position.Direction
import model.state.State

case class AnimationContainer(animation: Animation, animationSelector: AnimationSelector, animationTimestamp: Timestamp) {
    def getFrame(timestamp: Timestamp): Frame =
        animation.frame(timestamp - animationTimestamp)
}

object AnimationContainer {
    
    def apply(stateOpt: Option[State], directionOpt: Option[Direction], animationTimestamp: Timestamp,
              animationSelectorOpt: Option[AnimationSelector]): Option[AnimationContainer] =
        animationSelectorOpt.flatMap { animationSelector =>
            animationSelector.select(stateOpt, directionOpt)
                    .map(animation => new AnimationContainer(animation, animationSelector, animationTimestamp))
        }
    
    def selectAnimationFor(entity: Entity)(animationContainer: AnimationContainer): AnimationContainer = {
        val stateOpt = entity.stateContainer.map(_.state)
        val directionOpt = entity.positionContainer.map(_.position.direction)
        val animationTimestamp = entity.stateContainer
                .map(_.stateTimestamp)
                .getOrElse(entity.initialTimestamp)
        
        animationContainer.animationSelector.select(stateOpt, directionOpt)
                .map(animation => AnimationContainer(animation, animationContainer.animationSelector, animationTimestamp))
                .getOrElse(animationContainer)
    }
}

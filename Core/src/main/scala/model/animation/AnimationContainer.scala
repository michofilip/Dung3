package model.animation

import commons.temporal.Timestamp
import entity.Entity

case class AnimationContainer(animation: Animation, animationSelector: AnimationSelector, animationTimestamp: Timestamp) {
    def getFrame(timestamp: Timestamp): Frame =
        animation.frame(timestamp - animationTimestamp)
}

object AnimationContainer {
    def selectAnimation(entity: Entity)(animationContainer: AnimationContainer): AnimationContainer = {
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

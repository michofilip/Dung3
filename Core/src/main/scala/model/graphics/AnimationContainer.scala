package model.graphics

import commons.temporal.Timestamp
import entity.Entity

case class AnimationContainer(animation: Animation, animationSelector: AnimationSelector, animationTimestamp: Timestamp) {
    def getFrame(timestamp: Timestamp): Frame =
        animation.frame(timestamp - animationTimestamp)
}

object AnimationContainer {
    def selectAnimation(entity: Entity)(animationContainer: AnimationContainer): AnimationContainer = {
        val stateOpt = entity.stateContainerOpt.map(_.state)
        val directionOpt = entity.positionContainerOpt.map(_.position.direction)
        val animationTimestamp = entity.stateContainerOpt
                .map(_.stateTimestamp)
                .getOrElse(entity.initialTimestamp)
        
        animationContainer.animationSelector.select(stateOpt, directionOpt)
                .map(animation => AnimationContainer(animation, animationContainer.animationSelector, animationTimestamp))
                .getOrElse(animationContainer)
    }
}

package model.graphics

import commons.temporal.Timestamp
import entity.Entity

case class Graphics(animation: Animation, animationSelector: AnimationSelector, animationTimestamp: Timestamp) {
    def select(entity: Entity): Graphics = {
        val stateOpt = entity.stateContainerOpt.map(_.state)
        val directionOpt = entity.positionOpt.map(_.direction)
        val newAnimationTimestamp = entity.stateContainerOpt
                .map(_.stateTimestamp)
                .getOrElse(entity.initialTimestamp)
        
        animationSelector.select(stateOpt, directionOpt)
                .map(animation => Graphics(animation, animationSelector, newAnimationTimestamp))
                .getOrElse(this)
    }
    
    def getFrame(timestamp: Timestamp): Frame =
        animation.frame(timestamp - animationTimestamp)
}

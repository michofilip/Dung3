package model.graphics

import commons.temporal.Timestamp
import entity.Entity

case class Graphics(animation: Animation, animationSelector: AnimationSelector, animationTimestamp: Timestamp) {
    def getFrame(timestamp: Timestamp): Frame =
        animation.frame(timestamp - animationTimestamp)
}

object Graphics {
    def selectGraphics(entity: Entity)(graphics: Graphics): Graphics = {
        val stateOpt = entity.stateContainerOpt.map(_.state)
        val directionOpt = entity.positionContainerOpt.map(_.position.direction)
        val animationTimestamp = entity.stateContainerOpt
                .map(_.stateTimestamp)
                .getOrElse(entity.initialTimestamp)
        
        graphics.animationSelector.select(stateOpt, directionOpt)
                .map(animation => Graphics(animation, graphics.animationSelector, animationTimestamp))
                .getOrElse(graphics)
    }
}

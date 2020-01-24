package model.graphics

import commons.temporal.Timestamp
import model.position.Position
import model.state.StateHolder

case class Graphics(animation: Animation, animationSelector: AnimationSelector, animationTimestamp: Timestamp) {
    def select(stateHolderOpt: Option[StateHolder], positionOpt: Option[Position], timestamp: Timestamp): Graphics =
        animationSelector.select(stateHolderOpt.map(_.state), positionOpt.map(_.direction))
                .map(animation => Graphics(animation, animationSelector, timestamp))
                .getOrElse(this)
    
    def getFrame(timestamp: Timestamp): Frame =
        animation.frame(timestamp - animationTimestamp)
}

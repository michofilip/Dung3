package entity

import commons.temporal.Timestamp
import model.{Position, StateHolder}

case class Entity(id: Long, timestamp: Timestamp,
                  stateHolderOpt: Option[StateHolder] = None,
                  positionOpt: Option[Position] = None) {
    
    def setStateHolder(stateHolder: StateHolder): Entity = copy(stateHolderOpt = Some(stateHolder))
    
    def setPosition(position: Position): Entity = copy(positionOpt = Some(position))
    
    def removeStateHolder(): Entity = copy(stateHolderOpt = None)
    
    def removePosition(): Entity = copy(positionOpt = None)
    
}

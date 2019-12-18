package entity

import commons.temporal.Timestamp
import model.Position

case class Entity(id: Long, timestamp: Timestamp,
                  positionOpt: Option[Position]) {
    def setPosition(position: Position): Entity = copy(positionOpt = Some(position))
    
    def removePosition(): Entity = copy(positionOpt = None)
}

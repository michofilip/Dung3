package entity

import commons.temporal.Timestamp

object EntityService {
    
    implicit class PositionService(entity: Entity) {
        
        def moveTo(x: Int, y: Int)(timestamp: Timestamp): Entity = entity.positionOpt match {
            case Some(position) => entity.setPosition(position.moveTo(x, y, timestamp))
            case None => entity
        }
        
        def moveBy(dx: Int, dy: Int)(timestamp: Timestamp): Entity = entity.positionOpt match {
            case Some(position) => entity.setPosition(position.moveBy(dx, dy, timestamp))
            case None => entity
        }
        
        def rotateRight45(timestamp: Timestamp): Entity = entity.positionOpt match {
            case Some(position) => entity.setPosition(position.rotateRight45(timestamp))
            case None => entity
        }
        
        def rotateRight90(timestamp: Timestamp): Entity = entity.positionOpt match {
            case Some(position) => entity.setPosition(position.rotateRight90(timestamp))
            case None => entity
        }
        
        def rotateLeft45(timestamp: Timestamp): Entity = entity.positionOpt match {
            case Some(position) => entity.setPosition(position.rotateLeft45(timestamp))
            case None => entity
        }
        
        def rotateLeft90(timestamp: Timestamp): Entity = entity.positionOpt match {
            case Some(position) => entity.setPosition(position.rotateLeft90(timestamp))
            case None => entity
        }
        
        def rotate180(timestamp: Timestamp): Entity = entity.positionOpt match {
            case Some(position) => entity.setPosition(position.rotate180(timestamp))
            case None => entity
        }
        
    }
    
}

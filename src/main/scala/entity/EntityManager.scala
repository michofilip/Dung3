package entity

import model.EntityState.{Close, Open}
import model.EntityType.Door
import model.{Animation, AnimationSelector, Direction, EntityState, Frame, Physics, PhysicsSelector, Position}
import temporal.Timestamp

object EntityManager {
    
    implicit class StateManager(entity: Entity) {
        def setEntityState(state: EntityState): Entity = entity.copy(entityStateOpt = Some(state))
        
        def removeEntityState(): Entity = entity.copy(entityStateOpt = None)
    }
    
    implicit class PositionManger(entity: Entity) {
        def setPosition(position: Position): Entity = entity.copy(positionOpt = Some(position))
        
        def removePosition(): Entity = entity.copy(positionOpt = None)
    }
    
    implicit class DirectionManger(entity: Entity) {
        def setDirection(direction: Direction): Entity = entity.copy(directionOpt = Some(direction))
        
        def removeDirection(): Entity = entity.copy(directionOpt = None)
    }
    
    implicit class PhysicsManager(entity: Entity) {
        def setPhysics(physics: Physics): Entity = entity.copy(physicsOpt = Some(physics))
        
        def removePhysics(): Entity = entity.copy(physicsOpt = None)
        
        def setPhysicsSelector(physicsSelector: PhysicsSelector): Entity = entity.copy(physicsSelectorOpt = Some(physicsSelector))
        
        def removePhysicsSelector(): Entity = entity.copy(physicsSelectorOpt = None)
        
        def selectPhysics(): Entity = entity.physicsSelectorOpt match {
            case Some(physicsSelector) => physicsSelector.select(entity.entityStateOpt) match {
                case Some(physics) => setPhysics(physics)
                case None => removePhysics()
            }
            case None => entity
        }
    }
    
    implicit class AnimationManager(entity: Entity) {
        def setAnimation(animation: Animation): Entity = entity.copy(animationOpt = Some(animation))
        
        def removeAnimation(): Entity = entity.copy(animationOpt = None)
        
        def setAnimationSelector(animationSelector: AnimationSelector): Entity =
            entity.copy(animationSelectorOpt = Some(animationSelector))
        
        def removeAnimationSelector(): Entity = entity.copy(animationSelectorOpt = None)
        
        def setAnimationTimestamp(animationTimestamp: Timestamp): Entity = entity.copy(animationTimestampOpt = Some(animationTimestamp))
        
        def removeAnimationTimestamp(): Entity = entity.copy(animationTimestampOpt = None)
        
        def selectAnimation(): Entity = entity.animationSelectorOpt match {
            case Some(animationSelector) => animationSelector.select(entity.entityStateOpt, entity.directionOpt) match {
                case Some(animation) => setAnimation(animation)
                case None => removeAnimation()
            }
            case None => entity
        }
        
        def getFrame(timestamp: Timestamp): Option[Frame] =
            entity.animationOpt.map(_.frame(timestamp.time - entity.animationTimestampOpt.map(_.time).getOrElse(0L)))
    }
    
    implicit class DoorManager(entity: Entity) {
        def open(timestamp: Timestamp): Entity = (entity.entityType, entity.entityStateOpt) match {
            case (Door, Some(Close)) => entity
                    .setEntityState(Open)
                    .setAnimationTimestamp(timestamp)
                    .selectPhysics()
                    .selectAnimation()
            case _ => entity
        }
        
        def close(timestamp: Timestamp): Entity = (entity.entityType, entity.entityStateOpt) match {
            case (Door, Some(Open)) => entity
                    .setEntityState(Close)
                    .setAnimationTimestamp(timestamp)
                    .selectPhysics()
                    .selectAnimation()
            case _ => entity
        }
    }
    
}

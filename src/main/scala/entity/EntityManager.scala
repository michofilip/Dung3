package entity

import model.EntityState.{Close, Open}
import model.EntityType.Door
import model.{Animation, AnimationSelector, Direction, EntityState, Physics, PhysicsSelector, Position}

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
        
        def selectAnimation(): Entity = entity.animationSelectorOpt match {
            case Some(animationSelector) => animationSelector.select(entity.entityStateOpt, entity.directionOpt) match {
                case Some(animation) => setAnimation(animation)
                case None => removeAnimation()
            }
            case None => entity
        }
    }
    
    implicit class DoorManager(entity: Entity) {
        def open(): Entity = (entity.entityType, entity.entityStateOpt) match {
            case (Door, Some(Close)) => entity.setEntityState(Open).selectPhysics().selectAnimation()
            case _ => entity
        }
        
        def close(): Entity = (entity.entityType, entity.entityStateOpt) match {
            case (Door, Some(Open)) => entity.setEntityState(Close).selectPhysics().selectAnimation()
            case _ => entity
        }
    }
    
}

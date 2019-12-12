package entity

import entity.EntityManager.{AnimationManager, PhysicsManager, PositionManger, StateManager}
import model.EntityName.TestDoor
import model.EntityState.Open
import model.Position
import repositories.{AnimationSelectorRepository, EntityTypeRepository, PhysicsSelectorRepository}
import temporal.Timestamp

class EntityFactory(implicit
                    entityTypeRepository: EntityTypeRepository,
                    physicsSelectorRepository: PhysicsSelectorRepository,
                    animationSelectorRepository: AnimationSelectorRepository) {
    def makeTestDoor(timestamp: Timestamp): Entity = {
        val name = TestDoor
        val entType = entityTypeRepository.getEntityType(name).getOrElse(throw new NoSuchElementException)
        val position = Position(10, 20)
        val physicsSelector = physicsSelectorRepository.getPhysicsSelector(name).getOrElse(throw new NoSuchElementException)
        val animationSelector = animationSelectorRepository.getAnimationSelector(name).getOrElse(throw new NoSuchElementException)
        
        Entity(1, name, entType)
                .setEntityState(Open)
                .setPosition(position)
                .setPhysicsSelector(physicsSelector)
                .setAnimationSelector(animationSelector)
                .setAnimationTimestamp(timestamp)
                .selectPhysics()
                .selectAnimation()
    }
}

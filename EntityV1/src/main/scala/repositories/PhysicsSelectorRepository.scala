package repositories

import model.EntityName.TestDoor
import model.EntityState.{Close, Open}
import model.{EntityName, PhysicsSelector}

class PhysicsSelectorRepository(implicit physicsRepository: PhysicsRepository) {
    private val openPhysics = physicsRepository.physicsFF
    private val closePhysics = physicsRepository.physicsTT
    
    private val testDoorPhysicsSelector = PhysicsSelector(
        Some(Open) -> openPhysics,
        Some(Close) -> closePhysics
    )
    
    private val physicsSelectors: Map[EntityName, PhysicsSelector] = Map(TestDoor -> testDoorPhysicsSelector)
    
    def getPhysicsSelector(entityName: EntityName): Option[PhysicsSelector] = physicsSelectors.get(entityName)
    
}

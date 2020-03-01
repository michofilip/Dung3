package repository

import model.NameKey
import model.NameKey.WoodenDoor
import model.physics.PhysicsSelector
import model.state.State.{Close, Closing, Locked, Locking, Open, Opening, UnLocking}

class PhysicsSelectorRepository(implicit physicsRepository: PhysicsRepository) {
    
    private val solidDoorPhysicsSelector = PhysicsSelector(
        Some(Open) -> physicsRepository.physicsFF,
        Some(Opening) -> physicsRepository.physicsTF,
        Some(Closing) -> physicsRepository.physicsTF,
        Some(Close) -> physicsRepository.physicsTT
    )
    
    private val solidLockableDoorPhysicsSelector = PhysicsSelector(
        Some(Open) -> physicsRepository.physicsFF,
        Some(Opening) -> physicsRepository.physicsTF,
        Some(Closing) -> physicsRepository.physicsTF,
        Some(Close) -> physicsRepository.physicsTT,
        Some(UnLocking) -> physicsRepository.physicsTT,
        Some(Locking) -> physicsRepository.physicsTT,
        Some(Locked) -> physicsRepository.physicsTT,
    )
    
    private val transparentDoorPhysicsSelector = PhysicsSelector(
        Some(Open) -> physicsRepository.physicsFF,
        Some(Opening) -> physicsRepository.physicsTF,
        Some(Closing) -> physicsRepository.physicsTF,
        Some(Close) -> physicsRepository.physicsTF
    )
    
    private val transparentLockableDoorPhysicsSelector = PhysicsSelector(
        Some(Open) -> physicsRepository.physicsFF,
        Some(Opening) -> physicsRepository.physicsTF,
        Some(Closing) -> physicsRepository.physicsTF,
        Some(Close) -> physicsRepository.physicsTF,
        Some(UnLocking) -> physicsRepository.physicsTF,
        Some(Locking) -> physicsRepository.physicsTF,
        Some(Locked) -> physicsRepository.physicsTF,
    )
    
    private val physicsSelectors: Map[NameKey, PhysicsSelector] = Map {
        WoodenDoor -> solidDoorPhysicsSelector
    }
    
    
    def getPhysicsSelector(name: NameKey): Option[PhysicsSelector] = physicsSelectors.get(name)
    
    //    def getPhysicsSelector(nameKey: NameKey): Option[PhysicsSelector] = nameKey match {
    //        case NameKey.WoodenDoor => Some(solidDoorPhysicsSelector)
    //        case _ => None
    //    }
    
}

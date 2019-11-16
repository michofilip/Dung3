package model

sealed abstract class EntityState

object EntityState {
    
    final case object Open extends EntityState
    
    final case object Close extends EntityState
    
}
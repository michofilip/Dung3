package model.state

sealed abstract class State

object State {
    
    final case object Standing extends State
    
    final case object Walking extends State
    
    final case object Open extends State
    
    final case object Close extends State
    
}

package model.state

sealed abstract class State

object State {
    
    final case object Standing extends State
    
    final case object Walking extends State
    
    
    final case object Off extends State
    
    final case object SwitchingOff extends State
    
    final case object SwitchingOn extends State
    
    final case object On extends State
    
    
    final case object Open extends State
    
    final case object Opening extends State
    
    final case object Closing extends State
    
    final case object Close extends State
    
    final case object UnLocking extends State
    
    final case object Locking extends State
    
    final case object Locked extends State
    
}

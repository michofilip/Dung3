package engine.entity.parts.state

sealed abstract class State

object State {

    sealed trait MovementState extends State

    sealed trait SwitchState extends State

    sealed trait ClosableState extends State

    sealed trait LockableState extends State


    final case object Standing extends State with MovementState

    final case object Walking extends State with MovementState


    final case object Off extends State with SwitchState

    final case object SwitchingOff extends State with SwitchState

    final case object SwitchingOn extends State with SwitchState

    final case object On extends State with SwitchState


    final case object Open extends State with ClosableState

    final case object Opening extends State with ClosableState

    final case object Closing extends State with ClosableState

    final case object Close extends State with ClosableState

    // todo rethink state Unlocked
    final case object UnLocking extends State with ClosableState with LockableState

    final case object Locking extends State with ClosableState with LockableState

    final case object Locked extends State with ClosableState with LockableState

}

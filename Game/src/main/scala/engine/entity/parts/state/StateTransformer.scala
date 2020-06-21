package engine.entity.parts.state

import engine.entity.parts.state.State.{Standing, Walking}

trait StateTransformer extends (State => State)

object StateTransformer {
    def movementStateTransformer: StateTransformer = {
        case state: State.MovementState => state match {
            case State.Standing => Walking
            case State.Walking => Standing
        }
        case state => state
    }
}

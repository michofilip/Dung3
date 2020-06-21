package engine.entity.parts.state

import engine.entity.parts.state.State.{Standing, Walking}

object StateTransformer {
    type StateTransformer = State => State

    val movementStateTransformer: StateTransformer = {
        case state: State.MovementState => state match {
            case State.Standing => Walking
            case State.Walking => Standing
        }
        case state => state
    }
}

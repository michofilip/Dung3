package engine.entity.parts.state

import engine.entity.parts.state.StateTransformer.StateTransformer
import engine.temporal.Timestamp

case class StateContainer(state: State, stateTimestamp: Timestamp)

object StateContainer {
    def update(stateTransformer: StateTransformer, stateTimestamp: Timestamp)(stateContainer: StateContainer): StateContainer = {
        stateTransformer(stateContainer.state) match {
            case state if state != stateContainer.state => StateContainer(state, stateTimestamp)
            case _ => stateContainer
        }
    }
}

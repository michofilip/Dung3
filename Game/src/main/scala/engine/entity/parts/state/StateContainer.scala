package engine.entity.parts.state

import engine.entity.parts.state.StateMappers.StateMapper
import engine.temporal.Timestamp

case class StateContainer(state: State, stateTimestamp: Timestamp)

object StateContainer {
    def update(stateMapper: StateMapper, stateTimestamp: Timestamp)(stateContainer: StateContainer): StateContainer = {
        val state = stateMapper(stateContainer.state)
        if (state != stateContainer.state)
            StateContainer(state, stateTimestamp)
        else
            stateContainer
    }
}

package engine.entity.parts.state

import commons.temporal.Timestamp
import engine.entity.parts.state.StateMappers.StateMapper

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

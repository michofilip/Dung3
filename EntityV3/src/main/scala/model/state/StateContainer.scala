package model.state

import commons.temporal.Timestamp

case class StateContainer(state: State, stateTimestamp: Timestamp)

object StateContainer {
    def update(stateMapper: State => State, stateTimestamp: Timestamp)(stateContainer: StateContainer): StateContainer = {
        val state = stateMapper(stateContainer.state)
        if (state != stateContainer.state)
            StateContainer(state, stateTimestamp)
        else
            stateContainer
    }
}

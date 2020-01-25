package model.state

import commons.temporal.Timestamp

case class StateContainer(state: State, stateTimestamp: Timestamp) {
    def updateState(stateMapper: State => State, stateTimestamp: Timestamp): StateContainer = {
        val newState = stateMapper(state)
        if (newState != state)
            StateContainer(newState, stateTimestamp)
        else
            this
    }
}

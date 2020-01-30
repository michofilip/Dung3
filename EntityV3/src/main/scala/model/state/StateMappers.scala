package model.state

import model.state.State.{Standing, Walking}

object StateMappers {
    type StateMapper = State => State
    
    def movement: StateMapper = {
        case Standing => Walking
        case Walking => Standing
        case state => state
    }
}

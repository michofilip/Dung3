package entity

import commons.temporal.Timestamp
import model.graphics.AnimationContainer
import model.physics.PhysicsContainer
import model.position.PositionContainer
import model.state.StateContainer

case class Entity(id: Long, name: String, initialTimestamp: Timestamp,
                  stateContainerOpt: Option[StateContainer] = None,
                  positionContainerOpt: Option[PositionContainer] = None,
                  physicsContainerOpt: Option[PhysicsContainer] = None,
                  animationContainerOpt: Option[AnimationContainer] = None)
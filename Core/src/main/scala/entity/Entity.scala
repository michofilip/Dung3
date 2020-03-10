package entity

import commons.temporal.Timestamp
import model.NameKey
import model.animation.AnimationContainer
import model.physics.PhysicsContainer
import model.position.PositionContainer
import model.script.ScriptContainer
import model.state.StateContainer

case class Entity(id: Long, nameKey: NameKey, initialTimestamp: Timestamp,
                  stateContainerOpt: Option[StateContainer] = None,
                  positionContainerOpt: Option[PositionContainer] = None,
                  physicsContainerOpt: Option[PhysicsContainer] = None,
                  animationContainerOpt: Option[AnimationContainer] = None,
                  scriptContainerOpt: Option[ScriptContainer] = None)
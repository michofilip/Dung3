package entity

import commons.temporal.Timestamp
import model.NameKey
import model.animation.AnimationContainer
import model.physics.PhysicsContainer
import model.position.PositionContainer
import model.script.ScriptContainer
import model.state.StateContainer

case class Entity(id: Long, nameKey: NameKey, initialTimestamp: Timestamp,
                  stateContainer: Option[StateContainer] = None,
                  positionContainer: Option[PositionContainer] = None,
                  physicsContainer: Option[PhysicsContainer] = None,
                  animationContainer: Option[AnimationContainer] = None,
                  scriptContainerOpt: Option[ScriptContainer] = None)
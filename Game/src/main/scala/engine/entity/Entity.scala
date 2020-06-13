package engine.entity

import engine.entity.parts.NameKey
import engine.entity.parts.animation.AnimationContainer
import engine.entity.parts.physics.PhysicsContainer
import engine.entity.parts.position.PositionContainer
import engine.entity.parts.script.ScriptContainer
import engine.entity.parts.state.StateContainer
import engine.entity.parts.value.ValueContainer
import engine.temporal.Timestamp

case class Entity(id: Long, nameKey: NameKey, initialTimestamp: Timestamp,
                  stateContainerOpt: Option[StateContainer] = None,
                  positionContainerOpt: Option[PositionContainer] = None,
                  physicsContainerOpt: Option[PhysicsContainer] = None,
                  animationContainerOpt: Option[AnimationContainer] = None,
                  valueContainerOpt: Option[ValueContainer] = None,
                  scriptContainerOpt: Option[ScriptContainer] = None)
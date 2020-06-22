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
                  stateContainer: Option[StateContainer] = None,
                  positionContainer: Option[PositionContainer] = None,
                  physicsContainer: Option[PhysicsContainer] = None,
                  animationContainer: Option[AnimationContainer] = None,
                  valueContainer: Option[ValueContainer] = None,
                  scriptContainer: Option[ScriptContainer] = None)
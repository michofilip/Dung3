package entity

import model.{Animation, AnimationSelector, Direction, EntityName, EntityState, EntityType, Physics, PhysicsSelector, Position}
import temporal.Timestamp

case class Entity(id: Long,
                  entityName: EntityName,
                  entityType: EntityType,
                  entityStateOpt: Option[EntityState] = None,
                  positionOpt: Option[Position] = None,
                  directionOpt: Option[Direction] = None,
                  physicsOpt: Option[Physics] = None,
                  physicsSelectorOpt: Option[PhysicsSelector] = None,
                  animationOpt: Option[Animation] = None,
                  animationSelectorOpt: Option[AnimationSelector] = None,
                  animationTimestampOpt: Option[Timestamp] = None)
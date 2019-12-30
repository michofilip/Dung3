package entity

import commons.temporal.Timestamp
import model.{Coordinates, Direction, State}

case class Entity(id: Long, timestamp: Timestamp,
                  stateOpt: Option[State] = None,
                  coordinatesOpt: Option[Coordinates] = None,
                  directionOpt: Option[Direction] = None)
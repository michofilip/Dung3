package world

import commons.temporal.Timestamp
import repository.EntityRepository

case class WorldFrameContext(timestamp: Timestamp, turn: Int, entityRepository: EntityRepository)

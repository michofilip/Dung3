package world

import commons.temporal.Timestamp
import repository.EntityRepository
import value.ValueContext

case class WorldFrameContext(timestamp: Timestamp, turn: Int, entityRepository: EntityRepository) extends ValueContext

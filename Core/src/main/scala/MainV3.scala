import commons.temporal.Timestamp
import entity.Entity
import events.{Event, PositionEvents}
import model.NameKey
import model.position.{Coordinates, Direction, Position, PositionContainer}
import repository.EntityRepository
import world.WorldFrame

object MainV3 extends App {
    val initialTimestamp = Timestamp.now
    val position = PositionContainer(Position(Coordinates(10, 20), Direction.North), initialTimestamp)
    val entity = Entity(id = 1, nameKey = NameKey.WoodenDoor, initialTimestamp = Timestamp.now, positionContainer = Some(position))
    
    val entityRepository = EntityRepository(Seq(entity))
    val events = Vector[Event](PositionEvents.Step(1, Direction.South))
    val world1 = WorldFrame(initialTimestamp, 1, entityRepository, events)
    val world2 = world1.nextFrame(Vector(), initialTimestamp)
    println(world1)
    println(world2)
}

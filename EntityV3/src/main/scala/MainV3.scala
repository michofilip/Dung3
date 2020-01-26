import commons.temporal.Timestamp
import entity.Entity
import model.position.{Coordinates, Direction, Position}

object MainV3 extends App {
    val initialTimestamp = Timestamp.now
    //    val stateContainer = StateContainer(State.Open, initialTimestamp)
    val position = Position(Coordinates(10, 20), Direction.North, initialTimestamp)
    val entity = Entity(id = 1, name = "Test entity", initialTimestamp = Timestamp.now, positionOpt = Some(position))
    
    val entity2 = entity.updatePosition(Position.moveBy(5, -5), Timestamp.now)
    
    println(entity)
    println(entity2)
}

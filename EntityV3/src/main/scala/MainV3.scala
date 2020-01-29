import commons.temporal.Timestamp
import entity.Entity
import model.position.{Coordinates, Direction, Position, PositionContainer, PositionMappers}

object MainV3 extends App {
    val initialTimestamp = Timestamp.now
    //    val stateContainer = StateContainer(State.Open, initialTimestamp)
    val position = PositionContainer(Position(Coordinates(10, 20), Direction.North), initialTimestamp)
    val entity = Entity(id = 1, name = "Test entity", initialTimestamp = Timestamp.now, positionContainerOpt = Some(position))
    
    val entity2 = entity.updatePositionContainer(PositionMappers.moveBy(5, -5), Timestamp.now)
    
    println(entity)
    println(entity2)
}

package model.position

import commons.temporal.Timestamp
import model.position.Position.PositionMapper

case class PositionContainer(position: Position, positionTimestamp: Timestamp)

object PositionContainer {
    //    type PositionMapper = (Coordinates, Direction) => (Coordinates, Direction)
    
    def update(positionMapper: PositionMapper, positionTimestamp: Timestamp)
              (positionContainer: PositionContainer): PositionContainer = {
        val position = positionMapper(positionContainer.position)
        if (position != positionContainer.position)
            PositionContainer(position, positionTimestamp)
        else
            positionContainer
    }
    
    //    def moveTo(x: Int, y: Int): PositionMapper =
    //        (_, direction) => (Coordinates(x, y), direction)
    //
    //    def moveBy(dx: Int, dy: Int): PositionMapper =
    //        (coordinates, direction) => (coordinates.shift(dx, dy), direction)
    //
    //    def step(direction: Direction): PositionMapper =
    //        direction match {
    //            case Direction.North => moveBy(0, -1)
    //            case Direction.NorthEast => moveBy(1, -1)
    //            case Direction.East => moveBy(1, 0)
    //            case Direction.SouthEast => moveBy(1, 1)
    //            case Direction.South => moveBy(0, 1)
    //            case Direction.SouthWest => moveBy(-1, 1)
    //            case Direction.West => moveBy(-1, 0)
    //            case Direction.NorthWest => moveBy(-1, -1)
    //        }
    //
    //    def rotateTo(direction: Direction): PositionMapper =
    //        (coordinates, _) => (coordinates, direction)
    //
    //    def rotateRight45(): PositionMapper =
    //        (coordinates, direction) => (coordinates, direction.right)
    //
    //    def rotateRight90(): PositionMapper =
    //        (coordinates, direction) => (coordinates, direction.right.right)
    //
    //    def rotateLeft45(): PositionMapper =
    //        (coordinates, direction) => (coordinates, direction.left)
    //
    //    def rotateLeft90(): PositionMapper =
    //        (coordinates, direction) => (coordinates, direction.left.left)
    //
    //    def rotate180(): PositionMapper =
    //        (coordinates, direction) => (coordinates, direction.opposite)
}

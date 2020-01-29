package model.position

import commons.temporal.Timestamp

case class Position(coordinates: Coordinates, direction: Direction, positionTimestamp: Timestamp)

object Position {
    type PositionMapper = (Coordinates, Direction) => (Coordinates, Direction)
    
    def update(positionMapper: PositionMapper, positionTimestamp: Timestamp)
              (position: Position): Position = {
        val (coordinates, direction) = positionMapper(position.coordinates, position.direction)
        if (coordinates != position.coordinates || direction != position.direction)
            Position(coordinates, direction, positionTimestamp)
        else
            position
    }
    
    def moveTo(x: Int, y: Int): PositionMapper =
        (_, direction) => (Coordinates(x, y), direction)
    
    def moveBy(dx: Int, dy: Int): PositionMapper =
        (coordinates, direction) => (coordinates.shift(dx, dy), direction)
    
    def step(direction: Direction): PositionMapper =
        direction match {
            case Direction.North => moveBy(0, -1)
            case Direction.NorthEast => moveBy(1, -1)
            case Direction.East => moveBy(1, 0)
            case Direction.SouthEast => moveBy(1, 1)
            case Direction.South => moveBy(0, 1)
            case Direction.SouthWest => moveBy(-1, 1)
            case Direction.West => moveBy(-1, 0)
            case Direction.NorthWest => moveBy(-1, -1)
        }
    
    def rotateTo(direction: Direction): PositionMapper =
        (coordinates, _) => (coordinates, direction)
    
    def rotateRight45(): PositionMapper =
        (coordinates, direction) => (coordinates, direction.right)
    
    def rotateRight90(): PositionMapper =
        (coordinates, direction) => (coordinates, direction.right.right)
    
    def rotateLeft45(): PositionMapper =
        (coordinates, direction) => (coordinates, direction.left)
    
    def rotateLeft90(): PositionMapper =
        (coordinates, direction) => (coordinates, direction.left.left)
    
    def rotate180(): PositionMapper =
        (coordinates, direction) => (coordinates, direction.opposite)
}

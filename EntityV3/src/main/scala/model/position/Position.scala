package model.position

import commons.temporal.Timestamp

case class Position(coordinates: Coordinates, direction: Direction, positionTimestamp: Timestamp)

object Position {
    def update(positionMapper: (Coordinates, Direction) => (Coordinates, Direction), positionTimestamp: Timestamp)
              (position: Position): Position = {
        val (coordinates, direction) = positionMapper(position.coordinates, position.direction)
        if (coordinates != position.coordinates || direction != position.direction)
            Position(coordinates, direction, positionTimestamp)
        else
            position
    }
    
    def moveTo(x: Int, y: Int): (Coordinates, Direction) => (Coordinates, Direction) =
        (_, direction) => (Coordinates(x, y), direction)
    
    def moveBy(dx: Int, dy: Int): (Coordinates, Direction) => (Coordinates, Direction) =
        (coordinates, direction) => (coordinates.shift(dx, dy), direction)
    
    def rotateTo(direction: Direction): (Coordinates, Direction) => (Coordinates, Direction) =
        (coordinates, _) => (coordinates, direction)
    
    def rotateRight45(): (Coordinates, Direction) => (Coordinates, Direction) =
        (coordinates, direction) => (coordinates, direction.right)
    
    def rotateRight90(): (Coordinates, Direction) => (Coordinates, Direction) =
        (coordinates, direction) => (coordinates, direction.right.right)
    
    def rotateLeft45(): (Coordinates, Direction) => (Coordinates, Direction) =
        (coordinates, direction) => (coordinates, direction.left)
    
    def rotateLeft90(): (Coordinates, Direction) => (Coordinates, Direction) =
        (coordinates, direction) => (coordinates, direction.left.left)
    
    def rotate180(): (Coordinates, Direction) => (Coordinates, Direction) =
        (coordinates, direction) => (coordinates, direction.opposite)
}

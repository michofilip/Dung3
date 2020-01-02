package model.position

import commons.temporal.Timestamp

case class Position(coordinates: Coordinates, direction: Direction, positionTimestamp: Timestamp) {
    def moveTo(x: Int, y: Int, positionTimestamp: Timestamp): Position =
        copy(coordinates = Coordinates(x, y), positionTimestamp = positionTimestamp)
    
    def moveBy(dx: Int, dy: Int, positionTimestamp: Timestamp): Position =
        copy(coordinates = coordinates.shift(dx, dy), positionTimestamp = positionTimestamp)
    
    def rotateRight45(positionTimestamp: Timestamp): Position =
        copy(direction = direction.right)
    
    def rotateRight90(positionTimestamp: Timestamp): Position =
        copy(direction = direction.right.right)
    
    def rotateLeft45(positionTimestamp: Timestamp): Position =
        copy(direction = direction.left)
    
    def rotateLeft90(positionTimestamp: Timestamp): Position =
        copy(direction = direction.left.left)
    
    def rotate180(positionTimestamp: Timestamp): Position =
        copy(direction = direction.opposite)
}

package model.position

object PositionMappers {
    type PositionMapper = Position => Position
    
    implicit class PositionMapperComposer(positionMapper1: PositionMapper) {
        def -->(positionMapper2: PositionMapper): PositionMapper = position => {
            positionMapper2(positionMapper1(position))
        }
    }
    
    def moveTo(x: Int, y: Int): PositionMapper = {
        case Position(_, direction) => Position(Coordinates(x, y), direction)
    }
    
    def moveBy(dx: Int, dy: Int): PositionMapper = {
        case Position(coordinates, direction) => Position(coordinates.shift(dx, dy), direction)
    }
    
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
    
    def rotateTo(direction: Direction): PositionMapper = {
        case Position(coordinates, _) => Position(coordinates, direction)
    }
    
    def rotateRight45(): PositionMapper = {
        case Position(coordinates, direction) => Position(coordinates, direction.right)
    }
    
    def rotateRight90(): PositionMapper = {
        case Position(coordinates, direction) => Position(coordinates, direction.right.right)
    }
    
    def rotateLeft45(): PositionMapper = {
        case Position(coordinates, direction) => Position(coordinates, direction.left)
    }
    
    def rotateLeft90(): PositionMapper = {
        case Position(coordinates, direction) => Position(coordinates, direction.left.left)
    }
    
    def rotate180(): PositionMapper = {
        case Position(coordinates, direction) => Position(coordinates, direction.opposite)
    }
}

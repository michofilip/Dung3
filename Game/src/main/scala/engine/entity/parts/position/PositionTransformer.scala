package engine.entity.parts.position

trait PositionTransformer extends (Position => Position) {
    def andThen(positionTransformer: PositionTransformer): PositionTransformer = {
        (position: Position) => positionTransformer.apply(this.apply(position))
    }
}

object PositionTransformer {

    def moveTo(x: Int, y: Int): PositionTransformer = {
        case Position(_, direction) => Position(Coordinates(x, y), direction)
    }

    def moveBy(dx: Int, dy: Int): PositionTransformer = {
        case Position(coordinates, direction) => Position(coordinates.shift(dx, dy), direction)
    }

    def step(direction: Direction): PositionTransformer = direction match {
        case Direction.North => moveBy(0, -1)
        case Direction.NorthEast => moveBy(1, -1)
        case Direction.East => moveBy(1, 0)
        case Direction.SouthEast => moveBy(1, 1)
        case Direction.South => moveBy(0, 1)
        case Direction.SouthWest => moveBy(-1, 1)
        case Direction.West => moveBy(-1, 0)
        case Direction.NorthWest => moveBy(-1, -1)
    }

    def rotateTo(direction: Direction): PositionTransformer = {
        case Position(coordinates, _) => Position(coordinates, direction)
    }

    def rotateRight45(): PositionTransformer = {
        case Position(coordinates, direction) => Position(coordinates, direction.right)
    }

    def rotateRight90(): PositionTransformer = {
        case Position(coordinates, direction) => Position(coordinates, direction.right.right)
    }

    def rotateLeft45(): PositionTransformer = {
        case Position(coordinates, direction) => Position(coordinates, direction.left)
    }

    def rotateLeft90(): PositionTransformer = {
        case Position(coordinates, direction) => Position(coordinates, direction.left.left)
    }

    def rotate180(): PositionTransformer = {
        case Position(coordinates, direction) => Position(coordinates, direction.opposite)
    }
}

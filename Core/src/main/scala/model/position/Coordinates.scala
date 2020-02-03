package model.position

case class Coordinates(x: Int, y: Int) {
    def shift(dx: Int, dy: Int): Coordinates = Coordinates(x + dx, y + dy)
}

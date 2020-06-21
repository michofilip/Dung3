package engine.entity.parts.position

import engine.temporal.Timestamp

case class PositionContainer(position: Position, positionTimestamp: Timestamp)

object PositionContainer {
    def update(positionTransformer: PositionTransformer, positionTimestamp: Timestamp)
              (positionContainer: PositionContainer): PositionContainer = {
        positionTransformer(positionContainer.position) match {
            case position if position != positionContainer.position => PositionContainer(position, positionTimestamp)
            case _ => positionContainer
        }
    }
}

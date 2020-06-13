package engine.entity.parts.position

import commons.temporal.Timestamp
import engine.entity.parts.position.PositionMappers.PositionMapper

case class PositionContainer(position: Position, positionTimestamp: Timestamp)

object PositionContainer {
    def update(positionMapper: PositionMapper, positionTimestamp: Timestamp)
              (positionContainer: PositionContainer): PositionContainer = {
        val position = positionMapper(positionContainer.position)
        if (position != positionContainer.position)
            PositionContainer(position, positionTimestamp)
        else
            positionContainer
    }
}

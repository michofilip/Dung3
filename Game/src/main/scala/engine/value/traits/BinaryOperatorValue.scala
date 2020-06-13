package engine.value.traits

import engine.GameContext
import engine.value.Value
import engine.value.ValueTypes.Binary

trait BinaryOperatorValue[V1, V2, R] extends Value[R] {
    protected val operator: Binary[V1, V2, R]
    val value1: Value[V1]
    val value2: Value[V2]

    final override def get(implicit gameContext: GameContext): Option[R] =
        value1.get.flatMap(x => value2.get.flatMap(y => operator(x, y)))
}

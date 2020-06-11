package engine.value.traits

import engine.value.ValueTypes.Binary
import engine.value.{Value, ValueContext}

trait BinaryOperatorValue[V1, V2, R] extends Value[R] {
    protected val operator: Binary[V1, V2, R]
    val value1: Value[V1]
    val value2: Value[V2]

    final override def get(implicit gameContext: ValueContext): Option[R] =
        value1.get.flatMap(x => value2.get.flatMap(y => operator(x, y)))
}

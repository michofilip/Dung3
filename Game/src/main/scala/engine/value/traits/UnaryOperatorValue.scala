package engine.value.traits

import engine.GameContext
import engine.value.Value
import engine.value.ValueTypes.Unary

trait UnaryOperatorValue[V, R] extends Value[R] {
    protected val operator: Unary[V, R]
    val value: Value[V]

    final override def get(implicit gameContext: GameContext): Option[R] =
        value.get.flatMap(x => operator(x))
}
package engine.value

import engine.GameContext

object ValueTypes {
    type Unary[V, R] = V => Option[R]
    type Binary[V1, V2, R] = (V1, V2) => Option[R]
    type Provider[R] = GameContext => Option[R]
    type Comparator[V] = Binary[V, V, Int]
}
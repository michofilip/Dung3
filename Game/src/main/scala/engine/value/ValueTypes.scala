package engine.value

object ValueTypes {
    type Unary[V, R] = V => Option[R]
    type Binary[V1, V2, R] = (V1, V2) => Option[R]
    type Provider[R] = ValueContext => Option[R]
    type Comparator[V] = Binary[V, V, Int]
}
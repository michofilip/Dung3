package engine.value.traits

import engine.value.Value
import engine.value.ValueTypes.Comparator
import engine.value.basic.BooleanValue

trait ComparableValue[V] extends Value[V] {
    val comparator: Comparator[V]

    def <(that: ComparableValue[V]): BooleanValue = BooleanValue.Less(this, that)

    def <=(that: ComparableValue[V]): BooleanValue = BooleanValue.LessEquals(this, that)

    def >(that: ComparableValue[V]): BooleanValue = BooleanValue.Greater(this, that)

    def >=(that: ComparableValue[V]): BooleanValue = BooleanValue.GreaterEquals(this, that)

}

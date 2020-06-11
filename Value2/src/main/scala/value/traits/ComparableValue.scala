package value.traits

import value.Value
import value.ValueTypes.Comparator
import value.basic.BooleanValue

trait ComparableValue[V] extends Value[V] {
    val comparator: Comparator[V]

    def <(that: ComparableValue[V]): BooleanValue = BooleanValue.Less(this, that)

    def <=(that: ComparableValue[V]): BooleanValue = BooleanValue.LessEquals(this, that)

    def >(that: ComparableValue[V]): BooleanValue = BooleanValue.Greater(this, that)

    def >=(that: ComparableValue[V]): BooleanValue = BooleanValue.GreaterEquals(this, that)

}

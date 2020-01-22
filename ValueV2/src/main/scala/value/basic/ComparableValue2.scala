package value.basic

import value.basic.comparators.ValueComparator
import value.{Value, ValueContext}

trait ComparableValue2[V <: ComparableValue2[V]] extends Value {
    def compareTo(value: V)(implicit valueComparator: ValueComparator[V], valueContext: ValueContext): Option[Int] =
        valueComparator.compare(this.asInstanceOf[V], value)
    
    //    final def <(that: ComparableValue2): BooleanValue = BooleanValue.Less(this, that)
    //
    //    final def <=(that: ComparableValue2): BooleanValue = BooleanValue.LessEqual(this, that)
    //
    //    final def >(that: ComparableValue2): BooleanValue = BooleanValue.Greater(this, that)
    //
    //    final def >=(that: ComparableValue2): BooleanValue = BooleanValue.GreaterEqual(this, that)
    
}

package value.basic

import value.basic.comparators.ValueComparator
import value.{Value, ValueContext}

trait ComparableValue[V <: ComparableValue[V]] extends Value {
    protected val valueComparator: ValueComparator[V]
    
    def compareTo(that: V)(implicit valueContext: ValueContext): Option[Int] =
        valueComparator.compare(this.asInstanceOf[V], that)
    
    final def <(that: ComparableValue[V]): BooleanValue = BooleanValue.Less(this, that)
    
    final def <=(that: ComparableValue[V]): BooleanValue = BooleanValue.LessEqual(this, that)
    
    final def >(that: ComparableValue[V]): BooleanValue = BooleanValue.Greater(this, that)
    
    final def >=(that: ComparableValue[V]): BooleanValue = BooleanValue.GreaterEqual(this, that)
    
}

package value

import context.ValueContext
import utils.ValueComparator

trait ComparableValue2[V <: ComparableValue2[V]] extends Value {
        def compareTo2[V1 <: ComparableValue2[V1]](value: ComparableValue2[V1])
                                                  (implicit valueComparator: ValueComparator[V, V1],
                                                   valueContext: ValueContext): Option[Int] = {
            valueComparator.compare(this.asInstanceOf[V], value.asInstanceOf[V1])
        }
    
    //    final def <(that: ComparableValue2): BooleanValue = BooleanValue.Less(this, that)
    //
    //    final def <=(that: ComparableValue2): BooleanValue = BooleanValue.LessEqual(this, that)
    //
    //    final def >(that: ComparableValue2): BooleanValue = BooleanValue.Greater(this, that)
    //
    //    final def >=(that: ComparableValue2): BooleanValue = BooleanValue.GreaterEqual(this, that)
    
}

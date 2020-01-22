package utils

import context.ValueContext
import value.{ComparableValue2, IntValue}

abstract class ValueComparator[V1 <: ComparableValue2[V1], V2 <: ComparableValue2[V2]] {
    def compare(value1: V1, value2: V2)(implicit valueContext: ValueContext): Option[Int]
}

object ValueComparator {
    
    implicit object Int2IntValueComparator extends ValueComparator[IntValue, IntValue] {
        override def compare(value1: IntValue, value2: IntValue)(implicit valueContext: ValueContext): Option[Int] =
            value1.get.flatMap(v1 => value2.get.map(v2 => v1 - v2))
    }
    
}

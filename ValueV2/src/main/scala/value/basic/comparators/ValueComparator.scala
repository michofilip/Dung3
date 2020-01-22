package value.basic.comparators

import value.basic.{ByteValue, DoubleValue, FloatValue, IntValue, LongValue, ShortValue}
import value.{Value, ValueContext}

abstract class ValueComparator[V <: Value] {
    def compare(value1: V, value2: V)(implicit valueContext: ValueContext): Option[Int]
}

object ValueComparator {
    
    implicit object ByteValueComparator extends ValueComparator[ByteValue] {
        override def compare(value1: ByteValue, value2: ByteValue)(implicit valueContext: ValueContext): Option[Int] =
            value1.get.flatMap(v1 => value2.get.map(v2 => v1 compareTo v2))
    }
    
    implicit object ShortValueComparator extends ValueComparator[ShortValue] {
        override def compare(value1: ShortValue, value2: ShortValue)(implicit valueContext: ValueContext): Option[Int] =
            value1.get.flatMap(v1 => value2.get.map(v2 => v1 compareTo v2))
    }
    
    implicit object IntValueComparator extends ValueComparator[IntValue] {
        override def compare(value1: IntValue, value2: IntValue)(implicit valueContext: ValueContext): Option[Int] =
            value1.get.flatMap(v1 => value2.get.map(v2 => v1 compareTo v2))
    }
    
    implicit object LongValueComparator extends ValueComparator[LongValue] {
        override def compare(value1: LongValue, value2: LongValue)(implicit valueContext: ValueContext): Option[Int] =
            value1.get.flatMap(v1 => value2.get.map(v2 => v1 compareTo v2))
    }
    
    implicit object FloatValueComparator extends ValueComparator[FloatValue] {
        override def compare(value1: FloatValue, value2: FloatValue)(implicit valueContext: ValueContext): Option[Int] =
            value1.get.flatMap(v1 => value2.get.map(v2 => v1 compareTo v2))
    }
    
    implicit object DoubleValueComparator extends ValueComparator[DoubleValue] {
        override def compare(value1: DoubleValue, value2: DoubleValue)(implicit valueContext: ValueContext): Option[Int] =
            value1.get.flatMap(v1 => value2.get.map(v2 => v1 compareTo v2))
    }
    
}

package parts.value.basic

import commons.utils.Casters
import parts.value.{Value, ValueContext}
import parts.value.basic.utils.ValueComparator

abstract class LongValue extends Value with NumericValue with ComparableValue[LongValue] {
    override final protected type T = Long
    override protected val valueComparator: ValueComparator[LongValue] = ValueComparator.LongValueComparator
    
    final def unary_+ : LongValue = this
    
    final def unary_- : LongValue = LongValue.LongNegate(this)
    
    final def +(that: LongValue): LongValue = LongValue.LongAdd(this, that)
    
    final def -(that: LongValue): LongValue = LongValue.LongSubtract(this, that)
    
    final def *(that: LongValue): LongValue = LongValue.LongMultiply(this, that)
    
    final def /(that: LongValue): LongValue = LongValue.LongDivide(this, that)
    
    final def %(that: LongValue): LongValue = LongValue.LongMod(this, that)
}

object LongValue {
    
    final case object LongNull extends LongValue {
        override def get(implicit valueContext: ValueContext): Option[Long] = None
    }
    
    final case class LongConstant(value: Long) extends LongValue {
        override def get(implicit valueContext: ValueContext): Option[Long] = Some(value)
    }
    
    final case class LongNegate(value: LongValue) extends LongValue {
        override def get(implicit valueContext: ValueContext): Option[Long] = value.get.map(v => -v)
    }
    
    final case class LongAdd(value1: LongValue, value2: LongValue) extends LongValue {
        override def get(implicit valueContext: ValueContext): Option[Long] =
            value1.get.flatMap(v1 => value2.get.map(v2 => v1 + v2))
    }
    
    final case class LongSubtract(value1: LongValue, value2: LongValue) extends LongValue {
        override def get(implicit valueContext: ValueContext): Option[Long] =
            value1.get.flatMap(v1 => value2.get.map(v2 => v1 - v2))
    }
    
    final case class LongMultiply(value1: LongValue, value2: LongValue) extends LongValue {
        override def get(implicit valueContext: ValueContext): Option[Long] =
            value1.get.flatMap(v1 => value2.get.map(v2 => v1 * v2))
    }
    
    final case class LongDivide(value1: LongValue, value2: LongValue) extends LongValue {
        override def get(implicit valueContext: ValueContext): Option[Long] =
            value1.get.flatMap(v1 => value2.get.filter(_ != 0).map(v2 => v1 / v2))
    }
    
    final case class LongMod(value1: LongValue, value2: LongValue) extends LongValue {
        override def get(implicit valueContext: ValueContext): Option[Long] =
            value1.get.flatMap(v1 => value2.get.filter(_ != 0).map(v2 => v1 % v2))
    }
    
    final case class NumericToLong(value: NumericValue) extends LongValue {
        override def get(implicit valueContext: ValueContext): Option[Long] =
            value.get.flatMap(Casters.toLong)
    }
    
}
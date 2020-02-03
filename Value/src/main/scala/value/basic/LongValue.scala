package value.basic

import commons.utils.Casters
import value.basic.utils.ValueComparator
import value.{Value, ValueContext}

abstract class LongValue extends Value with NumericValue with ComparableValue[LongValue] {
    override final protected type T = Long
    override protected val valueComparator: ValueComparator[LongValue] = ValueComparator.LongValueComparator
    
    final def unary_+ : LongValue = this
    
    final def unary_- : LongValue = LongValue.LongNegate(this)
    
    // add
    final def +(that: ByteValue): LongValue = LongValue.LongAdd(this, that.toLongValue)
    
    final def +(that: ShortValue): LongValue = LongValue.LongAdd(this, that.toLongValue)
    
    final def +(that: IntValue): LongValue = LongValue.LongAdd(this, that.toLongValue)
    
    final def +(that: LongValue): LongValue = LongValue.LongAdd(this, that)
    
    final def +(that: FloatValue): DoubleValue = DoubleValue.DoubleAdd(this.toDoubleValue, that.toDoubleValue)
    
    final def +(that: DoubleValue): DoubleValue = DoubleValue.DoubleAdd(this.toDoubleValue, that)
    
    // subtract
    final def -(that: ByteValue): LongValue = LongValue.LongSubtract(this, that.toLongValue)
    
    final def -(that: ShortValue): LongValue = LongValue.LongSubtract(this, that.toLongValue)
    
    final def -(that: IntValue): LongValue = LongValue.LongSubtract(this, that.toLongValue)
    
    final def -(that: LongValue): LongValue = LongValue.LongSubtract(this, that)
    
    final def -(that: FloatValue): DoubleValue = DoubleValue.DoubleSubtract(this.toDoubleValue, that.toDoubleValue)
    
    final def -(that: DoubleValue): DoubleValue = DoubleValue.DoubleSubtract(this.toDoubleValue, that)
    
    // multiply
    final def *(that: ByteValue): LongValue = LongValue.LongMultiply(this, that.toLongValue)
    
    final def *(that: ShortValue): LongValue = LongValue.LongMultiply(this, that.toLongValue)
    
    final def *(that: IntValue): LongValue = LongValue.LongMultiply(this, that.toLongValue)
    
    final def *(that: LongValue): LongValue = LongValue.LongMultiply(this, that)
    
    final def *(that: FloatValue): DoubleValue = DoubleValue.DoubleMultiply(this.toDoubleValue, that.toDoubleValue)
    
    final def *(that: DoubleValue): DoubleValue = DoubleValue.DoubleMultiply(this.toDoubleValue, that)
    
    // divide
    final def /(that: ByteValue): LongValue = LongValue.LongDivide(this, that.toLongValue)
    
    final def /(that: ShortValue): LongValue = LongValue.LongDivide(this, that.toLongValue)
    
    final def /(that: IntValue): LongValue = LongValue.LongDivide(this, that.toLongValue)
    
    final def /(that: LongValue): LongValue = LongValue.LongDivide(this, that)
    
    final def /(that: FloatValue): DoubleValue = DoubleValue.DoubleDivide(this.toDoubleValue, that.toDoubleValue)
    
    final def /(that: DoubleValue): DoubleValue = DoubleValue.DoubleDivide(this.toDoubleValue, that)
    
    // modulo
    final def %(that: ByteValue): LongValue = LongValue.LongMod(this, that.toLongValue)
    
    final def %(that: ShortValue): LongValue = LongValue.LongMod(this, that.toLongValue)
    
    final def %(that: IntValue): LongValue = LongValue.LongMod(this, that.toLongValue)
    
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
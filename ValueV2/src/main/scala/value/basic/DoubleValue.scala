package value.basic

import utils.Caster
import value.basic.comparators.ValueComparator
import value.{Value, ValueContext}

abstract class DoubleValue extends Value with NumericValue with ComparableValue[DoubleValue] {
    override final protected type T = Double
    override protected val valueComparator: ValueComparator[DoubleValue] = ValueComparator.DoubleValueComparator
    
    final def unary_+ : DoubleValue = this
    
    final def unary_- : DoubleValue = DoubleValue.DoubleNegate(this)
    
    // add
    final def +(that: ByteValue): DoubleValue = DoubleValue.DoubleAdd(this, that.toDoubleValue)
    
    final def +(that: ShortValue): DoubleValue = DoubleValue.DoubleAdd(this, that.toDoubleValue)
    
    final def +(that: IntValue): DoubleValue = DoubleValue.DoubleAdd(this, that.toDoubleValue)
    
    final def +(that: LongValue): DoubleValue = DoubleValue.DoubleAdd(this, that.toDoubleValue)
    
    final def +(that: FloatValue): DoubleValue = DoubleValue.DoubleAdd(this, that.toDoubleValue)
    
    final def +(that: DoubleValue): DoubleValue = DoubleValue.DoubleAdd(this, that)
    
    // subtract
    final def -(that: ByteValue): DoubleValue = DoubleValue.DoubleSubtract(this, that.toDoubleValue)
    
    final def -(that: ShortValue): DoubleValue = DoubleValue.DoubleSubtract(this, that.toDoubleValue)
    
    final def -(that: IntValue): DoubleValue = DoubleValue.DoubleSubtract(this, that.toDoubleValue)
    
    final def -(that: LongValue): DoubleValue = DoubleValue.DoubleSubtract(this, that.toDoubleValue)
    
    final def -(that: FloatValue): DoubleValue = DoubleValue.DoubleSubtract(this, that.toDoubleValue)
    
    final def -(that: DoubleValue): DoubleValue = DoubleValue.DoubleSubtract(this, that)
    
    // multiply
    final def *(that: ByteValue): DoubleValue = DoubleValue.DoubleMultiply(this, that.toDoubleValue)
    
    final def *(that: ShortValue): DoubleValue = DoubleValue.DoubleMultiply(this, that.toDoubleValue)
    
    final def *(that: IntValue): DoubleValue = DoubleValue.DoubleMultiply(this, that.toDoubleValue)
    
    final def *(that: LongValue): DoubleValue = DoubleValue.DoubleMultiply(this, that.toDoubleValue)
    
    final def *(that: FloatValue): DoubleValue = DoubleValue.DoubleMultiply(this, that.toDoubleValue)
    
    final def *(that: DoubleValue): DoubleValue = DoubleValue.DoubleMultiply(this, that)
    
    // divide
    final def /(that: ByteValue): DoubleValue = DoubleValue.DoubleDivide(this, that.toDoubleValue)
    
    final def /(that: ShortValue): DoubleValue = DoubleValue.DoubleDivide(this, that.toDoubleValue)
    
    final def /(that: IntValue): DoubleValue = DoubleValue.DoubleDivide(this, that.toDoubleValue)
    
    final def /(that: LongValue): DoubleValue = DoubleValue.DoubleDivide(this, that.toDoubleValue)
    
    final def /(that: FloatValue): DoubleValue = DoubleValue.DoubleDivide(this, that.toDoubleValue)
    
    final def /(that: DoubleValue): DoubleValue = DoubleValue.DoubleDivide(this, that)
}

object DoubleValue {
    
    final case object DoubleNull extends DoubleValue {
        override def get(implicit valueContext: ValueContext): Option[Double] = None
    }
    
    final case class DoubleConstant(value: Double) extends DoubleValue {
        override def get(implicit valueContext: ValueContext): Option[Double] = Some(value)
    }
    
    final case class DoubleNegate(value: DoubleValue) extends DoubleValue {
        override def get(implicit valueContext: ValueContext): Option[Double] = value.get.map(v => -v)
    }
    
    final case class DoubleAdd(value1: DoubleValue, value2: DoubleValue) extends DoubleValue {
        override def get(implicit valueContext: ValueContext): Option[Double] =
            value1.get.flatMap(v1 => value2.get.map(v2 => v1 + v2))
    }
    
    final case class DoubleSubtract(value1: DoubleValue, value2: DoubleValue) extends DoubleValue {
        override def get(implicit valueContext: ValueContext): Option[Double] =
            value1.get.flatMap(v1 => value2.get.map(v2 => v1 - v2))
    }
    
    final case class DoubleMultiply(value1: DoubleValue, value2: DoubleValue) extends DoubleValue {
        override def get(implicit valueContext: ValueContext): Option[Double] =
            value1.get.flatMap(v1 => value2.get.map(v2 => v1 * v2))
    }
    
    final case class DoubleDivide(value1: DoubleValue, value2: DoubleValue) extends DoubleValue {
        override def get(implicit valueContext: ValueContext): Option[Double] =
            value1.get.flatMap(v1 => value2.get.filter(_ != 0).map(v2 => v1 / v2))
    }
    
    final case class NumericToDouble(value: NumericValue) extends DoubleValue {
        override def get(implicit valueContext: ValueContext): Option[Double] =
            value.get.flatMap(Caster.toDouble)
    }
    
}
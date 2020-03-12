package parts.value.basic

import commons.utils.Casters
import parts.value.{Value, ValueContext}
import parts.value.basic.utils.ValueComparator

abstract class FloatValue extends Value with NumericValue with ComparableValue[FloatValue] {
    override final protected type T = Float
    override protected val valueComparator: ValueComparator[FloatValue] = ValueComparator.FloatValueComparator
    
    final def unary_+ : FloatValue = this
    
    final def unary_- : FloatValue = FloatValue.FloatNegate(this)
    
    final def +(that: FloatValue): FloatValue = FloatValue.FloatAdd(this, that)
    
    final def -(that: FloatValue): FloatValue = FloatValue.FloatSubtract(this, that)
    
    final def *(that: FloatValue): FloatValue = FloatValue.FloatMultiply(this, that)
    
    final def /(that: FloatValue): FloatValue = FloatValue.FloatDivide(this, that)
    
}

object FloatValue {
    
    final case object FloatNull extends FloatValue {
        override def get(implicit valueContext: ValueContext): Option[Float] = None
    }
    
    final case class FloatConstant(value: Float) extends FloatValue {
        override def get(implicit valueContext: ValueContext): Option[Float] = Some(value)
    }
    
    final case class FloatNegate(value: FloatValue) extends FloatValue {
        override def get(implicit valueContext: ValueContext): Option[Float] = value.get.map(v => -v)
    }
    
    final case class FloatAdd(value1: FloatValue, value2: FloatValue) extends FloatValue {
        override def get(implicit valueContext: ValueContext): Option[Float] =
            value1.get.flatMap(v1 => value2.get.map(v2 => v1 + v2))
    }
    
    final case class FloatSubtract(value1: FloatValue, value2: FloatValue) extends FloatValue {
        override def get(implicit valueContext: ValueContext): Option[Float] =
            value1.get.flatMap(v1 => value2.get.map(v2 => v1 - v2))
    }
    
    final case class FloatMultiply(value1: FloatValue, value2: FloatValue) extends FloatValue {
        override def get(implicit valueContext: ValueContext): Option[Float] =
            value1.get.flatMap(v1 => value2.get.map(v2 => v1 * v2))
    }
    
    final case class FloatDivide(value1: FloatValue, value2: FloatValue) extends FloatValue {
        override def get(implicit valueContext: ValueContext): Option[Float] =
            value1.get.flatMap(v1 => value2.get.filter(_ != 0).map(v2 => v1 / v2))
    }
    
    final case class NumericToFloat(value: NumericValue) extends FloatValue {
        override def get(implicit valueContext: ValueContext): Option[Float] =
            value.get.flatMap(Casters.toFloat)
    }
    
}

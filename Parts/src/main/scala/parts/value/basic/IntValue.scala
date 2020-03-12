package parts.value.basic

import commons.utils.Casters
import parts.value.{Value, ValueContext}
import parts.value.basic.utils.ValueComparator

abstract class IntValue extends Value with NumericValue with ComparableValue[IntValue] {
    override final protected type T = Int
    override protected val valueComparator: ValueComparator[IntValue] = ValueComparator.IntValueComparator
    
    final def unary_+ : IntValue = this
    
    final def unary_- : IntValue = IntValue.IntNegate(this)
    
    final def +(that: IntValue): IntValue = IntValue.IntAdd(this, that)
    
    final def -(that: IntValue): IntValue = IntValue.IntSubtract(this, that)
    
    final def *(that: IntValue): IntValue = IntValue.IntMultiply(this, that)
    
    final def /(that: IntValue): IntValue = IntValue.IntDivide(this, that)
    
    final def %(that: IntValue): IntValue = IntValue.IntMod(this, that)
    
}

object IntValue {
    
    final case object IntNull extends IntValue {
        override def get(implicit valueContext: ValueContext): Option[Int] = None
    }
    
    final case class IntConstant(value: Int) extends IntValue {
        override def get(implicit valueContext: ValueContext): Option[Int] = Some(value)
    }
    
    final case class IntNegate(value: IntValue) extends IntValue {
        override def get(implicit valueContext: ValueContext): Option[Int] = value.get.map(v => -v)
    }
    
    final case class IntAdd(value1: IntValue, value2: IntValue) extends IntValue {
        override def get(implicit valueContext: ValueContext): Option[Int] =
            value1.get.flatMap(v1 => value2.get.map(v2 => v1 + v2))
    }
    
    final case class IntSubtract(value1: IntValue, value2: IntValue) extends IntValue {
        override def get(implicit valueContext: ValueContext): Option[Int] =
            value1.get.flatMap(v1 => value2.get.map(v2 => v1 - v2))
    }
    
    final case class IntMultiply(value1: IntValue, value2: IntValue) extends IntValue {
        override def get(implicit valueContext: ValueContext): Option[Int] =
            value1.get.flatMap(v1 => value2.get.map(v2 => v1 * v2))
    }
    
    final case class IntDivide(value1: IntValue, value2: IntValue) extends IntValue {
        override def get(implicit valueContext: ValueContext): Option[Int] =
            value1.get.flatMap(v1 => value2.get.filter(_ != 0).map(v2 => v1 / v2))
    }
    
    final case class IntMod(value1: IntValue, value2: IntValue) extends IntValue {
        override def get(implicit valueContext: ValueContext): Option[Int] =
            value1.get.flatMap(v1 => value2.get.filter(_ != 0).map(v2 => v1 % v2))
    }
    
    final case class NumericToInt(value: NumericValue) extends IntValue {
        override def get(implicit valueContext: ValueContext): Option[Int] =
            value.get.flatMap(Casters.toInt)
    }
    
}

package value.basic

import commons.utils.Casters
import value.basic.utils.ValueComparator
import value.{Value, ValueContext}

abstract class ShortValue extends Value with NumericValue with ComparableValue[ShortValue] {
    override final protected type T = Short
    override protected val valueComparator: ValueComparator[ShortValue] = ValueComparator.ShortValueComparator
    
    final def unary_+ : ShortValue = this
    
    final def unary_- : ShortValue = ShortValue.ShortNegate(this)
    
    final def +(that: ShortValue): ShortValue = ShortValue.ShortAdd(this, that)
    
    final def -(that: ShortValue): ShortValue = ShortValue.ShortSubtract(this, that)
    
    final def *(that: ShortValue): ShortValue = ShortValue.ShortMultiply(this, that)
    
    final def /(that: ShortValue): ShortValue = ShortValue.ShortDivide(this, that)
    
    final def %(that: ShortValue): ShortValue = ShortValue.ShortMod(this, that)
    
}

object ShortValue {
    
    final case object ShortNull extends ShortValue {
        override def get(implicit valueContext: ValueContext): Option[Short] = None
    }
    
    final case class ShortConstant(value: Short) extends ShortValue {
        override def get(implicit valueContext: ValueContext): Option[Short] = Some(value)
    }
    
    final case class ShortNegate(value: ShortValue) extends ShortValue {
        override def get(implicit valueContext: ValueContext): Option[Short] = value.get.map(v => (-v).toShort)
    }
    
    final case class ShortAdd(value1: ShortValue, value2: ShortValue) extends ShortValue {
        override def get(implicit valueContext: ValueContext): Option[Short] =
            value1.get.flatMap(v1 => value2.get.map(v2 => (v1 + v2).toShort))
    }
    
    final case class ShortSubtract(value1: ShortValue, value2: ShortValue) extends ShortValue {
        override def get(implicit valueContext: ValueContext): Option[Short] =
            value1.get.flatMap(v1 => value2.get.map(v2 => (v1 - v2).toShort))
    }
    
    final case class ShortMultiply(value1: ShortValue, value2: ShortValue) extends ShortValue {
        override def get(implicit valueContext: ValueContext): Option[Short] =
            value1.get.flatMap(v1 => value2.get.map(v2 => (v1 * v2).toShort))
    }
    
    final case class ShortDivide(value1: ShortValue, value2: ShortValue) extends ShortValue {
        override def get(implicit valueContext: ValueContext): Option[Short] =
            value1.get.flatMap(v1 => value2.get.filter(_ != 0).map(v2 => (v1 / v2).toShort))
    }
    
    final case class ShortMod(value1: ShortValue, value2: ShortValue) extends ShortValue {
        override def get(implicit valueContext: ValueContext): Option[Short] =
            value1.get.flatMap(v1 => value2.get.filter(_ != 0).map(v2 => (v1 % v2).toShort))
    }
    
    final case class NumericToShort(value: NumericValue) extends ShortValue {
        override def get(implicit valueContext: ValueContext): Option[Short] =
            value.get.flatMap(Casters.toShort)
    }
    
}
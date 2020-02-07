package value.basic

import commons.utils.Casters
import value.basic.utils.ValueComparator
import value.{Value, ValueContext}

abstract class ByteValue extends Value with NumericValue with ComparableValue[ByteValue] {
    override final protected type T = Byte
    override protected val valueComparator: ValueComparator[ByteValue] = ValueComparator.ByteValueComparator
    
    final def unary_+ : ByteValue = this
    
    final def unary_- : ByteValue = ByteValue.ByteNegate(this)
    
    final def +(that: ByteValue): ByteValue = ByteValue.ByteAdd(this, that)
    
    final def -(that: ByteValue): ByteValue = ByteValue.ByteSubtract(this, that)
    
    final def *(that: ByteValue): ByteValue = ByteValue.ByteMultiply(this, that)
    
    final def /(that: ByteValue): ByteValue = ByteValue.ByteDivide(this, that)
    
    final def %(that: ByteValue): ByteValue = ByteValue.ByteMod(this, that)
    
}

object ByteValue {
    
    final case object ByteNull extends ByteValue {
        override def get(implicit valueContext: ValueContext): Option[Byte] = None
    }
    
    final case class ByteConstant(value: Byte) extends ByteValue {
        override def get(implicit valueContext: ValueContext): Option[Byte] = Some(value)
    }
    
    final case class ByteNegate(value: ByteValue) extends ByteValue {
        override def get(implicit valueContext: ValueContext): Option[Byte] = value.get.map(v => (-v).toByte)
    }
    
    final case class ByteAdd(value1: ByteValue, value2: ByteValue) extends ByteValue {
        override def get(implicit valueContext: ValueContext): Option[Byte] =
            value1.get.flatMap(v1 => value2.get.map(v2 => (v1 + v2).toByte))
    }
    
    final case class ByteSubtract(value1: ByteValue, value2: ByteValue) extends ByteValue {
        override def get(implicit valueContext: ValueContext): Option[Byte] =
            value1.get.flatMap(v1 => value2.get.map(v2 => (v1 - v2).toByte))
    }
    
    final case class ByteMultiply(value1: ByteValue, value2: ByteValue) extends ByteValue {
        override def get(implicit valueContext: ValueContext): Option[Byte] =
            value1.get.flatMap(v1 => value2.get.map(v2 => (v1 * v2).toByte))
    }
    
    final case class ByteDivide(value1: ByteValue, value2: ByteValue) extends ByteValue {
        override def get(implicit valueContext: ValueContext): Option[Byte] =
            value1.get.flatMap(v1 => value2.get.filter(_ != 0).map(v2 => (v1 / v2).toByte))
    }
    
    final case class ByteMod(value1: ByteValue, value2: ByteValue) extends ByteValue {
        override def get(implicit valueContext: ValueContext): Option[Byte] =
            value1.get.flatMap(v1 => value2.get.filter(_ != 0).map(v2 => (v1 % v2).toByte))
    }
    
    final case class NumericToByte(value: NumericValue) extends ByteValue {
        override def get(implicit valueContext: ValueContext): Option[Byte] =
            value.get.flatMap(Casters.toByte)
    }
    
}
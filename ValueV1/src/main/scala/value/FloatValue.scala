package value

abstract class FloatValue extends Value with NumericValue {
    override final protected type T = Float
    
    override final def calculate: FloatValue = FloatValue.FloatCalculate(this)
    
    override protected[value] def compare(value: ComparableValue): Option[Int] = get.flatMap(v1 => value.get match {
        case Some(v2: Byte) => Some(v1 compareTo v2.toFloat)
        case Some(v2: Short) => Some(v1 compareTo v2.toFloat)
        case Some(v2: Int) => Some(v1 compareTo v2.toFloat)
        case Some(v2: Long) => Some(v1.toDouble compareTo v2.toDouble)
        case Some(v2: Float) => Some(v1 compareTo v2)
        case Some(v2: Double) => Some(v1.toDouble compareTo v2)
        case None => None
    })
    
    final def unary_+ : FloatValue = this
    
    final def unary_- : FloatValue = FloatValue.FloatNegate(this)
    
    // add
    final def +(that: ByteValue): FloatValue = FloatValue.FloatAdd(this, that.toFloatValue)
    
    final def +(that: ShortValue): FloatValue = FloatValue.FloatAdd(this, that.toFloatValue)
    
    final def +(that: IntValue): FloatValue = FloatValue.FloatAdd(this, that.toFloatValue)
    
    final def +(that: LongValue): FloatValue = FloatValue.FloatAdd(this, that.toFloatValue)
    
    final def +(that: FloatValue): FloatValue = FloatValue.FloatAdd(this, that)
    
    final def +(that: DoubleValue): DoubleValue = DoubleValue.DoubleAdd(this.toDoubleValue, that)
    
    // subtract
    final def -(that: ByteValue): FloatValue = FloatValue.FloatSubtract(this, that.toFloatValue)
    
    final def -(that: ShortValue): FloatValue = FloatValue.FloatSubtract(this, that.toFloatValue)
    
    final def -(that: IntValue): FloatValue = FloatValue.FloatSubtract(this, that.toFloatValue)
    
    final def -(that: LongValue): FloatValue = FloatValue.FloatSubtract(this, that.toFloatValue)
    
    final def -(that: FloatValue): FloatValue = FloatValue.FloatSubtract(this, that)
    
    final def -(that: DoubleValue): DoubleValue = DoubleValue.DoubleSubtract(this.toDoubleValue, that)
    
    // multiply
    final def *(that: ByteValue): FloatValue = FloatValue.FloatMultiply(this, that.toFloatValue)
    
    final def *(that: ShortValue): FloatValue = FloatValue.FloatMultiply(this, that.toFloatValue)
    
    final def *(that: IntValue): FloatValue = FloatValue.FloatMultiply(this, that.toFloatValue)
    
    final def *(that: LongValue): FloatValue = FloatValue.FloatMultiply(this, that.toFloatValue)
    
    final def *(that: FloatValue): FloatValue = FloatValue.FloatMultiply(this, that)
    
    final def *(that: DoubleValue): DoubleValue = DoubleValue.DoubleMultiply(this.toDoubleValue, that)
    
    // divide
    final def /(that: ByteValue): FloatValue = FloatValue.FloatDivide(this, that.toFloatValue)
    
    final def /(that: ShortValue): FloatValue = FloatValue.FloatDivide(this, that.toFloatValue)
    
    final def /(that: IntValue): FloatValue = FloatValue.FloatDivide(this, that.toFloatValue)
    
    final def /(that: LongValue): FloatValue = FloatValue.FloatDivide(this, that.toFloatValue)
    
    final def /(that: FloatValue): FloatValue = FloatValue.FloatDivide(this, that)
    
    final def /(that: DoubleValue): DoubleValue = DoubleValue.DoubleDivide(this.toDoubleValue, that)
}

object FloatValue {
    
    final case object FloatNull extends FloatValue {
        override def get: Option[Float] = None
    }
    
    final case class FloatConstant(value: Float) extends FloatValue {
        override def get: Option[Float] = Some(value)
    }
    
    final case class FloatCalculate(value: FloatValue) extends FloatValue {
        private val calculated: Option[Float] = value.get
        
        override def get: Option[Float] = calculated
    }
    
    final case class FloatNegate(value: FloatValue) extends FloatValue {
        override def get: Option[Float] = value.get.map(v => -v)
    }
    
    final case class FloatAdd(value1: FloatValue, value2: FloatValue) extends FloatValue {
        override def get: Option[Float] = value1.get.flatMap(v1 => value2.get.map(v2 => v1 + v2))
    }
    
    final case class FloatSubtract(value1: FloatValue, value2: FloatValue) extends FloatValue {
        override def get: Option[Float] = value1.get.flatMap(v1 => value2.get.map(v2 => v1 - v2))
    }
    
    final case class FloatMultiply(value1: FloatValue, value2: FloatValue) extends FloatValue {
        override def get: Option[Float] = value1.get.flatMap(v1 => value2.get.map(v2 => v1 * v2))
    }
    
    final case class FloatDivide(value1: FloatValue, value2: FloatValue) extends FloatValue {
        override def get: Option[Float] = value1.get.flatMap(v1 => value2.get.filter(_ != 0).map(v2 => v1 / v2))
    }
    
    final case class NumericToFloat(value: NumericValue) extends FloatValue {
        override def get: Option[Float] = value.get match {
            case Some(v: Byte) => Some(v.toFloat)
            case Some(v: Short) => Some(v.toFloat)
            case Some(v: Int) => Some(v.toFloat)
            case Some(v: Long) => Some(v.toFloat)
            case Some(v: Float) => Some(v.toFloat)
            case Some(v: Double) => Some(v.toFloat)
            case _ => None
        }
    }
    
}

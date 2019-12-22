package value

abstract class LongValue extends Value with NumericValue {
    override final protected type T = Long
    
    override final def calculate: LongValue = LongValue.LongCalculate(this)
    
    override protected[value] def compare(value: ComparableValue): Option[Int] = get.flatMap(v1 => value.get match {
        case Some(v2: Byte) => Some(v1 compareTo v2.toLong)
        case Some(v2: Short) => Some(v1 compareTo v2.toLong)
        case Some(v2: Int) => Some(v1 compareTo v2.toLong)
        case Some(v2: Long) => Some(v1 compareTo v2)
        case Some(v2: Float) => Some(v1.toDouble compareTo v2.toDouble)
        case Some(v2: Double) => Some(v1.toDouble compareTo v2)
        case None => None
    })
    
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
        override def get: Option[Long] = None
    }
    
    final case class LongConstant(value: Long) extends LongValue {
        override def get: Option[Long] = Some(value)
    }
    
    final case class LongCalculate(value: LongValue) extends LongValue {
        private val calculated: Option[Long] = value.get
        
        override def get: Option[Long] = calculated
    }
    
    final case class LongNegate(value: LongValue) extends LongValue {
        override def get: Option[Long] = value.get.map(v => -v)
    }
    
    final case class LongAdd(value1: LongValue, value2: LongValue) extends LongValue {
        override def get: Option[Long] = value1.get.flatMap(v1 => value2.get.map(v2 => v1 + v2))
    }
    
    final case class LongSubtract(value1: LongValue, value2: LongValue) extends LongValue {
        override def get: Option[Long] = value1.get.flatMap(v1 => value2.get.map(v2 => v1 - v2))
    }
    
    final case class LongMultiply(value1: LongValue, value2: LongValue) extends LongValue {
        override def get: Option[Long] = value1.get.flatMap(v1 => value2.get.map(v2 => v1 * v2))
    }
    
    final case class LongDivide(value1: LongValue, value2: LongValue) extends LongValue {
        override def get: Option[Long] = value1.get.flatMap(v1 => value2.get.filter(_ != 0).map(v2 => v1 / v2))
    }
    
    final case class LongMod(value1: LongValue, value2: LongValue) extends LongValue {
        override def get: Option[Long] = value1.get.flatMap(v1 => value2.get.filter(_ != 0).map(v2 => v1 % v2))
    }
    
    final case class NumericToLong(value: NumericValue) extends LongValue {
        override def get: Option[Long] = value.get match {
            case Some(v: Byte) => Some(v.toLong)
            case Some(v: Short) => Some(v.toLong)
            case Some(v: Int) => Some(v.toLong)
            case Some(v: Long) => Some(v.toLong)
            case Some(v: Float) => Some(v.toLong)
            case Some(v: Double) => Some(v.toLong)
            case _ => None
        }
    }
    
}
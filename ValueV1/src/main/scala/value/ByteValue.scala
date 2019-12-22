package value

abstract class ByteValue extends Value with NumericValue {
    override final protected type T = Byte
    
    override final def calculate: ByteValue = ByteValue.ByteCalculate(this)
    
    override protected[value] def compare(value: ComparableValue): Option[Int] = get.flatMap(v1 => value.get match {
        case Some(v2: Byte) => Some(v1 compareTo v2)
        case Some(v2: Short) => Some(v1.toShort compareTo v2)
        case Some(v2: Int) => Some(v1.toInt compareTo v2)
        case Some(v2: Long) => Some(v1.toLong compareTo v2)
        case Some(v2: Float) => Some(v1.toFloat compareTo v2)
        case Some(v2: Double) => Some(v1.toDouble compareTo v2)
        case None => None
    })
    
    final def unary_+ : ByteValue = this
    
    final def unary_- : ByteValue = ByteValue.ByteNegate(this)
    
    // add
    final def +(that: ByteValue): ByteValue = ByteValue.ByteAdd(this, that)
    
    final def +(that: ShortValue): ShortValue = ShortValue.ShortAdd(this.toShortValue, that)
    
    final def +(that: IntValue): IntValue = IntValue.IntAdd(this.toIntValue, that)
    
    final def +(that: LongValue): LongValue = LongValue.LongAdd(this.toLongValue, that)
    
    final def +(that: FloatValue): FloatValue = FloatValue.FloatAdd(this.toFloatValue, that)
    
    final def +(that: DoubleValue): DoubleValue = DoubleValue.DoubleAdd(this.toDoubleValue, that)
    
    // subtract
    final def -(that: ByteValue): ByteValue = ByteValue.ByteSubtract(this, that)
    
    final def -(that: ShortValue): ShortValue = ShortValue.ShortSubtract(this.toShortValue, that)
    
    final def -(that: IntValue): IntValue = IntValue.IntSubtract(this.toIntValue, that)
    
    final def -(that: LongValue): LongValue = LongValue.LongSubtract(this.toLongValue, that)
    
    final def -(that: FloatValue): FloatValue = FloatValue.FloatSubtract(this.toFloatValue, that)
    
    final def -(that: DoubleValue): DoubleValue = DoubleValue.DoubleSubtract(this.toDoubleValue, that)
    
    // multiply
    final def *(that: ByteValue): ByteValue = ByteValue.ByteMultiply(this, that)
    
    final def *(that: ShortValue): ShortValue = ShortValue.ShortMultiply(this.toShortValue, that)
    
    final def *(that: IntValue): IntValue = IntValue.IntMultiply(this.toIntValue, that)
    
    final def *(that: LongValue): LongValue = LongValue.LongMultiply(this.toLongValue, that)
    
    final def *(that: FloatValue): FloatValue = FloatValue.FloatMultiply(this.toFloatValue, that)
    
    final def *(that: DoubleValue): DoubleValue = DoubleValue.DoubleMultiply(this.toDoubleValue, that)
    
    // divide
    final def /(that: ByteValue): ByteValue = ByteValue.ByteDivide(this, that)
    
    final def /(that: ShortValue): ShortValue = ShortValue.ShortDivide(this.toShortValue, that)
    
    final def /(that: IntValue): IntValue = IntValue.IntDivide(this.toIntValue, that)
    
    final def /(that: LongValue): LongValue = LongValue.LongDivide(this.toLongValue, that)
    
    final def /(that: FloatValue): FloatValue = FloatValue.FloatDivide(this.toFloatValue, that)
    
    final def /(that: DoubleValue): DoubleValue = DoubleValue.DoubleDivide(this.toDoubleValue, that)
    
    // modulo
    final def %(that: ByteValue): ByteValue = ByteValue.ByteMod(this, that)
    
    final def %(that: ShortValue): ShortValue = ShortValue.ShortMod(this.toShortValue, that)
    
    final def %(that: IntValue): IntValue = IntValue.IntMod(this.toIntValue, that)
    
    final def %(that: LongValue): LongValue = LongValue.LongMod(this.toLongValue, that)
}

object ByteValue {
    
    final case object ByteNull extends ByteValue {
        override def get: Option[Byte] = None
    }
    
    final case class ByteConstant(value: Byte) extends ByteValue {
        override def get: Option[Byte] = Some(value)
    }
    
    final case class ByteCalculate(value: ByteValue) extends ByteValue {
        private val calculated: Option[Byte] = value.get
        
        override def get: Option[Byte] = calculated
    }
    
    final case class ByteNegate(value: ByteValue) extends ByteValue {
        override def get: Option[Byte] = value.get.map(v => (-v).toByte)
    }
    
    final case class ByteAdd(value1: ByteValue, value2: ByteValue) extends ByteValue {
        override def get: Option[Byte] = value1.get.flatMap(v1 => value2.get.map(v2 => (v1 + v2).toByte))
    }
    
    final case class ByteSubtract(value1: ByteValue, value2: ByteValue) extends ByteValue {
        override def get: Option[Byte] = value1.get.flatMap(v1 => value2.get.map(v2 => (v1 - v2).toByte))
    }
    
    final case class ByteMultiply(value1: ByteValue, value2: ByteValue) extends ByteValue {
        override def get: Option[Byte] = value1.get.flatMap(v1 => value2.get.map(v2 => (v1 * v2).toByte))
    }
    
    final case class ByteDivide(value1: ByteValue, value2: ByteValue) extends ByteValue {
        override def get: Option[Byte] = value1.get.flatMap(v1 => value2.get.filter(_ != 0).map(v2 => (v1 / v2).toByte))
    }
    
    final case class ByteMod(value1: ByteValue, value2: ByteValue) extends ByteValue {
        override def get: Option[Byte] = value1.get.flatMap(v1 => value2.get.filter(_ != 0).map(v2 => (v1 % v2).toByte))
    }
    
    final case class NumericToByte(value: NumericValue) extends ByteValue {
        override def get: Option[Byte] = value.get match {
            case Some(v: Byte) => Some(v.toByte)
            case Some(v: Short) => Some(v.toByte)
            case Some(v: Int) => Some(v.toByte)
            case Some(v: Long) => Some(v.toByte)
            case Some(v: Float) => Some(v.toByte)
            case Some(v: Double) => Some(v.toByte)
            case _ => None
        }
    }
    
}
package value

abstract class ShortValue extends Value with NumericValue {
    override final protected type T = Short
    
    override final def calculate: ShortValue = ShortValue.ShortCalculate(this)
    
    override protected[value] def compare(value: ComparableValue): Option[Int] = get.flatMap(v1 => value.get match {
        case Some(v2: Byte) => Some(v1 compareTo v2.toShort)
        case Some(v2: Short) => Some(v1 compareTo v2)
        case Some(v2: Int) => Some(v1.toInt compareTo v2)
        case Some(v2: Long) => Some(v1.toLong compareTo v2)
        case Some(v2: Float) => Some(v1.toFloat compareTo v2)
        case Some(v2: Double) => Some(v1.toDouble compareTo v2)
        case None => None
    })
    
    final def unary_+ : ShortValue = this
    
    final def unary_- : ShortValue = ShortValue.ShortNegate(this)
    
    // add
    final def +(that: ByteValue): ShortValue = ShortValue.ShortAdd(this, that.toShortValue)
    
    final def +(that: ShortValue): ShortValue = ShortValue.ShortAdd(this, that)
    
    final def +(that: IntValue): IntValue = IntValue.IntAdd(this.toIntValue, that)
    
    final def +(that: LongValue): LongValue = LongValue.LongAdd(this.toLongValue, that)
    
    final def +(that: FloatValue): FloatValue = FloatValue.FloatAdd(this.toFloatValue, that)
    
    final def +(that: DoubleValue): DoubleValue = DoubleValue.DoubleAdd(this.toDoubleValue, that)
    
    // subtract
    final def -(that: ByteValue): ShortValue = ShortValue.ShortSubtract(this, that.toShortValue)
    
    final def -(that: ShortValue): ShortValue = ShortValue.ShortSubtract(this, that)
    
    final def -(that: IntValue): IntValue = IntValue.IntSubtract(this.toIntValue, that)
    
    final def -(that: LongValue): LongValue = LongValue.LongSubtract(this.toLongValue, that)
    
    final def -(that: FloatValue): FloatValue = FloatValue.FloatSubtract(this.toFloatValue, that)
    
    final def -(that: DoubleValue): DoubleValue = DoubleValue.DoubleSubtract(this.toDoubleValue, that)
    
    // multiply
    final def *(that: ByteValue): ShortValue = ShortValue.ShortMultiply(this, that.toShortValue)
    
    final def *(that: ShortValue): ShortValue = ShortValue.ShortMultiply(this, that)
    
    final def *(that: IntValue): IntValue = IntValue.IntMultiply(this.toIntValue, that)
    
    final def *(that: LongValue): LongValue = LongValue.LongMultiply(this.toLongValue, that)
    
    final def *(that: FloatValue): FloatValue = FloatValue.FloatMultiply(this.toFloatValue, that)
    
    final def *(that: DoubleValue): DoubleValue = DoubleValue.DoubleMultiply(this.toDoubleValue, that)
    
    // divide
    final def /(that: ByteValue): ShortValue = ShortValue.ShortDivide(this, that.toShortValue)
    
    final def /(that: ShortValue): ShortValue = ShortValue.ShortDivide(this, that)
    
    final def /(that: IntValue): IntValue = IntValue.IntDivide(this.toIntValue, that)
    
    final def /(that: LongValue): LongValue = LongValue.LongDivide(this.toLongValue, that)
    
    final def /(that: FloatValue): FloatValue = FloatValue.FloatDivide(this.toFloatValue, that)
    
    final def /(that: DoubleValue): DoubleValue = DoubleValue.DoubleDivide(this.toDoubleValue, that)
    
    // modulo
    final def %(that: ByteValue): ShortValue = ShortValue.ShortMod(this, that.toShortValue)
    
    final def %(that: ShortValue): ShortValue = ShortValue.ShortMod(this, that)
    
    final def %(that: IntValue): IntValue = IntValue.IntMod(this.toIntValue, that)
    
    final def %(that: LongValue): LongValue = LongValue.LongMod(this.toLongValue, that)
}

object ShortValue {
    
    final case object ShortNull extends ShortValue {
        override def get: Option[Short] = None
    }
    
    final case class ShortConstant(value: Short) extends ShortValue {
        override def get: Option[Short] = Some(value)
    }
    
    final case class ShortCalculate(value: ShortValue) extends ShortValue {
        private val calculated: Option[Short] = value.get
        
        override def get: Option[Short] = calculated
    }
    
    final case class ShortNegate(value: ShortValue) extends ShortValue {
        override def get: Option[Short] = value.get.map(v => (-v).toShort)
    }
    
    final case class ShortAdd(value1: ShortValue, value2: ShortValue) extends ShortValue {
        override def get: Option[Short] = value1.get.flatMap(v1 => value2.get.map(v2 => (v1 + v2).toShort))
    }
    
    final case class ShortSubtract(value1: ShortValue, value2: ShortValue) extends ShortValue {
        override def get: Option[Short] = value1.get.flatMap(v1 => value2.get.map(v2 => (v1 - v2).toShort))
    }
    
    final case class ShortMultiply(value1: ShortValue, value2: ShortValue) extends ShortValue {
        override def get: Option[Short] = value1.get.flatMap(v1 => value2.get.map(v2 => (v1 * v2).toShort))
    }
    
    final case class ShortDivide(value1: ShortValue, value2: ShortValue) extends ShortValue {
        override def get: Option[Short] = value1.get.flatMap(v1 => value2.get.filter(_ != 0).map(v2 => (v1 / v2).toShort))
    }
    
    final case class ShortMod(value1: ShortValue, value2: ShortValue) extends ShortValue {
        override def get: Option[Short] = value1.get.flatMap(v1 => value2.get.filter(_ != 0).map(v2 => (v1 % v2).toShort))
    }
    
    final case class NumericToShort(value: NumericValue) extends ShortValue {
        override def get: Option[Short] = value.get match {
            case Some(v: Byte) => Some(v.toShort)
            case Some(v: Short) => Some(v.toShort)
            case Some(v: Int) => Some(v.toShort)
            case Some(v: Long) => Some(v.toShort)
            case Some(v: Float) => Some(v.toShort)
            case Some(v: Double) => Some(v.toShort)
            case _ => None
        }
    }
    
}
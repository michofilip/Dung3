package value

abstract class IntValue extends Value with NumericValue {
    override final protected type T = Int
    
    override final def calculate: IntValue = IntValue.IntCalculate(this)
    
    final def unary_+ : IntValue = this
    
    final def unary_- : IntValue = IntValue.IntNegate(this)
    
    //    // add
    //    final def +(that: ByteValue): IntValue = IntValue.IntAdd(this, that.toIntValue)
    //
    //    final def +(that: ShortValue): IntValue = IntValue.IntAdd(this, that.toIntValue)
    //
    //    final def +(that: IntValue): IntValue = IntValue.IntAdd(this, that)
    //
    //    final def +(that: LongValue): LongValue = LongValue.LongAdd(this.toLongValue, that)
    //
    //    final def +(that: FloatValue): FloatValue = FloatValue.FloatAdd(this.toFloatValue, that)
    //
    //    final def +(that: DoubleValue): DoubleValue = DoubleValue.DoubleAdd(this.toDoubleValue, that)
    //
    //    // subtract
    //    final def -(that: ByteValue): IntValue = IntValue.IntSubtract(this, that.toIntValue)
    //
    //    final def -(that: ShortValue): IntValue = IntValue.IntSubtract(this, that.toIntValue)
    //
    //    final def -(that: IntValue): IntValue = IntValue.IntSubtract(this, that)
    //
    //    final def -(that: LongValue): LongValue = LongValue.LongSubtract(this.toLongValue, that)
    //
    //    final def -(that: FloatValue): FloatValue = FloatValue.FloatSubtract(this.toFloatValue, that)
    //
    //    final def -(that: DoubleValue): DoubleValue = DoubleValue.DoubleSubtract(this.toDoubleValue, that)
    //
    //    // multiply
    //    final def *(that: ByteValue): IntValue = IntValue.IntMultiply(this, that.toIntValue)
    //
    //    final def *(that: ShortValue): IntValue = IntValue.IntMultiply(this, that.toIntValue)
    //
    //    final def *(that: IntValue): IntValue = IntValue.IntMultiply(this, that)
    //
    //    final def *(that: LongValue): LongValue = LongValue.LongMultiply(this.toLongValue, that)
    //
    //    final def *(that: FloatValue): FloatValue = FloatValue.FloatMultiply(this.toFloatValue, that)
    //
    //    final def *(that: DoubleValue): DoubleValue = DoubleValue.DoubleMultiply(this.toDoubleValue, that)
    //
    //    // divide
    //    final def /(that: ByteValue): IntValue = IntValue.IntDivide(this, that.toIntValue)
    //
    //    final def /(that: ShortValue): IntValue = IntValue.IntDivide(this, that.toIntValue)
    //
    //    final def /(that: IntValue): IntValue = IntValue.IntDivide(this, that)
    //
    //    final def /(that: LongValue): LongValue = LongValue.LongDivide(this.toLongValue, that)
    //
    //    final def /(that: FloatValue): FloatValue = FloatValue.FloatDivide(this.toFloatValue, that)
    //
    //    final def /(that: DoubleValue): DoubleValue = DoubleValue.DoubleDivide(this.toDoubleValue, that)
    //
    //    // modulo
    //    final def %(that: ByteValue): IntValue = IntValue.IntMod(this, that.toIntValue)
    //
    //    final def %(that: ShortValue): IntValue = IntValue.IntMod(this, that.toIntValue)
    //
    //    final def %(that: IntValue): IntValue = IntValue.IntMod(this, that)
    //
    //    final def %(that: LongValue): LongValue = LongValue.LongMod(this.toLongValue, that)
    
    override protected[value] def compare(value: ComparableValue): Option[Int] = get.flatMap(v1 => value.get match {
        case Some(v2: Byte) => Some(v1 compareTo v2.toInt)
        case Some(v2: Short) => Some(v1 compareTo v2.toInt)
        case Some(v2: Int) => Some(v1 compareTo v2)
        case Some(v2: Long) => Some(v1.toLong compareTo v2)
        case Some(v2: Float) => Some(v1.toDouble compareTo v2.toDouble)
        case Some(v2: Double) => Some(v1.toDouble compareTo v2)
        case None => None
    })
}

object IntValue {
    
    final case object IntNull extends IntValue {
        override def get: Option[Int] = None
    }
    
    final case class IntConstant(value: Int) extends IntValue {
        override def get: Option[Int] = Some(value)
    }
    
    final case class IntCalculate(value: IntValue) extends IntValue {
        private val calculated: Option[Int] = value.get
        
        override def get: Option[Int] = calculated
    }
    
    final case class IntNegate(value: IntValue) extends IntValue {
        override def get: Option[Int] = value.get.map(v => -v)
    }
    
    final case class IntAdd(value1: IntValue, value2: IntValue) extends IntValue {
        override def get: Option[Int] = value1.get.flatMap(v1 => value2.get.map(v2 => v1 + v2))
    }
    
    final case class IntSubtract(value1: IntValue, value2: IntValue) extends IntValue {
        override def get: Option[Int] = value1.get.flatMap(v1 => value2.get.map(v2 => v1 - v2))
    }
    
    final case class IntMultiply(value1: IntValue, value2: IntValue) extends IntValue {
        override def get: Option[Int] = value1.get.flatMap(v1 => value2.get.map(v2 => v1 * v2))
    }
    
    final case class IntDivide(value1: IntValue, value2: IntValue) extends IntValue {
        override def get: Option[Int] = value1.get.flatMap(v1 => value2.get.filter(v2 => v2 != 0).map(v2 => v1 / v2))
    }
    
    final case class IntMod(value1: IntValue, value2: IntValue) extends IntValue {
        override def get: Option[Int] = value1.get.flatMap(v1 => value2.get.filter(v2 => v2 != 0).map(v2 => v1 % v2))
    }
    
    final case class NumericToInt(value: NumericValue) extends IntValue {
        override def get: Option[Int] = value.get match {
            case Some(v: Byte) => Some(v.toInt)
            case Some(v: Short) => Some(v.toInt)
            case Some(v: Int) => Some(v.toInt)
            case Some(v: Long) => Some(v.toInt)
            case Some(v: Float) => Some(v.toInt)
            case Some(v: Double) => Some(v.toInt)
            case _ => None
        }
    }
    
}

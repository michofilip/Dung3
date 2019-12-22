package value

import value.StringValue.CharToString

abstract class StringValue extends Value {
    override final protected type T = String
    
    override final def calculate: StringValue = StringValue.StringCalculate(this)
    
    final def +(that: CharValue): StringValue = StringValue.Concatenate(this, CharToString(that))
    
    final def +(that: StringValue): StringValue = StringValue.Concatenate(this, that)
    
    final def length: IntValue = StringValue.Length(this)
}

object StringValue {
    
    final case object StringNull extends StringValue {
        override def get: Option[String] = None
    }
    
    final case class StringConstant(value: String) extends StringValue {
        override def get: Option[String] = Some(value)
    }
    
    final case class StringCalculate(value: StringValue) extends StringValue {
        private val calculated: Option[String] = value.get
        
        override def get: Option[String] = calculated
    }
    
    final case class Concatenate(value1: StringValue, value2: StringValue) extends StringValue {
        override def get: Option[String] = value1.get.flatMap(v1 => value2.get.map(v2 => v1 + v2))
    }
    
    final case class Length(value: StringValue) extends IntValue {
        override def get: Option[Int] = value.get.map(v => v.length)
    }
    
    final case class NumericToString(value: NumericValue) extends StringValue {
        override def get: Option[String] = value.get match {
            case Some(v: Byte) => Some(v.toString)
            case Some(v: Short) => Some(v.toString)
            case Some(v: Int) => Some(v.toString)
            case Some(v: Long) => Some(v.toString)
            case Some(v: Float) => Some(v.toString)
            case Some(v: Double) => Some(v.toString)
            case _ => None
        }
    }
    
    final case class CharToString(value: CharValue) extends StringValue {
        override def get: Option[String] = value.get.map(v => v.toString)
    }
    
}
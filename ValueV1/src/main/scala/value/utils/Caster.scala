package value.utils

object Caster {
    def toByte(value: AnyVal): Option[Byte] = value match {
        case v: Byte => Some(v.toByte)
        case v: Short => Some(v.toByte)
        case v: Int => Some(v.toByte)
        case v: Long => Some(v.toByte)
        case v: Float => Some(v.toByte)
        case v: Double => Some(v.toByte)
        case _ => None
    }
    
    def toShort(value: AnyVal): Option[Short] = value match {
        case v: Byte => Some(v.toShort)
        case v: Short => Some(v.toShort)
        case v: Int => Some(v.toShort)
        case v: Long => Some(v.toShort)
        case v: Float => Some(v.toShort)
        case v: Double => Some(v.toShort)
        case _ => None
    }
    
    def toInt(value: AnyVal): Option[Int] = value match {
        case v: Byte => Some(v.toInt)
        case v: Short => Some(v.toInt)
        case v: Int => Some(v.toInt)
        case v: Long => Some(v.toInt)
        case v: Float => Some(v.toInt)
        case v: Double => Some(v.toInt)
        case _ => None
    }
    
    def toLong(value: AnyVal): Option[Long] = value match {
        case v: Byte => Some(v.toLong)
        case v: Short => Some(v.toLong)
        case v: Int => Some(v.toLong)
        case v: Long => Some(v.toLong)
        case v: Float => Some(v.toLong)
        case v: Double => Some(v.toLong)
        case _ => None
    }
    
    def toFloat(value: AnyVal): Option[Float] = value match {
        case v: Byte => Some(v.toFloat)
        case v: Short => Some(v.toFloat)
        case v: Int => Some(v.toFloat)
        case v: Long => Some(v.toFloat)
        case v: Float => Some(v.toFloat)
        case v: Double => Some(v.toFloat)
        case _ => None
    }
    
    def toDouble(value: AnyVal): Option[Double] = value match {
        case v: Byte => Some(v.toDouble)
        case v: Short => Some(v.toDouble)
        case v: Int => Some(v.toDouble)
        case v: Long => Some(v.toDouble)
        case v: Float => Some(v.toDouble)
        case v: Double => Some(v.toDouble)
        case _ => None
    }
    
    def toString(value: AnyVal): Option[String] = value match {
        case v: Byte => Some(v.toString)
        case v: Short => Some(v.toString)
        case v: Int => Some(v.toString)
        case v: Long => Some(v.toString)
        case v: Float => Some(v.toString)
        case v: Double => Some(v.toString)
        case _ => None
    }
    
}

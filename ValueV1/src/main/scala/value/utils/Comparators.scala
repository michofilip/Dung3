package value.utils

object Comparators {
    def compareAnyVal(x: AnyVal, y: AnyVal): Option[Int] = (x, y) match {
        case (x: Byte, y: Byte) => Some(x compareTo y)
        case (x: Byte, y: Short) => Some(x.toShort compareTo y)
        case (x: Byte, y: Int) => Some(x.toInt compareTo y)
        case (x: Byte, y: Long) => Some(x.toLong compareTo y)
        case (x: Byte, y: Float) => Some(x.toFloat compareTo y)
        case (x: Byte, y: Double) => Some(x.toDouble compareTo y)
        
        case (x: Short, y: Byte) => Some(x compareTo y)
        case (x: Short, y: Short) => Some(x compareTo y)
        case (x: Short, y: Int) => Some(x.toInt compareTo y)
        case (x: Short, y: Long) => Some(x.toLong compareTo y)
        case (x: Short, y: Float) => Some(x.toFloat compareTo y)
        case (x: Short, y: Double) => Some(x.toDouble compareTo y)
        
        case (x: Int, y: Byte) => Some(x compareTo y)
        case (x: Int, y: Short) => Some(x compareTo y)
        case (x: Int, y: Int) => Some(x compareTo y)
        case (x: Int, y: Long) => Some(x.toLong compareTo y)
        case (x: Int, y: Float) => Some(x.toFloat compareTo y)
        case (x: Int, y: Double) => Some(x.toDouble compareTo y)
        
        case (x: Long, y: Byte) => Some(x compareTo y)
        case (x: Long, y: Short) => Some(x compareTo y)
        case (x: Long, y: Int) => Some(x compareTo y)
        case (x: Long, y: Long) => Some(x compareTo y)
        case (x: Long, y: Float) => Some(x.toDouble compareTo y)
        case (x: Long, y: Double) => Some(x.toDouble compareTo y)
        
        case (x: Float, y: Byte) => Some(x compareTo y)
        case (x: Float, y: Short) => Some(x compareTo y)
        case (x: Float, y: Int) => Some(x compareTo y)
        case (x: Float, y: Long) => Some(x compareTo y)
        case (x: Float, y: Float) => Some(x compareTo y)
        case (x: Float, y: Double) => Some(x.toDouble compareTo y)
        
        case (x: Double, y: Byte) => Some(x compareTo y)
        case (x: Double, y: Short) => Some(x compareTo y)
        case (x: Double, y: Int) => Some(x compareTo y)
        case (x: Double, y: Long) => Some(x compareTo y)
        case (x: Double, y: Float) => Some(x compareTo y)
        case (x: Double, y: Double) => Some(x compareTo y)
        
        case _ => None
    }
    
}

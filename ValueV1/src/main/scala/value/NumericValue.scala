package value

import context.ValueContext
import utils.Comparators

trait NumericValue extends Value with ComparableValue {
    
    override protected type T <: AnyVal
    
    override final protected[value] def compareTo(value: ComparableValue)(implicit valueContext: ValueContext): Option[Int] =
        value match {
            case value: NumericValue => get.flatMap(v1 => value.get.flatMap(v2 => Comparators.compareAnyVal(v1, v2)))
            case _ => None
        }
    
    final def toByteValue: ByteValue = ByteValue.NumericToByte(this)
    
    final def toShortValue: ShortValue = ShortValue.NumericToShort(this)
    
    final def toIntValue: IntValue = IntValue.NumericToInt(this)
    
    final def toLongValue: LongValue = LongValue.NumericToLong(this)
    
    final def toFloatValue: FloatValue = FloatValue.NumericToFloat(this)
    
    final def toDoubleValue: DoubleValue = DoubleValue.NumericToDouble(this)
}

package value.traits

import value.Value
import value.basic.{ByteValue, DoubleValue, FloatValue, IntValue, LongValue, ShortValue, StringValue}

trait NumericValue[V] extends Value[V] {
    def toByteValue: ByteValue = ByteValue.Numeric2Byte(this)

    def toShortValue: ShortValue = ShortValue.Numeric2Short(this)

    def toIntValue: IntValue = IntValue.Numeric2Int(this)

    def toLongValue: LongValue = LongValue.Numeric2Long(this)

    def toFloatValue: FloatValue = FloatValue.Numeric2Float(this)

    def toDoubleValue: DoubleValue = DoubleValue.Numeric2Double(this)

    def toStringValue: StringValue = StringValue.Numeric2String(this)
}

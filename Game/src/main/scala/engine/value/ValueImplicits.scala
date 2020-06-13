package engine.value

import engine.value.basic.{BooleanValue, ByteValue, CharValue, DoubleValue, FloatValue, IntValue, LongValue, ShortValue, StringValue}

import scala.language.implicitConversions

object ValueImplicits {

    implicit def boolean2Value(x: Boolean): BooleanValue = BooleanValue.BooleanConstant(x)

    implicit def byte2Value(x: Byte): ByteValue = ByteValue.ByteConstant(x)

    implicit def short2Value(x: Short): ShortValue = ShortValue.ShortConstant(x)

    implicit def int2Value(x: Int): IntValue = IntValue.IntConstant(x)

    implicit def long2Value(x: Long): LongValue = LongValue.LongConstant(x)

    implicit def float2Value(x: Float): FloatValue = FloatValue.FloatConstant(x)

    implicit def double2Value(x: Double): DoubleValue = DoubleValue.DoubleConstant(x)

    implicit def char2Value(x: Char): CharValue = CharValue.CharConstant(x)

    implicit def string2Value(x: String): StringValue = StringValue.StringConstant(x)

    implicit class Boolean2Value(value: Boolean) {
        def toValue: BooleanValue = value
    }

    implicit class Bite2Value(value: Byte) {
        def toValue: ByteValue = value
    }

    implicit class Short2Value(value: Short) {
        def toValue: ShortValue = value
    }

    implicit class Int2Value(value: Int) {
        def toValue: IntValue = value
    }

    implicit class Long2Value(value: Long) {
        def toValue: LongValue = value
    }

    implicit class Float2Value(value: Float) {
        def toValue: FloatValue = value
    }

    implicit class Double2Value(value: Double) {
        def toValue: DoubleValue = value
    }

    implicit class Char2Value(value: Char) {
        def toValue: CharValue = value
    }

    implicit class String2Value(value: String) {
        def toValue: StringValue = value
    }

}

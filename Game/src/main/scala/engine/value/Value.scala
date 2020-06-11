package engine.value

import engine.value.basic.{BooleanValue, ByteValue, CharValue, DoubleValue, FloatValue, IntValue, LongValue, ShortValue, StringValue}

abstract class Value[V] {
    def get(implicit gameContext: ValueContext): Option[V]

    def getOrElse(default: => V)(implicit gameContext: ValueContext): V =
        get.getOrElse(default)

    def ===[W](that: Value[W]): BooleanValue = BooleanValue.Equals(this, that)

    def !==[W](that: Value[W]): BooleanValue = BooleanValue.UnEquals(this, that)
}

object Value {

    def apply(x: Boolean): BooleanValue = BooleanValue.BooleanConstant(x)

    def apply(x: Byte): ByteValue = ByteValue.ByteConstant(x)

    def apply(x: Short): ShortValue = ShortValue.ShortConstant(x)

    def apply(x: Int): IntValue = IntValue.IntConstant(x)

    def apply(x: Long): LongValue = LongValue.LongConstant(x)

    def apply(x: Float): FloatValue = FloatValue.FloatConstant(x)

    def apply(x: Double): DoubleValue = DoubleValue.DoubleConstant(x)

    def apply(x: Char): CharValue = CharValue.CharConstant(x)

    def apply(x: String): StringValue = StringValue.StringConstant(x)
}

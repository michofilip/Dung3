package value.basic

import value.Value
import value.ValueTypes.{Binary, Provider, Unary}
import value.basic.StringValue.StringUnaryOperatorValue
import value.operators.BasicOperators
import value.traits.{BinaryOperatorValue, ProviderValue, UnaryOperatorValue}

sealed abstract class CharValue extends Value[Char] {
    def +(that: StringValue): StringValue = this.toStringValue + that

    def +(that: CharValue): StringValue = this.toStringValue + that

    def toStringValue: StringValue = CharValue.Char2String(this)
}

object CharValue {

    abstract class CharProviderValue[V](final override protected val provider: Provider[Char])
        extends CharValue with ProviderValue[Char]

    abstract class CharUnaryOperatorValue[V](final override protected val operator: Unary[V, Char])
        extends CharValue with UnaryOperatorValue[V, Char]

    abstract class CharBinaryOperatorValue[V1, V2](final override protected val operator: Binary[V1, V2, Char])
        extends CharValue with BinaryOperatorValue[V1, V2, Char]


    final case object CharNull extends CharProviderValue(BasicOperators.charConstant(None))

    final case class CharConstant(x: Char) extends CharProviderValue(BasicOperators.charConstant(Some(x)))

    final case class Char2String(value: CharValue) extends StringUnaryOperatorValue[Char](BasicOperators.char2string)

}
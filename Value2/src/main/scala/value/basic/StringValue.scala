package value.basic

import value.Value
import value.ValueTypes.{Binary, Provider, Unary}
import value.basic.IntValue.IntUnaryOperatorValue
import value.operators.BasicOperators
import value.traits.{BinaryOperatorValue, NumericValue, ProviderValue, UnaryOperatorValue}

sealed abstract class StringValue extends Value[String] {
    def +(that: StringValue): StringValue = StringValue.StringConcatenate(this, that)

    def +(that: CharValue): StringValue = StringValue.StringConcatenate(this, that.toStringValue)

    def length: IntValue = StringValue.StringLength(this)
}

object StringValue {

    abstract class StringProviderValue[V](final override protected val provider: Provider[String])
        extends StringValue with ProviderValue[String]

    abstract class StringUnaryOperatorValue[V](final override protected val operator: Unary[V, String])
        extends StringValue with UnaryOperatorValue[V, String]

    abstract class StringBinaryOperatorValue[V1, V2](final override protected val operator: Binary[V1, V2, String])
        extends StringValue with BinaryOperatorValue[V1, V2, String]


    final case object StringNull extends StringProviderValue(BasicOperators.stringConstant(None))

    final case class StringConstant(x: String) extends StringProviderValue(BasicOperators.stringConstant(Some(x)))

    final case class StringLength(value: StringValue) extends IntUnaryOperatorValue[String](BasicOperators.stringLength)

    final case class Numeric2String[V](value: NumericValue[V]) extends StringUnaryOperatorValue[V](BasicOperators.numeric2string)

    final case class StringConcatenate(value1: StringValue, value2: StringValue) extends StringBinaryOperatorValue[String, String](BasicOperators.stringConcatenate)

}
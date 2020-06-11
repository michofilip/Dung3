package value.basic

import value.ValueTypes.{Binary, Comparator, Provider, Unary}
import value.traits.{BinaryOperatorValue, ComparableValue, NumericValue, ProviderValue, UnaryOperatorValue}
import value.Value
import value.operators.BasicOperators

sealed abstract class FloatValue extends Value[Float] with ComparableValue[Float] with NumericValue[Float] {
    override val comparator: Comparator[Float] = BasicOperators.floatComparator

    def unary_+ : FloatValue = this

    def unary_- : FloatValue = FloatValue.FloatNegation(this)

    def +(that: FloatValue): FloatValue = FloatValue.FloatAddition(this, that)

    def -(that: FloatValue): FloatValue = FloatValue.FloatSubtraction(this, that)

    def *(that: FloatValue): FloatValue = FloatValue.FloatMultiplication(this, that)

    def /(that: FloatValue): FloatValue = FloatValue.FloatDivision(this, that)
}

object FloatValue {

    abstract class FloatProviderValue[V](final override protected val provider: Provider[Float])
        extends FloatValue with ProviderValue[Float]

    abstract class FloatUnaryOperatorValue[V](final override protected val operator: Unary[V, Float])
        extends FloatValue with UnaryOperatorValue[V, Float]

    abstract class FloatBinaryOperatorValue[V1, V2](final override protected val operator: Binary[V1, V2, Float])
        extends FloatValue with BinaryOperatorValue[V1, V2, Float]


    final case object FloatNull extends FloatProviderValue(BasicOperators.floatConstant(None))

    final case class FloatConstant(x: Float) extends FloatProviderValue(BasicOperators.floatConstant(Some(x)))

    final case class FloatNegation(value: FloatValue) extends FloatUnaryOperatorValue[Float](BasicOperators.floatNegation)

    final case class FloatAddition(value1: FloatValue, value2: FloatValue) extends FloatBinaryOperatorValue[Float, Float](BasicOperators.floatAddition)

    final case class FloatSubtraction(value1: FloatValue, value2: FloatValue) extends FloatBinaryOperatorValue[Float, Float](BasicOperators.floatSubtraction)

    final case class FloatMultiplication(value1: FloatValue, value2: FloatValue) extends FloatBinaryOperatorValue[Float, Float](BasicOperators.floatMultiplication)

    final case class FloatDivision(value1: FloatValue, value2: FloatValue) extends FloatBinaryOperatorValue[Float, Float](BasicOperators.floatDivision)


    final case class Numeric2Float[V](value: NumericValue[V]) extends FloatUnaryOperatorValue[V](BasicOperators.numeric2float)

}


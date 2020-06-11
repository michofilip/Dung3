package value.basic

import value.ValueTypes.{Binary, Comparator, Provider, Unary}
import value.traits.{BinaryOperatorValue, ComparableValue, NumericValue, ProviderValue, UnaryOperatorValue}
import value.Value
import value.operators.BasicOperators

sealed abstract class DoubleValue extends Value[Double] with ComparableValue[Double] with NumericValue[Double] {
    override val comparator: Comparator[Double] = BasicOperators.doubleComparator

    def unary_+ : DoubleValue = this

    def unary_- : DoubleValue = DoubleValue.DoubleNegation(this)

    def +(that: DoubleValue): DoubleValue = DoubleValue.DoubleAddition(this, that)

    def -(that: DoubleValue): DoubleValue = DoubleValue.DoubleSubtraction(this, that)

    def *(that: DoubleValue): DoubleValue = DoubleValue.DoubleMultiplication(this, that)

    def /(that: DoubleValue): DoubleValue = DoubleValue.DoubleDivision(this, that)
}

object DoubleValue {

    abstract class DoubleProviderValue[V](final override protected val provider: Provider[Double])
        extends DoubleValue with ProviderValue[Double]

    abstract class DoubleUnaryOperatorValue[V](final override protected val operator: Unary[V, Double])
        extends DoubleValue with UnaryOperatorValue[V, Double]

    abstract class DoubleBinaryOperatorValue[V1, V2](final override protected val operator: Binary[V1, V2, Double])
        extends DoubleValue with BinaryOperatorValue[V1, V2, Double]


    final case object DoubleNull extends DoubleProviderValue(BasicOperators.doubleConstant(None))

    final case class DoubleConstant(x: Double) extends DoubleProviderValue(BasicOperators.doubleConstant(Some(x)))

    final case class DoubleNegation(value: DoubleValue) extends DoubleUnaryOperatorValue[Double](BasicOperators.doubleNegation)

    final case class DoubleAddition(value1: DoubleValue, value2: DoubleValue) extends DoubleBinaryOperatorValue[Double, Double](BasicOperators.doubleAddition)

    final case class DoubleSubtraction(value1: DoubleValue, value2: DoubleValue) extends DoubleBinaryOperatorValue[Double, Double](BasicOperators.doubleSubtraction)

    final case class DoubleMultiplication(value1: DoubleValue, value2: DoubleValue) extends DoubleBinaryOperatorValue[Double, Double](BasicOperators.doubleMultiplication)

    final case class DoubleDivision(value1: DoubleValue, value2: DoubleValue) extends DoubleBinaryOperatorValue[Double, Double](BasicOperators.doubleDivision)


    final case class Numeric2Double[V](value: NumericValue[V]) extends DoubleUnaryOperatorValue[V](BasicOperators.numeric2double)

}


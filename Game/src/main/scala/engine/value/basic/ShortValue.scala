package engine.value.basic

import engine.value.Value
import engine.value.ValueTypes.{Binary, Comparator, Provider, Unary}
import engine.value.operators.BasicOperators
import engine.value.traits.{BinaryOperatorValue, ComparableValue, NumericValue, ProviderValue, UnaryOperatorValue}

sealed abstract class ShortValue extends Value[Short] with ComparableValue[Short] with NumericValue[Short] {
    override val comparator: Comparator[Short] = BasicOperators.shortComparator


    def unary_+ : ShortValue = this

    def unary_- : ShortValue = ShortValue.ShortNegation(this)

    def +(that: ShortValue): ShortValue = ShortValue.ShortAddition(this, that)

    def -(that: ShortValue): ShortValue = ShortValue.ShortSubtraction(this, that)

    def *(that: ShortValue): ShortValue = ShortValue.ShortMultiplication(this, that)

    def /(that: ShortValue): ShortValue = ShortValue.ShortDivision(this, that)

    def %(that: ShortValue): ShortValue = ShortValue.ShortReminder(this, that)

}

object ShortValue {

    abstract class ShortProviderValue[V](final override protected val provider: Provider[Short])
        extends ShortValue with ProviderValue[Short]

    abstract class ShortUnaryOperatorValue[V](final override protected val operator: Unary[V, Short])
        extends ShortValue with UnaryOperatorValue[V, Short]

    abstract class ShortBinaryOperatorValue[V1, V2](final override protected val operator: Binary[V1, V2, Short])
        extends ShortValue with BinaryOperatorValue[V1, V2, Short]


    final case object ShortNull extends ShortProviderValue(BasicOperators.shortConstant(None))

    final case class ShortConstant(x: Short) extends ShortProviderValue(BasicOperators.shortConstant(Some(x)))

    final case class ShortNegation(value: ShortValue) extends ShortUnaryOperatorValue[Short](BasicOperators.shortNegation)

    final case class ShortAddition(value1: ShortValue, value2: ShortValue) extends ShortBinaryOperatorValue[Short, Short](BasicOperators.shortAddition)

    final case class ShortSubtraction(value1: ShortValue, value2: ShortValue) extends ShortBinaryOperatorValue[Short, Short](BasicOperators.shortSubtraction)

    final case class ShortMultiplication(value1: ShortValue, value2: ShortValue) extends ShortBinaryOperatorValue[Short, Short](BasicOperators.shortMultiplication)

    final case class ShortDivision(value1: ShortValue, value2: ShortValue) extends ShortBinaryOperatorValue[Short, Short](BasicOperators.shortDivision)

    final case class ShortReminder(value1: ShortValue, value2: ShortValue) extends ShortBinaryOperatorValue[Short, Short](BasicOperators.shortReminder)


    final case class Numeric2Short[V](value: NumericValue[V]) extends ShortUnaryOperatorValue[V](BasicOperators.numeric2short)

}


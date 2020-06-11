package engine.value.basic

import engine.value.Value
import engine.value.ValueTypes.{Binary, Comparator, Provider, Unary}
import engine.value.operators.BasicOperators
import engine.value.traits.{ComparableValue, NumericValue, ProviderValue, UnaryOperatorValue}

sealed abstract class LongValue extends Value[Long] with ComparableValue[Long] with NumericValue[Long] {
    override val comparator: Comparator[Long] = BasicOperators.longComparator

    def unary_+ : LongValue = this

    def unary_- : LongValue = LongValue.LongNegation(this)

    def +(that: LongValue): LongValue = LongValue.LongAddition(this, that)

    def -(that: LongValue): LongValue = LongValue.LongSubtraction(this, that)

    def *(that: LongValue): LongValue = LongValue.LongMultiplication(this, that)

    def /(that: LongValue): LongValue = LongValue.LongDivision(this, that)

    def %(that: LongValue): LongValue = LongValue.LongReminder(this, that)

}

object LongValue {

    abstract class LongProviderValue[V](final override protected val provider: Provider[Long])
        extends LongValue with ProviderValue[Long]

    abstract class LongUnaryOperatorValue[V](final override protected val operator: Unary[V, Long])
        extends LongValue with UnaryOperatorValue[V, Long]

    abstract class LongBinaryOperatorValue[V1, V2](final override protected val operator: Binary[V1, V2, Long])
        extends LongValue with BinaryOperatorValue[V1, V2, Long]


    final case object LongNull extends LongProviderValue(BasicOperators.longConstant(None))

    final case class LongConstant(x: Long) extends LongProviderValue(BasicOperators.longConstant(Some(x)))

    final case class LongNegation(value: LongValue) extends LongUnaryOperatorValue[Long](BasicOperators.longNegation)

    final case class LongAddition(value1: LongValue, value2: LongValue) extends LongBinaryOperatorValue[Long, Long](BasicOperators.longAddition)

    final case class LongSubtraction(value1: LongValue, value2: LongValue) extends LongBinaryOperatorValue[Long, Long](BasicOperators.longSubtraction)

    final case class LongMultiplication(value1: LongValue, value2: LongValue) extends LongBinaryOperatorValue[Long, Long](BasicOperators.longMultiplication)

    final case class LongDivision(value1: LongValue, value2: LongValue) extends LongBinaryOperatorValue[Long, Long](BasicOperators.longDivision)

    final case class LongReminder(value1: LongValue, value2: LongValue) extends LongBinaryOperatorValue[Long, Long](BasicOperators.longReminder)


    final case class Numeric2Long[V](value: NumericValue[V]) extends LongUnaryOperatorValue[V](BasicOperators.numeric2long)

}


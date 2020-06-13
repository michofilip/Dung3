package engine.value.basic

import engine.value.Value
import engine.value.ValueTypes.{Binary, Comparator, Provider, Unary}
import engine.value.operators.BasicOperators
import engine.value.traits.{BinaryOperatorValue, ComparableValue, NumericValue, ProviderValue, UnaryOperatorValue}

sealed abstract class IntValue extends Value[Int] with ComparableValue[Int] with NumericValue[Int] {
    override val comparator: Comparator[Int] = BasicOperators.intComparator

    def unary_+ : IntValue = this

    def unary_- : IntValue = IntValue.IntNegation(this)

    def +(that: IntValue): IntValue = IntValue.IntAddition(this, that)

    def -(that: IntValue): IntValue = IntValue.IntSubtraction(this, that)

    def *(that: IntValue): IntValue = IntValue.IntMultiplication(this, that)

    def /(that: IntValue): IntValue = IntValue.IntDivision(this, that)

    def %(that: IntValue): IntValue = IntValue.IntReminder(this, that)

}

object IntValue {

    abstract class IntProviderValue[V](final override protected val provider: Provider[Int])
        extends IntValue with ProviderValue[Int]

    abstract class IntUnaryOperatorValue[V](final override protected val operator: Unary[V, Int])
        extends IntValue with UnaryOperatorValue[V, Int]

    abstract class IntBinaryOperatorValue[V1, V2](final override protected val operator: Binary[V1, V2, Int])
        extends IntValue with BinaryOperatorValue[V1, V2, Int]


    final case object IntNull extends IntProviderValue(BasicOperators.intConstant(None))

    final case class IntConstant(x: Int) extends IntProviderValue(BasicOperators.intConstant(Some(x)))

    final case class IntNegation(value: IntValue) extends IntUnaryOperatorValue[Int](BasicOperators.intNegation)

    final case class IntAddition(value1: IntValue, value2: IntValue) extends IntBinaryOperatorValue[Int, Int](BasicOperators.intAddition)

    final case class IntSubtraction(value1: IntValue, value2: IntValue) extends IntBinaryOperatorValue[Int, Int](BasicOperators.intSubtraction)

    final case class IntMultiplication(value1: IntValue, value2: IntValue) extends IntBinaryOperatorValue[Int, Int](BasicOperators.intMultiplication)

    final case class IntDivision(value1: IntValue, value2: IntValue) extends IntBinaryOperatorValue[Int, Int](BasicOperators.intDivision)

    final case class IntReminder(value1: IntValue, value2: IntValue) extends IntBinaryOperatorValue[Int, Int](BasicOperators.intReminder)


    final case class Numeric2Int[V](value: NumericValue[V]) extends IntUnaryOperatorValue[V](BasicOperators.numeric2int)

}
package value.basic

import value.ValueTypes.{Binary, Comparator, Provider, Unary}
import value.traits.{BinaryOperatorValue, ComparableValue, NumericValue, ProviderValue, UnaryOperatorValue}
import value.Value
import value.operators.BasicOperators

sealed abstract class ByteValue extends Value[Byte] with ComparableValue[Byte] with NumericValue[Byte] {
    override val comparator: Comparator[Byte] = BasicOperators.byteComparator


    def unary_+ : ByteValue = this

    def unary_- : ByteValue = ByteValue.ByteNegation(this)

    def +(that: ByteValue): ByteValue = ByteValue.ByteAddition(this, that)

    def -(that: ByteValue): ByteValue = ByteValue.ByteSubtraction(this, that)

    def *(that: ByteValue): ByteValue = ByteValue.ByteMultiplication(this, that)

    def /(that: ByteValue): ByteValue = ByteValue.ByteDivision(this, that)

    def %(that: ByteValue): ByteValue = ByteValue.ByteReminder(this, that)

}

object ByteValue {

    abstract class ByteProviderValue[V](final override protected val provider: Provider[Byte])
        extends ByteValue with ProviderValue[Byte]

    abstract class ByteUnaryOperatorValue[V](final override protected val operator: Unary[V, Byte])
        extends ByteValue with UnaryOperatorValue[V, Byte]

    abstract class ByteBinaryOperatorValue[V1, V2](final override protected val operator: Binary[V1, V2, Byte])
        extends ByteValue with BinaryOperatorValue[V1, V2, Byte]


    final case object ByteNull extends ByteProviderValue(BasicOperators.byteConstant(None))

    final case class ByteConstant(x: Byte) extends ByteProviderValue(BasicOperators.byteConstant(Some(x)))

    final case class ByteNegation(value: ByteValue) extends ByteUnaryOperatorValue[Byte](BasicOperators.byteNegation)

    final case class ByteAddition(value1: ByteValue, value2: ByteValue) extends ByteBinaryOperatorValue[Byte, Byte](BasicOperators.byteAddition)

    final case class ByteSubtraction(value1: ByteValue, value2: ByteValue) extends ByteBinaryOperatorValue[Byte, Byte](BasicOperators.byteSubtraction)

    final case class ByteMultiplication(value1: ByteValue, value2: ByteValue) extends ByteBinaryOperatorValue[Byte, Byte](BasicOperators.byteMultiplication)

    final case class ByteDivision(value1: ByteValue, value2: ByteValue) extends ByteBinaryOperatorValue[Byte, Byte](BasicOperators.byteDivision)

    final case class ByteReminder(value1: ByteValue, value2: ByteValue) extends ByteBinaryOperatorValue[Byte, Byte](BasicOperators.byteReminder)


    final case class Numeric2Byte[V](value: NumericValue[V]) extends ByteUnaryOperatorValue[V](BasicOperators.numeric2byte)

}
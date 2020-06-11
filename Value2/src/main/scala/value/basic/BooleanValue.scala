package value.basic

import value.ValueTypes.{Binary, Provider, Unary}
import value.traits.{BinaryOperatorValue, ComparableValue, ProviderValue, UnaryOperatorValue}
import value.Value
import value.operators.BasicOperators

sealed abstract class BooleanValue extends Value[Boolean] {
    def unary_! : BooleanValue = BooleanValue.NOT(this)

    def &&(that: BooleanValue): BooleanValue = BooleanValue.AND(this, that)

    def ||(that: BooleanValue): BooleanValue = BooleanValue.OR(this, that)

    def &!&(that: BooleanValue): BooleanValue = BooleanValue.NAND(this, that)

    def |!|(that: BooleanValue): BooleanValue = BooleanValue.NOR(this, that)

    def >=<(that: BooleanValue): BooleanValue = BooleanValue.XOR(this, that)

    def <=>(that: BooleanValue): BooleanValue = BooleanValue.XNOR(this, that)
}

object BooleanValue {

    abstract class BooleanProviderValue[V](final override protected val provider: Provider[Boolean])
        extends BooleanValue with ProviderValue[Boolean]

    abstract class BooleanUnaryOperatorValue[V](final override protected val operator: Unary[V, Boolean])
        extends BooleanValue with UnaryOperatorValue[V, Boolean]

    abstract class BooleanBinaryOperatorValue[V1, V2](final override protected val operator: Binary[V1, V2, Boolean])
        extends BooleanValue with BinaryOperatorValue[V1, V2, Boolean]


    final case object BooleanNull extends BooleanProviderValue(BasicOperators.boolConstant(None))

    final case class BooleanConstant(x: Boolean) extends BooleanProviderValue(BasicOperators.boolConstant(Some(x)))

    final case class NOT(value: BooleanValue) extends BooleanUnaryOperatorValue[Boolean](BasicOperators.NOT)

    final case class AND(value1: BooleanValue, value2: BooleanValue) extends BooleanBinaryOperatorValue[Boolean, Boolean](BasicOperators.AND)

    final case class OR(value1: BooleanValue, value2: BooleanValue) extends BooleanBinaryOperatorValue[Boolean, Boolean](BasicOperators.OR)

    final case class NAND(value1: BooleanValue, value2: BooleanValue) extends BooleanBinaryOperatorValue[Boolean, Boolean](BasicOperators.NAND)

    final case class NOR(value1: BooleanValue, value2: BooleanValue) extends BooleanBinaryOperatorValue[Boolean, Boolean](BasicOperators.NOR)

    final case class XOR(value1: BooleanValue, value2: BooleanValue) extends BooleanBinaryOperatorValue[Boolean, Boolean](BasicOperators.XOR)

    final case class XNOR(value1: BooleanValue, value2: BooleanValue) extends BooleanBinaryOperatorValue[Boolean, Boolean](BasicOperators.XNOR)


    final case class Equals[V1, V2](value1: Value[V1], value2: Value[V2]) extends BooleanBinaryOperatorValue[V1, V2](BasicOperators.eq)

    final case class UnEquals[V1, V2](value1: Value[V1], value2: Value[V2]) extends BooleanBinaryOperatorValue[V1, V2](BasicOperators.neq)

    final case class Less[V](value1: ComparableValue[V], value2: ComparableValue[V]) extends BooleanBinaryOperatorValue[V, V](BasicOperators.lt(value1.comparator))

    final case class LessEquals[V](value1: ComparableValue[V], value2: ComparableValue[V]) extends BooleanBinaryOperatorValue[V, V](BasicOperators.leq(value1.comparator))

    final case class Greater[V](value1: ComparableValue[V], value2: ComparableValue[V]) extends BooleanBinaryOperatorValue[V, V](BasicOperators.gt(value1.comparator))

    final case class GreaterEquals[V](value1: ComparableValue[V], value2: ComparableValue[V]) extends BooleanBinaryOperatorValue[V, V](BasicOperators.geq(value1.comparator))

}

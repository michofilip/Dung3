package value.operators

import java.lang

import value.ValueTypes.{Binary, Comparator, Provider, Unary}

object BasicOperators {

    // constants
    val boolConstant: Option[Boolean] => Provider[Boolean] = x => _ => x
    val byteConstant: Option[Byte] => Provider[Byte] = x => _ => x
    val shortConstant: Option[Short] => Provider[Short] = x => _ => x
    val intConstant: Option[Int] => Provider[Int] = x => _ => x
    val longConstant: Option[Long] => Provider[Long] = x => _ => x
    val floatConstant: Option[Float] => Provider[Float] = x => _ => x
    val doubleConstant: Option[Double] => Provider[Double] = x => _ => x
    val charConstant: Option[Char] => Provider[Char] = x => _ => x
    val stringConstant: Option[String] => Provider[String] = x => _ => x

    // logic operations
    val NOT: Unary[Boolean, Boolean] = x => Some(!x)
    val AND: Binary[Boolean, Boolean, Boolean] = (x, y) => Some(x && y)
    val OR: Binary[Boolean, Boolean, Boolean] = (x, y) => Some(x || y)
    val NAND: Binary[Boolean, Boolean, Boolean] = (x, y) => Some(!(x && y))
    val NOR: Binary[Boolean, Boolean, Boolean] = (x, y) => Some(!(x || y))
    val XOR: Binary[Boolean, Boolean, Boolean] = (x, y) => Some((x && !y) || (!x && y))
    val XNOR: Binary[Boolean, Boolean, Boolean] = (x, y) => Some((x && y) || (!x && !y))

    // byte operations
    val byteNegation: Unary[Byte, Byte] = x => Some((-x).toByte)
    val byteAddition: Binary[Byte, Byte, Byte] = (x, y) => Some((x + y).toByte)
    val byteSubtraction: Binary[Byte, Byte, Byte] = (x, y) => Some((x - y).toByte)
    val byteMultiplication: Binary[Byte, Byte, Byte] = (x, y) => Some((x * y).toByte)
    val byteDivision: Binary[Byte, Byte, Byte] = (x, y) => if (y != 0) Some((x / y).toByte) else None
    val byteReminder: Binary[Byte, Byte, Byte] = (x, y) => if (y != 0) Some((x % y).toByte) else None

    // short operations
    val shortNegation: Unary[Short, Short] = x => Some((-x).toShort)
    val shortAddition: Binary[Short, Short, Short] = (x, y) => Some((x + y).toShort)
    val shortSubtraction: Binary[Short, Short, Short] = (x, y) => Some((x - y).toShort)
    val shortMultiplication: Binary[Short, Short, Short] = (x, y) => Some((x * y).toShort)
    val shortDivision: Binary[Short, Short, Short] = (x, y) => if (y != 0) Some((x / y).toShort) else None
    val shortReminder: Binary[Short, Short, Short] = (x, y) => if (y != 0) Some((x % y).toShort) else None

    // int operations
    val intNegation: Unary[Int, Int] = x => Some(-x)
    val intAddition: Binary[Int, Int, Int] = (x, y) => Some(x + y)
    val intSubtraction: Binary[Int, Int, Int] = (x, y) => Some(x - y)
    val intMultiplication: Binary[Int, Int, Int] = (x, y) => Some(x * y)
    val intDivision: Binary[Int, Int, Int] = (x, y) => if (y != 0) Some(x / y) else None
    val intReminder: Binary[Int, Int, Int] = (x, y) => if (y != 0) Some(x % y) else None

    // long operations
    val longNegation: Unary[Long, Long] = x => Some(-x)
    val longAddition: Binary[Long, Long, Long] = (x, y) => Some(x + y)
    val longSubtraction: Binary[Long, Long, Long] = (x, y) => Some(x - y)
    val longMultiplication: Binary[Long, Long, Long] = (x, y) => Some(x * y)
    val longDivision: Binary[Long, Long, Long] = (x, y) => if (y != 0) Some(x / y) else None
    val longReminder: Binary[Long, Long, Long] = (x, y) => if (y != 0) Some(x % y) else None

    // float operations
    val floatNegation: Unary[Float, Float] = x => Some(-x)
    val floatAddition: Binary[Float, Float, Float] = (x, y) => Some(x + y)
    val floatSubtraction: Binary[Float, Float, Float] = (x, y) => Some(x - y)
    val floatMultiplication: Binary[Float, Float, Float] = (x, y) => Some(x * y)
    val floatDivision: Binary[Float, Float, Float] = (x, y) => if (y != 0) Some(x / y) else None

    // double operations
    val doubleNegation: Unary[Double, Double] = x => Some(-x)
    val doubleAddition: Binary[Double, Double, Double] = (x, y) => Some(x + y)
    val doubleSubtraction: Binary[Double, Double, Double] = (x, y) => Some(x - y)
    val doubleMultiplication: Binary[Double, Double, Double] = (x, y) => Some(x * y)
    val doubleDivision: Binary[Double, Double, Double] = (x, y) => if (y != 0) Some(x / y) else None

    // char / string operations
    val stringConcatenate: Binary[String, String, String] = (x, y) => Some(x + y)
    val stringLength: Unary[String, Int] = x => Some(x.length)
    val char2string: Unary[Char, String] = x => Some(x.toString)
    val numeric2string: Unary[Any, String] = {
        case x: Byte => Some(x.toString)
        case x: Short => Some(x.toString)
        case x: Int => Some(x.toString)
        case x: Long => Some(x.toString)
        case x: Float => Some(x.toString)
        case x: Double => Some(x.toString)
        case _ => None
    }

    // casters
    val numeric2byte: Unary[Any, Byte] = {
        case x: Byte => Some(x.toByte)
        case x: Short => Some(x.toByte)
        case x: Int => Some(x.toByte)
        case x: Long => Some(x.toByte)
        case x: Float => Some(x.toByte)
        case x: Double => Some(x.toByte)
        case _ => None
    }
    val numeric2short: Unary[Any, Short] = {
        case x: Byte => Some(x.toShort)
        case x: Short => Some(x.toShort)
        case x: Int => Some(x.toShort)
        case x: Long => Some(x.toShort)
        case x: Float => Some(x.toShort)
        case x: Double => Some(x.toShort)
        case _ => None
    }
    val numeric2int: Unary[Any, Int] = {
        case x: Byte => Some(x.toInt)
        case x: Short => Some(x.toInt)
        case x: Int => Some(x.toInt)
        case x: Long => Some(x.toInt)
        case x: Float => Some(x.toInt)
        case x: Double => Some(x.toInt)
        case _ => None
    }
    val numeric2long: Unary[Any, Long] = {
        case x: Byte => Some(x.toLong)
        case x: Short => Some(x.toLong)
        case x: Int => Some(x.toLong)
        case x: Long => Some(x.toLong)
        case x: Float => Some(x.toLong)
        case x: Double => Some(x.toLong)
        case _ => None
    }
    val numeric2float: Unary[Any, Float] = {
        case x: Byte => Some(x.toFloat)
        case x: Short => Some(x.toFloat)
        case x: Int => Some(x.toFloat)
        case x: Long => Some(x.toFloat)
        case x: Float => Some(x.toFloat)
        case x: Double => Some(x.toFloat)
        case _ => None
    }
    val numeric2double: Unary[Any, Double] = {
        case x: Byte => Some(x.toDouble)
        case x: Short => Some(x.toDouble)
        case x: Int => Some(x.toDouble)
        case x: Long => Some(x.toDouble)
        case x: Float => Some(x.toDouble)
        case x: Double => Some(x.toDouble)
        case _ => None
    }

    // comparators
    val byteComparator: Comparator[Byte] = (x, y) => Some(lang.Byte.compare(x, y))
    val shortComparator: Comparator[Short] = (x, y) => Some(lang.Short.compare(x, y))
    val intComparator: Comparator[Int] = (x, y) => Some(lang.Integer.compare(x, y))
    val longComparator: Comparator[Long] = (x, y) => Some(lang.Long.compare(x, y))
    val floatComparator: Comparator[Float] = (x, y) => Some(lang.Float.compare(x, y))
    val doubleComparator: Comparator[Double] = (x, y) => Some(lang.Double.compare(x, y))

    // ordering
    def eq[V1, V2]: Binary[V1, V2, Boolean] = (x, y) => Some(x == y)

    def neq[V1, V2]: Binary[V1, V2, Boolean] = (x, y) => Some(x != y)

    def lt[V](comparator: Comparator[V]): Binary[V, V, Boolean] = (x, y) => comparator(x, y).map(v => v < 0)

    def leq[V](comparator: Comparator[V]): Binary[V, V, Boolean] = (x, y) => comparator(x, y).map(v => v <= 0)

    def gt[V](comparator: Comparator[V]): Binary[V, V, Boolean] = (x, y) => comparator(x, y).map(v => v > 0)

    def geq[V](comparator: Comparator[V]): Binary[V, V, Boolean] = (x, y) => comparator(x, y).map(v => v >= 0)
}

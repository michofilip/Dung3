package commons.utils

import org.scalatest.funsuite.AnyFunSuite

class CastersTest extends AnyFunSuite {
    private val byteValue = 1.toByte
    private val shortValue = 1.toShort
    private val intValue = 1.toInt
    private val longValue = 1.toLong
    private val floatValue = 1.toFloat
    private val doubleValue = 1.toDouble
    private val integerStringValue = "1"
    private val decimalStringValue = "1.0"
    
    test("Casters.toByte of AnyVal should be Option[Byte]") {
        assert(Casters.toByte(byteValue).exists(p => p.isInstanceOf[Byte]))
        assert(Casters.toByte(shortValue).exists(p => p.isInstanceOf[Byte]))
        assert(Casters.toByte(intValue).exists(p => p.isInstanceOf[Byte]))
        assert(Casters.toByte(longValue).exists(p => p.isInstanceOf[Byte]))
        assert(Casters.toByte(floatValue).exists(p => p.isInstanceOf[Byte]))
        assert(Casters.toByte(doubleValue).exists(p => p.isInstanceOf[Byte]))
    }
    
    test("Casters.toShort of AnyVal should be Option[Short]") {
        assert(Casters.toShort(byteValue).exists(p => p.isInstanceOf[Short]))
        assert(Casters.toShort(shortValue).exists(p => p.isInstanceOf[Short]))
        assert(Casters.toShort(intValue).exists(p => p.isInstanceOf[Short]))
        assert(Casters.toShort(longValue).exists(p => p.isInstanceOf[Short]))
        assert(Casters.toShort(floatValue).exists(p => p.isInstanceOf[Short]))
        assert(Casters.toShort(doubleValue).exists(p => p.isInstanceOf[Short]))
    }
    
    test("Casters.toInt of AnyVal should be Option[Int]") {
        assert(Casters.toInt(byteValue).exists(p => p.isInstanceOf[Int]))
        assert(Casters.toInt(shortValue).exists(p => p.isInstanceOf[Int]))
        assert(Casters.toInt(intValue).exists(p => p.isInstanceOf[Int]))
        assert(Casters.toInt(longValue).exists(p => p.isInstanceOf[Int]))
        assert(Casters.toInt(floatValue).exists(p => p.isInstanceOf[Int]))
        assert(Casters.toInt(doubleValue).exists(p => p.isInstanceOf[Int]))
    }
    
    test("Casters.toLong of AnyVal should be Option[Long]") {
        assert(Casters.toLong(byteValue).exists(p => p.isInstanceOf[Long]))
        assert(Casters.toLong(shortValue).exists(p => p.isInstanceOf[Long]))
        assert(Casters.toLong(intValue).exists(p => p.isInstanceOf[Long]))
        assert(Casters.toLong(longValue).exists(p => p.isInstanceOf[Long]))
        assert(Casters.toLong(floatValue).exists(p => p.isInstanceOf[Long]))
        assert(Casters.toLong(doubleValue).exists(p => p.isInstanceOf[Long]))
    }
    
    test("Casters.toFloat of AnyVal should be Option[Float]") {
        assert(Casters.toFloat(byteValue).exists(p => p.isInstanceOf[Float]))
        assert(Casters.toFloat(shortValue).exists(p => p.isInstanceOf[Float]))
        assert(Casters.toFloat(intValue).exists(p => p.isInstanceOf[Float]))
        assert(Casters.toFloat(longValue).exists(p => p.isInstanceOf[Float]))
        assert(Casters.toFloat(floatValue).exists(p => p.isInstanceOf[Float]))
        assert(Casters.toFloat(doubleValue).exists(p => p.isInstanceOf[Float]))
    }
    
    test("Casters.toDouble of AnyVal should be Option[Double]") {
        assert(Casters.toDouble(byteValue).exists(p => p.isInstanceOf[Double]))
        assert(Casters.toDouble(shortValue).exists(p => p.isInstanceOf[Double]))
        assert(Casters.toDouble(intValue).exists(p => p.isInstanceOf[Double]))
        assert(Casters.toDouble(longValue).exists(p => p.isInstanceOf[Double]))
        assert(Casters.toDouble(floatValue).exists(p => p.isInstanceOf[Double]))
        assert(Casters.toDouble(doubleValue).exists(p => p.isInstanceOf[Double]))
    }
    
    test("Casters.toString of AnyVal should be Option[String]") {
        assertResult(Some(integerStringValue))(Casters.toString(byteValue))
        assertResult(Some(integerStringValue))(Casters.toString(shortValue))
        assertResult(Some(integerStringValue))(Casters.toString(intValue))
        assertResult(Some(integerStringValue))(Casters.toString(longValue))
        assertResult(Some(decimalStringValue))(Casters.toString(floatValue))
        assertResult(Some(decimalStringValue))(Casters.toString(doubleValue))
    }
}

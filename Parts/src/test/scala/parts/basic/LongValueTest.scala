package parts.basic

import org.scalatest.funsuite.AnyFunSuite
import parts.value.ValueContext
import parts.value.basic.LongValue.{LongConstant, LongNull}

class LongValueTest extends AnyFunSuite {
    implicit private val valueContext: ValueContext = new ValueContext {}
    
    test("LongNull should get None") {
        // given
        val v = LongNull
        
        // then
        assertResult(None)(v.get)
    }
    
    test("LongConstant(3) should get Some(3)") {
        // given
        val v = LongConstant(3)
        
        // then
        assertResult(Some(3))(v.get)
    }
    
    test("-LongConstant(3) should get Some(-3)") {
        // given
        val v = LongConstant(3)
        
        // when
        val result = -v
        
        // then
        assertResult(Some(-3))(result.get)
    }
    
    test("-LongNull should get None") {
        // given
        val v = LongNull
        
        // when
        val result = -v
        
        // then
        assertResult(None)(result.get)
    }
    
    test("LongConstant(2) + LongConstant(3) should get Some(5)") {
        // given
        val v1 = LongConstant(2)
        val v2 = LongConstant(3)
        
        // when
        val result = v1 + v2
        
        // then
        assertResult(Some(5))(result.get)
    }
    
    test("LongConstant(2) + LongNull should get None") {
        // given
        val v1 = LongConstant(2)
        val v2 = LongNull
        
        // when
        val result = v1 + v2
        
        // then
        assertResult(None)(result.get)
    }
    
    test("LongConstant(2) - LongConstant(3) should get Some(-1)") {
        // given
        val v1 = LongConstant(2)
        val v2 = LongConstant(3)
        
        // when
        val result = v1 - v2
        
        // then
        assertResult(Some(-1))(result.get)
    }
    
    test("LongConstant(2) - LongNull should get None") {
        // given
        val v1 = LongConstant(2)
        val v2 = LongNull
        
        // when
        val result = v1 - v2
        
        // then
        assertResult(None)(result.get)
    }
    
    test("LongConstant(2) * LongConstant(3) should get Some(6)") {
        // given
        val v1 = LongConstant(2)
        val v2 = LongConstant(3)
        
        // when
        val result = v1 * v2
        
        // then
        assertResult(Some(6))(result.get)
    }
    
    test("LongConstant(2) * LongNull should get None") {
        // given
        val v1 = LongConstant(2)
        val v2 = LongNull
        
        // when
        val result = v1 * v2
        
        // then
        assertResult(None)(result.get)
    }
    
    test("LongConstant(6) / LongConstant(2) should get Some(3)") {
        // given
        val v1 = LongConstant(6)
        val v2 = LongConstant(2)
        
        // when
        val result = v1 / v2
        
        // then
        assertResult(Some(3))(result.get)
    }
    
    test("LongConstant(6) / LongConstant(4) should get Some(1)") {
        // given
        val v1 = LongConstant(6)
        val v2 = LongConstant(4)
        
        // when
        val result = v1 / v2
        
        // then
        assertResult(Some(1))(result.get)
    }
    
    test("LongConstant(6) / LongConstant(0) should get None") {
        // given
        val v1 = LongConstant(6)
        val v2 = LongConstant(0)
        
        // when
        val result = v1 / v2
        
        // then
        assertResult(None)(result.get)
    }
    
    test("LongConstant(6) / LongNull should get None") {
        // given
        val v1 = LongConstant(6)
        val v2 = LongNull
        
        // when
        val result = v1 / v2
        
        // then
        assertResult(None)(result.get)
    }
    
    test("LongConstant(3) % LongConstant(2) should get Some(1)") {
        // given
        val v1 = LongConstant(3)
        val v2 = LongConstant(2)
        
        // when
        val result = v1 % v2
        
        // then
        assertResult(Some(1))(result.get)
    }
    
    test("LongConstant(3) % LongConstant(0) should get None") {
        // given
        val v1 = LongConstant(3)
        val v2 = LongConstant(0)
        
        // when
        val result = v1 % v2
        
        // then
        assertResult(None)(result.get)
    }
    
    test("LongConstant(3) % LongNull should get None") {
        // given
        val v1 = LongConstant(3)
        val v2 = LongNull
        
        // when
        val result = v1 % v2
        
        // then
        assertResult(None)(result.get)
    }
}

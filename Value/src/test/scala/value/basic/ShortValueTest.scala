package value.basic

import org.scalatest.funsuite.AnyFunSuite
import value.ValueContext
import value.basic.ShortValue.{ShortConstant, ShortNull}

class ShortValueTest extends AnyFunSuite {
    implicit private val valueContext: ValueContext = new ValueContext {}
    
    test("ShortNull should get None") {
        // given
        val v = ShortNull
        
        // then
        assertResult(None)(v.get)
    }
    
    test("ShortConstant(3) should get Some(3)") {
        // given
        val v = ShortConstant(3)
        
        // then
        assertResult(Some(3))(v.get)
    }
    
    test("-ShortConstant(3) should get Some(-3)") {
        // given
        val v = ShortConstant(3)
        
        // when
        val result = -v
        
        // then
        assertResult(Some(-3))(result.get)
    }
    
    test("-ShortNull should get None") {
        // given
        val v = ShortNull
        
        // when
        val result = -v
        
        // then
        assertResult(None)(result.get)
    }
    
    test("ShortConstant(2) + ShortConstant(3) should get Some(5)") {
        // given
        val v1 = ShortConstant(2)
        val v2 = ShortConstant(3)
        
        // when
        val result = v1 + v2
        
        // then
        assertResult(Some(5))(result.get)
    }
    
    test("ShortConstant(2) + ShortNull should get None") {
        // given
        val v1 = ShortConstant(2)
        val v2 = ShortNull
        
        // when
        val result = v1 + v2
        
        // then
        assertResult(None)(result.get)
    }
    
    test("ShortConstant(2) - ShortConstant(3) should get Some(-1)") {
        // given
        val v1 = ShortConstant(2)
        val v2 = ShortConstant(3)
        
        // when
        val result = v1 - v2
        
        // then
        assertResult(Some(-1))(result.get)
    }
    
    test("ShortConstant(2) - ShortNull should get None") {
        // given
        val v1 = ShortConstant(2)
        val v2 = ShortNull
        
        // when
        val result = v1 - v2
        
        // then
        assertResult(None)(result.get)
    }
    
    test("ShortConstant(2) * ShortConstant(3) should get Some(6)") {
        // given
        val v1 = ShortConstant(2)
        val v2 = ShortConstant(3)
        
        // when
        val result = v1 * v2
        
        // then
        assertResult(Some(6))(result.get)
    }
    
    test("ShortConstant(2) * ShortNull should get None") {
        // given
        val v1 = ShortConstant(2)
        val v2 = ShortNull
        
        // when
        val result = v1 * v2
        
        // then
        assertResult(None)(result.get)
    }
    
    test("ShortConstant(6) / ShortConstant(2) should get Some(3)") {
        // given
        val v1 = ShortConstant(6)
        val v2 = ShortConstant(2)
        
        // when
        val result = v1 / v2
        
        // then
        assertResult(Some(3))(result.get)
    }
    
    test("ShortConstant(6) / ShortConstant(4) should get Some(1)") {
        // given
        val v1 = ShortConstant(6)
        val v2 = ShortConstant(4)
        
        // when
        val result = v1 / v2
        
        // then
        assertResult(Some(1))(result.get)
    }
    
    test("ShortConstant(6) / ShortConstant(0) should get None") {
        // given
        val v1 = ShortConstant(6)
        val v2 = ShortConstant(0)
        
        // when
        val result = v1 / v2
        
        // then
        assertResult(None)(result.get)
    }
    
    test("ShortConstant(6) / ShortNull should get None") {
        // given
        val v1 = ShortConstant(6)
        val v2 = ShortNull
        
        // when
        val result = v1 / v2
        
        // then
        assertResult(None)(result.get)
    }
    
    test("ShortConstant(3) % ShortConstant(2) should get Some(1)") {
        // given
        val v1 = ShortConstant(3)
        val v2 = ShortConstant(2)
        
        // when
        val result = v1 % v2
        
        // then
        assertResult(Some(1))(result.get)
    }
    
    test("ShortConstant(3) % ShortConstant(0) should get None") {
        // given
        val v1 = ShortConstant(3)
        val v2 = ShortConstant(0)
        
        // when
        val result = v1 % v2
        
        // then
        assertResult(None)(result.get)
    }
    
    test("ShortConstant(3) % ShortNull should get None") {
        // given
        val v1 = ShortConstant(3)
        val v2 = ShortNull
        
        // when
        val result = v1 % v2
        
        // then
        assertResult(None)(result.get)
    }
}

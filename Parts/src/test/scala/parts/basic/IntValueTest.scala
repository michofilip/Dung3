package parts.basic

import org.scalatest.funsuite.AnyFunSuite
import parts.value.ValueContext
import parts.value.basic.IntValue.{IntConstant, IntNull}

class IntValueTest extends AnyFunSuite {
    implicit private val valueContext: ValueContext = new ValueContext {}
    
    test("IntNull should get None") {
        // given
        val v = IntNull
        
        // then
        assertResult(None)(v.get)
    }
    
    test("IntConstant(3) should get Some(3)") {
        // given
        val v = IntConstant(3)
        
        // then
        assertResult(Some(3))(v.get)
    }
    
    test("-IntConstant(3) should get Some(-3)") {
        // given
        val v = IntConstant(3)
        
        // when
        val result = -v
        
        // then
        assertResult(Some(-3))(result.get)
    }
    
    test("-IntNull should get None") {
        // given
        val v = IntNull
        
        // when
        val result = -v
        
        // then
        assertResult(None)(result.get)
    }
    
    test("IntConstant(2) + IntConstant(3) should get Some(5)") {
        // given
        val v1 = IntConstant(2)
        val v2 = IntConstant(3)
        
        // when
        val result = v1 + v2
        
        // then
        assertResult(Some(5))(result.get)
    }
    
    test("IntConstant(2) + IntNull should get None") {
        // given
        val v1 = IntConstant(2)
        val v2 = IntNull
        
        // when
        val result = v1 + v2
        
        // then
        assertResult(None)(result.get)
    }
    
    test("IntConstant(2) - IntConstant(3) should get Some(-1)") {
        // given
        val v1 = IntConstant(2)
        val v2 = IntConstant(3)
        
        // when
        val result = v1 - v2
        
        // then
        assertResult(Some(-1))(result.get)
    }
    
    test("IntConstant(2) - IntNull should get None") {
        // given
        val v1 = IntConstant(2)
        val v2 = IntNull
        
        // when
        val result = v1 - v2
        
        // then
        assertResult(None)(result.get)
    }
    
    test("IntConstant(2) * IntConstant(3) should get Some(6)") {
        // given
        val v1 = IntConstant(2)
        val v2 = IntConstant(3)
        
        // when
        val result = v1 * v2
        
        // then
        assertResult(Some(6))(result.get)
    }
    
    test("IntConstant(2) * IntNull should get None") {
        // given
        val v1 = IntConstant(2)
        val v2 = IntNull
        
        // when
        val result = v1 * v2
        
        // then
        assertResult(None)(result.get)
    }
    
    test("IntConstant(6) / IntConstant(2) should get Some(3)") {
        // given
        val v1 = IntConstant(6)
        val v2 = IntConstant(2)
        
        // when
        val result = v1 / v2
        
        // then
        assertResult(Some(3))(result.get)
    }
    
    test("IntConstant(6) / IntConstant(4) should get Some(1)") {
        // given
        val v1 = IntConstant(6)
        val v2 = IntConstant(4)
        
        // when
        val result = v1 / v2
        
        // then
        assertResult(Some(1))(result.get)
    }
    
    test("IntConstant(6) / IntConstant(0) should get None") {
        // given
        val v1 = IntConstant(6)
        val v2 = IntConstant(0)
        
        // when
        val result = v1 / v2
        
        // then
        assertResult(None)(result.get)
    }
    
    test("IntConstant(6) / IntNull should get None") {
        // given
        val v1 = IntConstant(6)
        val v2 = IntNull
        
        // when
        val result = v1 / v2
        
        // then
        assertResult(None)(result.get)
    }
    
    test("IntConstant(3) % IntConstant(2) should get Some(1)") {
        // given
        val v1 = IntConstant(3)
        val v2 = IntConstant(2)
        
        // when
        val result = v1 % v2
        
        // then
        assertResult(Some(1))(result.get)
    }
    
    test("IntConstant(3) % IntConstant(0) should get None") {
        // given
        val v1 = IntConstant(3)
        val v2 = IntConstant(0)
        
        // when
        val result = v1 % v2
        
        // then
        assertResult(None)(result.get)
    }
    
    test("IntConstant(3) % IntNull should get None") {
        // given
        val v1 = IntConstant(3)
        val v2 = IntNull
        
        // when
        val result = v1 % v2
        
        // then
        assertResult(None)(result.get)
    }
}

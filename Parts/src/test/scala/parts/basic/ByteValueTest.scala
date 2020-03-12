package parts.basic

import org.scalatest.funsuite.AnyFunSuite
import parts.value.ValueContext
import parts.value.basic.ByteValue.{ByteConstant, ByteNull}

class ByteValueTest extends AnyFunSuite {
    implicit private val valueContext: ValueContext = new ValueContext {}
    
    test("ByteNull should get None") {
        // given
        val v = ByteNull
        
        // then
        assertResult(None)(v.get)
    }
    
    test("ByteConstant(3) should get Some(3)") {
        // given
        val v = ByteConstant(3)
        
        // then
        assertResult(Some(3))(v.get)
    }
    
    test("-ByteConstant(3) should get Some(-3)") {
        // given
        val v = ByteConstant(3)
        
        // when
        val result = -v
        
        // then
        assertResult(Some(-3))(result.get)
    }
    
    test("-ByteNull should get None") {
        // given
        val v = ByteNull
        
        // when
        val result = -v
        
        // then
        assertResult(None)(result.get)
    }
    
    test("ByteConstant(2) + ByteConstant(3) should get Some(5)") {
        // given
        val v1 = ByteConstant(2)
        val v2 = ByteConstant(3)
        
        // when
        val result = v1 + v2
        
        // then
        assertResult(Some(5))(result.get)
    }
    
    test("ByteConstant(2) + ByteNull should get None") {
        // given
        val v1 = ByteConstant(2)
        val v2 = ByteNull
        
        // when
        val result = v1 + v2
        
        // then
        assertResult(None)(result.get)
    }
    
    test("ByteConstant(2) - ByteConstant(3) should get Some(-1)") {
        // given
        val v1 = ByteConstant(2)
        val v2 = ByteConstant(3)
        
        // when
        val result = v1 - v2
        
        // then
        assertResult(Some(-1))(result.get)
    }
    
    test("ByteConstant(2) - ByteNull should get None") {
        // given
        val v1 = ByteConstant(2)
        val v2 = ByteNull
        
        // when
        val result = v1 - v2
        
        // then
        assertResult(None)(result.get)
    }
    
    test("ByteConstant(2) * ByteConstant(3) should get Some(6)") {
        // given
        val v1 = ByteConstant(2)
        val v2 = ByteConstant(3)
        
        // when
        val result = v1 * v2
        
        // then
        assertResult(Some(6))(result.get)
    }
    
    test("ByteConstant(2) * ByteNull should get None") {
        // given
        val v1 = ByteConstant(2)
        val v2 = ByteNull
        
        // when
        val result = v1 * v2
        
        // then
        assertResult(None)(result.get)
    }
    
    test("ByteConstant(6) / ByteConstant(2) should get Some(3)") {
        // given
        val v1 = ByteConstant(6)
        val v2 = ByteConstant(2)
        
        // when
        val result = v1 / v2
        
        // then
        assertResult(Some(3))(result.get)
    }
    
    test("ByteConstant(6) / ByteConstant(4) should get Some(1)") {
        // given
        val v1 = ByteConstant(6)
        val v2 = ByteConstant(4)
        
        // when
        val result = v1 / v2
        
        // then
        assertResult(Some(1))(result.get)
    }
    
    test("ByteConstant(6) / ByteConstant(0) should get None") {
        // given
        val v1 = ByteConstant(6)
        val v2 = ByteConstant(0)
        
        // when
        val result = v1 / v2
        
        // then
        assertResult(None)(result.get)
    }
    
    test("ByteConstant(6) / ByteNull should get None") {
        // given
        val v1 = ByteConstant(6)
        val v2 = ByteNull
        
        // when
        val result = v1 / v2
        
        // then
        assertResult(None)(result.get)
    }
    
    test("ByteConstant(3) % ByteConstant(2) should get Some(1)") {
        // given
        val v1 = ByteConstant(3)
        val v2 = ByteConstant(2)
        
        // when
        val result = v1 % v2
        
        // then
        assertResult(Some(1))(result.get)
    }
    
    test("ByteConstant(3) % ByteConstant(0) should get None") {
        // given
        val v1 = ByteConstant(3)
        val v2 = ByteConstant(0)
        
        // when
        val result = v1 % v2
        
        // then
        assertResult(None)(result.get)
    }
    
    test("ByteConstant(3) % ByteNull should get None") {
        // given
        val v1 = ByteConstant(3)
        val v2 = ByteNull
        
        // when
        val result = v1 % v2
        
        // then
        assertResult(None)(result.get)
    }
}

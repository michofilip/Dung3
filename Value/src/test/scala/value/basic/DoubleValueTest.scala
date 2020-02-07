package value.basic

import commons.utils.MathUtils
import org.scalatest.funsuite.AnyFunSuite
import value.ValueContext
import value.basic.DoubleValue.{DoubleConstant, DoubleNull}

class DoubleValueTest extends AnyFunSuite {
    implicit private val valueContext: ValueContext = new ValueContext {}
    
    test("DoubleNull should get None") {
        // given
        val v = DoubleNull
        
        // then
        assertResult(None)(v.get.map(MathUtils.round(_, 10)))
    }
    
    test("DoubleConstant(3.14) should get Some(3.14)") {
        // given
        val v = DoubleConstant(3.14)
        
        // then
        assertResult(Some(3.14))(v.get.map(MathUtils.round(_, 10)))
    }
    
    test("-DoubleConstant(3.14) should get Some(-3.14)") {
        // given
        val v = DoubleConstant(3.14)
        
        // when
        val result = -v
        
        // then
        assertResult(Some(-3.14))(result.get.map(MathUtils.round(_, 10)))
    }
    
    test("-DoubleNull should get None") {
        // given
        val v = DoubleNull
        
        // when
        val result = -v
        
        // then
        assertResult(None)(result.get.map(MathUtils.round(_, 10)))
    }
    
    test("DoubleConstant(2.71) + DoubleConstant(3.14) should get Some(5.85)") {
        // given
        val v1 = DoubleConstant(2.71)
        val v2 = DoubleConstant(3.14)
        
        // when
        val result = v1 + v2
        
        // then
        assertResult(Some(5.85))(result.get.map(MathUtils.round(_, 10)))
    }
    
    test("DoubleConstant(2.71) + DoubleNull should get None") {
        // given
        val v1 = DoubleConstant(2.71)
        val v2 = DoubleNull
        
        // when
        val result = v1 + v2
        
        // then
        assertResult(None)(result.get.map(MathUtils.round(_, 10)))
    }
    
    test("DoubleConstant(2.71) - DoubleConstant(3.14) should get Some(-0,43)") {
        // given
        val v1 = DoubleConstant(2.71)
        val v2 = DoubleConstant(3.14)
        
        // when
        val result = v1 - v2
        
        // then
        assertResult(Some(-0.43))(result.get.map(MathUtils.round(_, 10)))
    }
    
    test("DoubleConstant(2.71) - DoubleNull should get None") {
        // given
        val v1 = DoubleConstant(2.71)
        val v2 = DoubleNull
        
        // when
        val result = v1 - v2
        
        // then
        assertResult(None)(result.get.map(MathUtils.round(_, 10)))
    }
    
    test("DoubleConstant(2.71) * DoubleConstant(3.14) should get Some(8.5094)") {
        // given
        val v1 = DoubleConstant(2.71)
        val v2 = DoubleConstant(3.14)
        
        // when
        val result = v1 * v2
        
        // then
        assertResult(Some(8.5094))(result.get.map(MathUtils.round(_, 10)))
    }
    
    test("DoubleConstant(2.71) * DoubleNull should get None") {
        // given
        val v1 = DoubleConstant(2.71)
        val v2 = DoubleNull
        
        // when
        val result = v1 * v2
        
        // then
        assertResult(None)(result.get.map(MathUtils.round(_, 10)))
    }
    
    test("DoubleConstant(8.5094) / DoubleConstant(3.14) should get Some(2.71)") {
        // given
        val v1 = DoubleConstant(8.5094)
        val v2 = DoubleConstant(3.14)
        
        // when
        val result = v1 / v2
        
        // then
        assertResult(Some(2.71))(result.get.map(MathUtils.round(_, 10)))
    }
    
    test("DoubleConstant(8.5094) / DoubleConstant(0) should get None") {
        // given
        val v1 = DoubleConstant(8.5094)
        val v2 = DoubleConstant(0)
        
        // when
        val result = v1 / v2
        
        // then
        assertResult(None)(result.get.map(MathUtils.round(_, 10)))
    }
    
    test("DoubleConstant(8.5094) / DoubleNull should get None") {
        // given
        val v1 = DoubleConstant(8.5094)
        val v2 = DoubleNull
        
        // when
        val result = v1 / v2
        
        // then
        assertResult(None)(result.get.map(MathUtils.round(_, 10)))
    }
}

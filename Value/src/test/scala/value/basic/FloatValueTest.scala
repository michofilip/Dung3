package value.basic

import commons.utils.MathUtils
import org.scalatest.funsuite.AnyFunSuite
import value.ValueContext
import value.basic.FloatValue.{FloatConstant, FloatNull}

class FloatValueTest extends AnyFunSuite {
    implicit private val valueContext: ValueContext = new ValueContext {}
    
    test("FloatNull should get None") {
        // given
        val v = FloatNull
        
        // then
        assertResult(None)(v.get.map(MathUtils.round(_, 5)))
    }
    
    test("FloatConstant(3.14) should get Some(3.14)") {
        // given
        val v = FloatValue.FloatConstant(3.14F)
        
        // then
        assertResult(Some(3.14))(v.get.map(MathUtils.round(_, 5)))
    }
    
    test("-FloatConstant(3.14) should get Some(-3.14)") {
        // given
        val v = FloatConstant(3.14F)
        
        // when
        val result = -v
        
        // then
        assertResult(Some(-3.14))(result.get.map(MathUtils.round(_, 5)))
    }
    
    test("-FloatNull should get None") {
        // given
        val v = FloatNull
        
        // when
        val result = -v
        
        // then
        assertResult(None)(result.get.map(MathUtils.round(_, 5)))
    }
    
    test("FloatConstant(2.71) + FloatConstant(3.14) should get Some(5.85)") {
        // given
        val v1 = FloatConstant(2.71F)
        val v2 = FloatConstant(3.14F)
        
        // when
        val result = v1 + v2
        
        // then
        assertResult(Some(5.85))(result.get.map(MathUtils.round(_, 5)))
    }
    
    test("FloatConstant(2.71) + FloatNull should get None") {
        // given
        val v1 = FloatConstant(2.71F)
        val v2 = FloatNull
        
        // when
        val result = v1 + v2
        
        // then
        assertResult(None)(result.get.map(MathUtils.round(_, 5)))
    }
    
    test("FloatConstant(2.71) - FloatConstant(3.14) should get Some(-0,43)") {
        // given
        val v1 = FloatConstant(2.71F)
        val v2 = FloatConstant(3.14F)
        
        // when
        val result = v1 - v2
        
        // then
        assertResult(Some(-0.43))(result.get.map(MathUtils.round(_, 5)))
    }
    
    test("FloatConstant(2.71) - FloatNull should get None") {
        // given
        val v1 = FloatConstant(2.71F)
        val v2 = FloatNull
        
        // when
        val result = v1 - v2
        
        // then
        assertResult(None)(result.get.map(MathUtils.round(_, 5)))
    }
    
    test("FloatConstant(2.71) * FloatConstant(3.14) should get Some(8.5094)") {
        // given
        val v1 = FloatConstant(2.71F)
        val v2 = FloatConstant(3.14F)
        
        // when
        val result = v1 * v2
        
        // then
        assertResult(Some(8.5094))(result.get.map(MathUtils.round(_, 5)))
    }
    
    test("FloatConstant(2.71) * FloatNull should get None") {
        // given
        val v1 = FloatConstant(2.71F)
        val v2 = FloatNull
        
        // when
        val result = v1 * v2
        
        // then
        assertResult(None)(result.get.map(MathUtils.round(_, 5)))
    }
    
    test("FloatConstant(8.5094) / FloatConstant(3.14) should get Some(2.71)") {
        // given
        val v1 = FloatConstant(8.5094F)
        val v2 = FloatConstant(3.14F)
        
        // when
        val result = v1 / v2
        
        // then
        assertResult(Some(2.71))(result.get.map(MathUtils.round(_, 5)))
    }
    
    test("FloatConstant(8.5094) / FloatConstant(0) should get None") {
        // given
        val v1 = FloatConstant(8.5094F)
        val v2 = FloatConstant(0)
        
        // when
        val result = v1 / v2
        
        // then
        assertResult(None)(result.get.map(MathUtils.round(_, 5)))
    }
    
    test("FloatConstant(8.5094) / FloatNull should get None") {
        // given
        val v1 = FloatConstant(8.5094F)
        val v2 = FloatNull
        
        // when
        val result = v1 / v2
        
        // then
        assertResult(None)(result.get.map(MathUtils.round(_, 5)))
    }
}

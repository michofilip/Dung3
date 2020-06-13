package utils

import engine.utils.MathUtils._
import org.scalatest.funsuite.AnyFunSuite

class MathUtilsTest extends AnyFunSuite {
    test("1 mod 3 should be 1") {
        assertResult(1)(1 %% 3)
    }
    
    test("4 mod 3 should be 1") {
        assertResult(1)(4 %% 3)
    }
    
    test("-2 mod 3 should be 1") {
        assertResult(1)(-2 %% 3)
    }
    
    test("floor(3.14) should be 3") {
        assertResult(3)(floor(3.14))
    }
    
    test("floor(-3.14) should be -4") {
        assertResult(-4)(floor(-3.14))
    }
    
    test("ceil(3.14) should be 4") {
        assertResult(4)(ceil(3.14))
    }
    
    test("ceil(-3.14) should be -3") {
        assertResult(-3)(ceil(-3.14))
    }
    
    test("0 bound by (1, 3) should be 1") {
        assertResult(1)(1 <| 0 |> 3)
    }
    
    test("1 bound by (1, 3) should be 1") {
        assertResult(1)(1 <| 1 |> 3)
    }
    
    test("2 bound by (1, 3) should be 2") {
        assertResult(2)(1 <| 2 |> 3)
    }
    
    test("3 bound by (1, 3) should be 3") {
        assertResult(3)(1 <| 3 |> 3)
    }
    
    test("4 bound by (1, 3) should be 3") {
        assertResult(3)(1 <| 4 |> 3)
    }
}

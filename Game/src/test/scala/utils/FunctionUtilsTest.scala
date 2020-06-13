package utils

import commons.utils.FunctionUtils._
import org.scalatest.funsuite.AnyFunSuite

class FunctionUtilsTest extends AnyFunSuite {

    private val f1: Int => Int = x => x + 1
    private val f2: Int => Int = x => 2 * x

    test("(f1 --> f2)(1) should be 4") {
        assertResult(4)((f1 --> f2) (1))
    }

    test("(f2 --> f1)(1) should be 3") {
        assertResult(3)((f2 --> f1) (1))
    }
}

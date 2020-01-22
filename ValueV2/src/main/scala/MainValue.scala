import context.ValueContext
import value.DoubleValue.DoubleConstant
import value.IntValue.IntConstant
import value.ValueImports._
import value.{DoubleValue, IntValue}

object MainValue extends App {
    implicit val vc: ValueContext = new ValueContext {}
    
    val v1: IntValue = IntConstant(4)
    val v2: IntValue = IntConstant(3)
    val v3: DoubleValue = DoubleConstant(3.14)
    
    println(v1)
    println(v2)
    println(v3)
    println(v1.compareTo(v2))
    println(v1.compareTo(v3))
    println(v3.compareTo(v1))
}

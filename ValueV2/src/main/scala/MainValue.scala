import value.ValueContext
import value.basic.DoubleValue.DoubleConstant
import value.basic.IntValue.IntConstant
import value.basic.converters.ValueImplicits._
import value.basic.{DoubleValue, IntValue}

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
    println((v1 > v2).get)
}

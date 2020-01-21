import context.ValueContext
import value.IntValue.IntConstant

object MainValue extends App {
    implicit val vc: ValueContext = new ValueContext {}
    
    val v1: IntConstant = IntConstant(2)
    val v2: IntConstant = IntConstant(3)
    
    println(v1)
    println(v2)
    println(v1.compareTo2(v2))
}

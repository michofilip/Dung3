import _root_.value.Value
import _root_.value.basic.BooleanValue
import _root_.value.basic.BooleanValue.BooleanConstant
import _root_.value.basic.IntValue.IntConstant
import scripts.Statement
import scripts.Statements._

object MainScript extends App {
    val condition: BooleanValue = BooleanConstant(true)
    val value1: Value = IntConstant(2)
    
    val x1: Statement =
        when(condition).therefore(
            execute(),
            execute()
        ).otherwise(when(condition).therefore(
            execute(),
            execute()
        )).otherwise(
            execute(),
            execute()
        )
    
    val x2: Statement =
        loop(condition).body(
            execute(),
            execute()
        )
    
    val x3: Statement =
        choose(value1).variants(
            value(IntConstant(1)).therefore(
                execute(),
                execute()
            ),
            value(IntConstant(2), IntConstant(3)).therefore(
                execute(),
                execute()
            ),
            value(IntConstant(4)).when(condition).therefore(
                execute(),
                execute()
            ),
            value(IntConstant(4)).therefore(
                execute(),
                execute()
            )
        ).otherwise(
            execute(),
            execute()
        )
    
    println(x1)
    println(x2)
    println(x3)
}

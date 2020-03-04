import scripts.Statement
import scripts.Statements._
import value.basic.BooleanValue
import value.basic.BooleanValue.BooleanConstant

object MainScript extends App {
    val condition: BooleanValue = BooleanConstant(true)
    
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
    
    println(x1)
}

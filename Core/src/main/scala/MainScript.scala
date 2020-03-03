import scripts.Statement
import scripts.Statements._
import value.basic.BooleanValue
import value.basic.BooleanValue.BooleanConstant

object MainScript extends App {
    val condition: BooleanValue = BooleanConstant(true)
    
    private val otherwise: Statement =
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
    
    println(otherwise)
}

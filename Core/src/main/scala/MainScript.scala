import _root_.value.Value
import _root_.value.basic.BooleanValue
import _root_.value.basic.BooleanValue.BooleanConstant
import _root_.value.basic.IntValue.IntConstant
import statement.Statements._
import statement.{Statement, StatementCompiler}

object MainScript extends App {
    val condition: BooleanValue = BooleanConstant(false)
    val value: Value = IntConstant(0)
    
    val x1: Statement =
        when(condition).therefore(
            execute(),
            execute()
        ).otherwiseWhen(condition).therefore(
            execute(),
            execute()
        ).otherwise(
            execute(),
            execute()
        )
    
    val x2: Statement =
        loop(condition).body(
            execute(),
            execute()
        )
    
    val x3: Statement =
        choose(value).from(
            variant(IntConstant(1)).therefore(
                execute(),
                execute()
            ),
            variant(IntConstant(2), IntConstant(3)).therefore(
                execute(),
                execute()
            ),
            variant(IntConstant(4)).when(condition).therefore(
                execute(),
                execute()
            ),
            variant(IntConstant(4)).therefore(
                execute(),
                execute()
            )
        ).otherwise(
            execute(),
            execute()
        )
    
    //    println(x1)
    //    println(x2)
    //    println(x3)
    
    val x0 =
        choose(value).from(
            variant(IntConstant(1), IntConstant(2), IntConstant(3)).when(condition).therefore(
                execute()
            )
        ).otherwise(
            execute()
        )
    
    StatementCompiler.compileToScript(x0).instructions.foreach(println)
}

package scripts

import scripts.Statement.{Block, Choose, Execute, Loop, Variant, When}
import value.Value
import value.basic.BooleanValue

object Statements {
    def execute(): Statement =
        Execute(Vector.empty)
    
    def block(statements: Statement*): Statement =
        Block(statements.toVector)
    
    def when(condition: BooleanValue): When =
        When(condition)
    
    def loop(condition: BooleanValue): Loop =
        Loop(condition)
    
    def value(value: Value): Variant =
        Variant(value)
    
    def choose(value: Value): Choose =
        Choose(value)
}

package statement

import events.Event.Events
import statement.Statement.{Block, Choose, Execute, Loop, Variant, When}
import value.Value
import value.basic.BooleanValue

object Statements {
    def execute(events: Events): Statement =
        Execute(events)
    
    def block(statements: Statement*): Statement =
        Block(statements.toVector)
    
    def when(condition: BooleanValue): When =
        When(condition)
    
    def loop(condition: BooleanValue): Loop =
        Loop(condition)
    
    def variant(value: Value, values: Value*): Variant =
        Variant(value +: values.toVector)
    
    def choose(value: Value): Choose =
        Choose(value)
}

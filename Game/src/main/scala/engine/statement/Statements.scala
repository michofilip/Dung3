package engine.statement

import engine.events.Event
import engine.statement.Statement.{Block, Choose, Execute, Loop, Variant, When}
import engine.value.Value
import engine.value.basic.BooleanValue

object Statements {
    def execute(events: Event*): Statement =
        Execute(events.toVector)

    def block(statements: Statement*): Statement =
        Block(statements.toVector)

    def when(condition: BooleanValue): When =
        When(condition)

    def loop(condition: BooleanValue): Loop =
        Loop(condition)

    def variant(value: Value[_], values: Value[_]*): Variant =
        Variant(value +: values.toVector)

    def choose(value: Value[_]): Choose =
        Choose(value)
}

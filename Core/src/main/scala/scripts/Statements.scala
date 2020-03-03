package scripts

import scripts.Statement.{Block, Execute, When}
import value.basic.BooleanValue

object Statements {
    def execute(): Statement =
        Execute(Vector.empty)
    
    def block(statements: Statement*): Statement =
        Block(statements.toVector)
    
    def when(condition: BooleanValue): When =
        When(condition)
}
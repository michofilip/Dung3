package scripts

import events.Event
import scripts.Statement.Compilable
import value.basic.BooleanValue

sealed abstract class Statement extends Compilable {
    
}

object Statement {
    
    sealed trait Compilable
    
    final case class Execute(events: Vector[Event]) extends Statement
    
    final case class Block(statements: Vector[Statement]) extends Statement
    
    final case class When(condition: BooleanValue) extends Compilable {
        def therefore(statements: Statement*): WhenTherefore =
            WhenTherefore(condition, Block(statements.toVector))
    }
    
    final case class WhenTherefore(condition: BooleanValue, thereforeStatement: Statement) extends Statement {
        def otherwise(whenTherefore: WhenTherefore): MultiWhenTherefore =
            MultiWhenTherefore(Vector(this, whenTherefore))
        
        def otherwise(statements: Statement*): MultiWhenThereforeOtherwise =
            MultiWhenThereforeOtherwise(Vector(this), Block(statements.toVector))
    }
    
    final case class MultiWhenTherefore(whenThereforeSeq: Vector[WhenTherefore]) extends Statement {
        def otherwise(whenTherefore: WhenTherefore): MultiWhenTherefore =
            MultiWhenTherefore(whenThereforeSeq :+ whenTherefore)
        
        def otherwise(statements: Statement*): MultiWhenThereforeOtherwise =
            MultiWhenThereforeOtherwise(whenThereforeSeq, Block(statements.toVector))
    }
    
    final case class MultiWhenThereforeOtherwise(whenThereforeSeq: Vector[WhenTherefore], otherwiseStatement: Statement) extends Statement
    
    final case class Loop(condition: BooleanValue) {
        def body(statements: Statement*): LoopBody =
            LoopBody(condition, Block(statements.toVector))
    }
    
    final case class LoopBody(condition: BooleanValue, body: Statement) extends Statement
    
}

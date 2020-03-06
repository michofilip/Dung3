package scripts

import events.Event
import value.Value
import value.basic.BooleanValue
import value.basic.BooleanValue.BooleanConstant

sealed abstract class Statement {
    
}

object Statement {
    
    final case class Execute(events: Vector[Event]) extends Statement
    
    final case class Block(statements: Vector[Statement]) extends Statement
    
    final case class When(condition: BooleanValue) {
        def therefore(statements: Statement*): MultiWhenTherefore =
            MultiWhenTherefore(Vector(WhenTherefore(condition, Block(statements.toVector))))
    }
    
    final case class WhenTherefore(condition: BooleanValue, thereforeStatement: Statement)
    
    final case class MultiWhenTherefore(whenThereforeSeq: Vector[WhenTherefore]) extends Statement {
        def otherwiseWhen(condition: BooleanValue): MultiWhenThereforeWhen =
            MultiWhenThereforeWhen(whenThereforeSeq, condition)
        
        def otherwise(statements: Statement*): MultiWhenThereforeOtherwise =
            MultiWhenThereforeOtherwise(whenThereforeSeq, Block(statements.toVector))
    }
    
    final case class MultiWhenThereforeWhen(whenThereforeSeq: Vector[WhenTherefore], condition: BooleanValue) {
        def therefore(statements: Statement*): MultiWhenTherefore =
            MultiWhenTherefore(whenThereforeSeq :+ WhenTherefore(condition, Block(statements.toVector)))
    }
    
    final case class MultiWhenThereforeOtherwise(whenThereforeSeq: Vector[WhenTherefore], otherwiseStatement: Statement) extends Statement
    
    final case class Loop(condition: BooleanValue) {
        def body(statements: Statement*): LoopBody =
            LoopBody(condition, Block(statements.toVector))
    }
    
    final case class LoopBody(condition: BooleanValue, body: Statement) extends Statement
    
    final case class Variant(values: Vector[Value]) {
        def when(condition: BooleanValue): VariantWhen =
            VariantWhen(values, condition)
        
        def therefore(statements: Statement*): VariantWhenTherefore =
            VariantWhenTherefore(values, BooleanConstant(true), Block(statements.toVector))
    }
    
    final case class VariantWhen(values: Vector[Value], condition: BooleanValue) {
        def therefore(statements: Statement*): VariantWhenTherefore =
            VariantWhenTherefore(values, condition, Block(statements.toVector))
    }
    
    final case class VariantWhenTherefore(values: Vector[Value], condition: BooleanValue, therefore: Statement)
    
    final case class Choose(value: Value) {
        def from(variant: VariantWhenTherefore, variants: VariantWhenTherefore*): ChooseVariants =
            ChooseVariants(value, variant +: variants.toVector)
    }
    
    final case class ChooseVariants(value: Value, variants: Vector[VariantWhenTherefore]) extends Statement {
        def otherwise(statements: Statement*): ChooseVariantsOtherwise =
            ChooseVariantsOtherwise(value, variants, Block(statements.toVector))
    }
    
    final case class ChooseVariantsOtherwise(value: Value, variants: Vector[VariantWhenTherefore], otherwise: Statement) extends Statement
    
}

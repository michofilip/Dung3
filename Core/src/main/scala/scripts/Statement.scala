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
    
    final case class Variant(value: Value) {
        def or(value: Value): MultiVariant =
            MultiVariant(Vector(this.value, value))
        
        def when(condition: BooleanValue): MultiVariantCondition =
            MultiVariantCondition(Vector(value), condition)
        
        def therefore(statements: Statement*): MultiVariantConditionTherefore =
            MultiVariantConditionTherefore(Vector(value), BooleanConstant(true), Block(statements.toVector))
    }
    
    final case class MultiVariant(values: Vector[Value]) {
        def or(value: Value): MultiVariant =
            MultiVariant(values :+ value)
        
        def when(condition: BooleanValue): MultiVariantCondition =
            MultiVariantCondition(values, condition)
        
        def therefore(statements: Statement*): MultiVariantConditionTherefore =
            MultiVariantConditionTherefore(values, BooleanConstant(true), Block(statements.toVector))
    }
    
    final case class MultiVariantCondition(values: Vector[Value], condition: BooleanValue) {
        def therefore(statements: Statement*): MultiVariantConditionTherefore =
            MultiVariantConditionTherefore(values, condition, Block(statements.toVector))
    }
    
    final case class MultiVariantConditionTherefore(values: Vector[Value], condition: BooleanValue, therefore: Statement)
    
    final case class Choose(value: Value) {
        def variant(multiVariantTherefore: MultiVariantConditionTherefore): ChooseMultiVariantTherefore =
            ChooseMultiVariantTherefore(value, Vector(multiVariantTherefore))
    }
    
    final case class ChooseMultiVariantTherefore(value: Value, variantThereforeSeq: Vector[MultiVariantConditionTherefore]) extends Statement {
        def variant(multiVariantThen: MultiVariantConditionTherefore): ChooseMultiVariantTherefore =
            ChooseMultiVariantTherefore(value, variantThereforeSeq :+ multiVariantThen)
        
        def otherwise(statements: Statement*): ChooseMultiVariantThereforeOtherwise =
            ChooseMultiVariantThereforeOtherwise(value, variantThereforeSeq, Block(statements.toVector))
    }
    
    final case class ChooseMultiVariantThereforeOtherwise(value: Value, variantThenSeq: Vector[MultiVariantConditionTherefore], otherwise: Statement) extends Statement
    
}

package engine.statement

import engine.events.Event.Events
import engine.value.Value
import engine.value.basic.BooleanValue

sealed abstract class Statement

object Statement {

    final case class Execute(events: Events) extends Statement

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

    final case class Variant(values: Vector[Value[_]]) {
        def when(condition: BooleanValue): VariantWhen =
            VariantWhen(values, Some(condition))

        def therefore(statements: Statement*): VariantWhenTherefore =
            VariantWhenTherefore(values, None, Block(statements.toVector))
    }

    final case class VariantWhen(values: Vector[Value[_]], conditionOpt: Option[BooleanValue]) {
        def therefore(statements: Statement*): VariantWhenTherefore =
            VariantWhenTherefore(values, conditionOpt, Block(statements.toVector))
    }

    final case class VariantWhenTherefore(values: Vector[Value[_]], conditionOpt: Option[BooleanValue], therefore: Statement)

    final case class Choose(value: Value[_]) {
        def from(variant: VariantWhenTherefore, variants: VariantWhenTherefore*): ChooseVariants =
            ChooseVariants(value, variant +: variants.toVector)
    }

    final case class ChooseVariants(value: Value[_], variants: Vector[VariantWhenTherefore]) extends Statement {
        def otherwise(statements: Statement*): ChooseVariantsOtherwise =
            ChooseVariantsOtherwise(value, variants, Block(statements.toVector))
    }

    final case class ChooseVariantsOtherwise(value: Value[_], variants: Vector[VariantWhenTherefore], otherwise: Statement) extends Statement

}

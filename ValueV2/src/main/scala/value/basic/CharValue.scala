package value.basic

import value.{Value, ValueContext}

abstract class CharValue extends Value {
    override final protected type T = Char
}

object CharValue {
    
    final case object CharNull extends CharValue {
        override def get(implicit valueContext: ValueContext): Option[Char] = None
    }
    
    final case class CharConstant(value: Char) extends CharValue {
        override def get(implicit valueContext: ValueContext): Option[Char] = Some(value)
    }
    
}
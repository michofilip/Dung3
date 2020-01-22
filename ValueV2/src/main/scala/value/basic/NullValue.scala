package value.basic

import value.{Value, ValueContext}

case object NullValue extends Value {
    override protected type T = Any
    
    override def get(implicit valueContext: ValueContext): Option[Any] = None
    
}

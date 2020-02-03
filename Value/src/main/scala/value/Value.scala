package value

import value.basic.BooleanValue

abstract class Value {
    protected type T
    
    def get(implicit valueContext: ValueContext): Option[T]
    
    def getOrElse(default: => T)(implicit valueContext: ValueContext): T = get.getOrElse(default)
    
    def ===(that: Value): BooleanValue = BooleanValue.Equals(this, that)
    
    def !==(that: Value): BooleanValue = BooleanValue.Unequals(this, that)
}

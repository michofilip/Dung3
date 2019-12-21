package value

abstract class Value {
    protected type T
    
    def get: Option[T]
    
    def getOrElse(default: => T): T = get.getOrElse(default)
    
    def calculate: Value
    
    def ===(that: Value): BooleanValue = BooleanValue.Equals(this, that)
    
    def !==(that: Value): BooleanValue = BooleanValue.Unequals(this, that)
}

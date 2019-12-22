package value

trait ComparableValue extends Value {
    protected[value] def compare(value: ComparableValue): Option[Int]
    
    final def <(that: ComparableValue): BooleanValue = BooleanValue.Less(this, that)
    
    final def <=(that: ComparableValue): BooleanValue = BooleanValue.LessEqual(this, that)
    
    final def >(that: ComparableValue): BooleanValue = BooleanValue.Greater(this, that)
    
    final def >=(that: ComparableValue): BooleanValue = BooleanValue.GreaterEqual(this, that)
    
}
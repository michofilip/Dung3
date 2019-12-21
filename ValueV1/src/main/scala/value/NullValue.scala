package value

case object NullValue extends Value {
    override protected type T = Any
    
    override def get: Option[Any] = None
    
    override def calculate: NullValue.type = this
}

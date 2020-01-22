package value.basic

import value.{Value, ValueContext}

abstract class BooleanValue extends Value {
    override final protected type T = Boolean
    
    final def unary_! : BooleanValue = BooleanValue.NOT(this)
    
    final def &&(that: BooleanValue): BooleanValue = BooleanValue.AND(this, that)
    
    final def !&&(that: BooleanValue): BooleanValue = BooleanValue.NAND(this, that)
    
    final def ||(that: BooleanValue): BooleanValue = BooleanValue.OR(this, that)
    
    final def !||(that: BooleanValue): BooleanValue = BooleanValue.NOR(this, that)
    
    final def <>(that: BooleanValue): BooleanValue = BooleanValue.XOR(this, that)
    
    final def !<>(that: BooleanValue): BooleanValue = BooleanValue.XNOR(this, that)
}

object BooleanValue {
    
    final case object BooleanNull extends BooleanValue {
        override def get(implicit valueContext: ValueContext): Option[Boolean] = None
    }
    
    final case class BooleanConstant(value: Boolean) extends BooleanValue {
        override def get(implicit valueContext: ValueContext): Option[Boolean] = Some(value)
    }
    
    final case class NOT(value: BooleanValue) extends BooleanValue {
        override def get(implicit valueContext: ValueContext): Option[Boolean] = value.get.map(v => !v)
    }
    
    final case class AND(value1: BooleanValue, value2: BooleanValue) extends BooleanValue {
        override def get(implicit valueContext: ValueContext): Option[Boolean] =
            value1.get.flatMap(v1 => value2.get.map(v2 => v1 && v2))
    }
    
    final case class NAND(value1: BooleanValue, value2: BooleanValue) extends BooleanValue {
        override def get(implicit valueContext: ValueContext): Option[Boolean] =
            value1.get.flatMap(v1 => value2.get.map(v2 => !(v1 && v2)))
    }
    
    final case class OR(value1: BooleanValue, value2: BooleanValue) extends BooleanValue {
        override def get(implicit valueContext: ValueContext): Option[Boolean] =
            value1.get.flatMap(v1 => value2.get.map(v2 => v1 || v2))
    }
    
    final case class NOR(value1: BooleanValue, value2: BooleanValue) extends BooleanValue {
        override def get(implicit valueContext: ValueContext): Option[Boolean] =
            value1.get.flatMap(v1 => value2.get.map(v2 => !(v1 || v2)))
    }
    
    final case class XOR(value1: BooleanValue, value2: BooleanValue) extends BooleanValue {
        override def get(implicit valueContext: ValueContext): Option[Boolean] =
            value1.get.flatMap(v1 => value2.get.map(v2 => (v1 && !v2) || (!v1 && v2)))
    }
    
    final case class XNOR(value1: BooleanValue, value2: BooleanValue) extends BooleanValue {
        override def get(implicit valueContext: ValueContext): Option[Boolean] =
            value1.get.flatMap(v1 => value2.get.map(v2 => (v1 && v2) || (!v1 && !v2)))
    }
    
    final case class Equals(value1: Value, value2: Value) extends BooleanValue {
        override def get(implicit valueContext: ValueContext): Option[Boolean] =
            value1.get.flatMap(v1 => value2.get.map(v2 => v1 == v2))
    }
    
    final case class Unequals(value1: Value, value2: Value) extends BooleanValue {
        override def get(implicit valueContext: ValueContext): Option[Boolean] =
            value1.get.flatMap(v1 => value2.get.map(v2 => v1 != v2))
    }
    
    final case class Less(value1: ComparableValue, value2: ComparableValue) extends BooleanValue {
        override def get(implicit valueContext: ValueContext): Option[Boolean] =
            value1.compareTo(value2).map(_ < 0)
    }
    
    final case class LessEqual(value1: ComparableValue, value2: ComparableValue) extends BooleanValue {
        override def get(implicit valueContext: ValueContext): Option[Boolean] =
            value1.compareTo(value2).map(_ <= 0)
    }
    
    final case class Greater(value1: ComparableValue, value2: ComparableValue) extends BooleanValue {
        override def get(implicit valueContext: ValueContext): Option[Boolean] =
            value1.compareTo(value2).map(_ > 0)
    }
    
    final case class GreaterEqual(value1: ComparableValue, value2: ComparableValue) extends BooleanValue {
        override def get(implicit valueContext: ValueContext): Option[Boolean] =
            value1.compareTo(value2).map(_ >= 0)
    }
    
}

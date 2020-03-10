package model.value

import value.Value
import value.basic.NullValue

case class ValueContainer(values: Map[String, Value]) {
    def getValue(name: String): Value =
        values.getOrElse(name, NullValue)
}

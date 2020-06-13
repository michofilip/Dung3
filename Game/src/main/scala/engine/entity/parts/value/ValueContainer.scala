package engine.entity.parts.value

import value.Value
import value.basic.BooleanValue.BooleanNull
import value.basic.ByteValue.ByteNull
import value.basic.CharValue.CharNull
import value.basic.DoubleValue.DoubleNull
import value.basic.FloatValue.FloatNull
import value.basic.IntValue.IntNull
import value.basic.LongValue.LongNull
import value.basic.ShortValue.ShortNull
import value.basic.StringValue.StringNull
import value.basic.{BooleanValue, ByteValue, CharValue, DoubleValue, FloatValue, IntValue, LongValue, NullValue, ShortValue, StringValue}

case class ValueContainer(values: Map[String, Value]) {
    def setValue(name: String, value: Value): ValueContainer =
        ValueContainer(values + (name -> value))
    
    def removeValue(name: String): ValueContainer =
        ValueContainer(values - name)
    
    def getValue(name: String): Value =
        values.getOrElse(name, NullValue)
    
    def getBooleanValue(name: String): BooleanValue =
        values.get(name) match {
            case Some(value: BooleanValue) => value
            case _ => BooleanNull
        }
    
    def getByteValue(name: String): ByteValue =
        values.get(name) match {
            case Some(value: ByteValue) => value
            case _ => ByteNull
        }
    
    def getShortValue(name: String): ShortValue =
        values.get(name) match {
            case Some(value: ShortValue) => value
            case _ => ShortNull
        }
    
    def getIntValue(name: String): IntValue =
        values.get(name) match {
            case Some(value: IntValue) => value
            case _ => IntNull
        }
    
    def getLongValue(name: String): LongValue =
        values.get(name) match {
            case Some(value: LongValue) => value
            case _ => LongNull
        }
    
    def getFloatValue(name: String): FloatValue =
        values.get(name) match {
            case Some(value: FloatValue) => value
            case _ => FloatNull
        }
    
    def getDoubleValue(name: String): DoubleValue =
        values.get(name) match {
            case Some(value: DoubleValue) => value
            case _ => DoubleNull
        }
    
    def getCharValue(name: String): CharValue =
        values.get(name) match {
            case Some(value: CharValue) => value
            case _ => CharNull
        }
    
    def getStringValue(name: String): StringValue =
        values.get(name) match {
            case Some(value: StringValue) => value
            case _ => StringNull
        }
}

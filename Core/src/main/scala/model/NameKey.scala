package model

sealed abstract class NameKey

object NameKey {
    
    final case object WoodenDoor extends NameKey
    
    final case object Player extends NameKey
    
}

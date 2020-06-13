package engine.entity.parts

sealed abstract class NameKey

object NameKey {
    
    final case object WoodenDoor extends NameKey
    
    final case object Player extends NameKey
    
}

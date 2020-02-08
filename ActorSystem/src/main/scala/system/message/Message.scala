package system.message

sealed abstract class Message

object Message {
    
    final case class TestMessage() extends Message
    
    final case class StartTimer() extends Message
    
    final case class StopTimer() extends Message
    
    final case class Exit() extends Message
    
}
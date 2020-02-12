//package system.messages
//
//import commons.temporal.Timestamp
//import events.Event
//
//sealed abstract class Message
//
//object Message {
//
//    final case object Start extends Message
//
//    final case object Stop extends Message
//
//    final case object Status extends Message
//
//    final case object Exit extends Message
//
//
//    final case class StartTimer() extends Message
//
//    final case class StopTimer() extends Message
//
//    final case class SetTimer(timestamp: Timestamp) extends Message
//
//    final case class NextFrame() extends Message
//
//    final case class AddEvents(events: Vector[Event]) extends Message
//
//}
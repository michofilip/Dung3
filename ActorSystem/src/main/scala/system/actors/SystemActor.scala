//package system.actors
//
//import akka.actor.typed.Behavior
//import akka.actor.typed.scaladsl.Behaviors
//import commons.temporal.Timer
//import events.Event
//import system.message.Message
//import world.WorldFrame
//
//class SystemActor(var timer: Timer,
//                  var worldFrame: WorldFrame,
//                  var externalEvents: Vector[Event]) {
//    def receive(): Behavior[Message] = Behaviors.receive { (context, message) =>
//        message match {
//            case Message.Exit() =>
//                context.log.info("Exit")
//                Behaviors.stopped
//
//            case Message.StartTimer() =>
//                context.log.info("Timer started")
//                timer = timer.start
//                Behaviors.same
//            case Message.StopTimer() =>
//                context.log.info("Timer stopped")
//                timer = timer.stop
//                Behaviors.same
//            case Message.SetTimer(timestamp) =>
//                timer = Timer(timestamp, isRunning = false)
//                Behaviors.same
//
//            case Message.NextFrame() =>
//                worldFrame.nextFrame(externalEvents, timer.getTimestamp)
//                externalEvents = Vector.empty
//                context.self ! Message.NextFrame()
//                Behaviors.same
//
//            case Message.AddEvents(events) =>
//                externalEvents = externalEvents ++ events
//                Behaviors.same
//
//        }
//    }
//}

//package system.actors
//
//import akka.actor.typed.Behavior
//import akka.actor.typed.scaladsl.Behaviors
//import system.messages.Message
//
//object MainActor {
//    var isRunning: Boolean = false
//
//    def apply(): Behavior[Message] = Behaviors.setup { context =>
//
//        Behaviors.receiveMessage {
//            case Message.Start =>
//                context.log.info("Starting")
//                isRunning = true
//                Behaviors.same
//            case Message.Stop =>
//                context.log.info("Stopping")
//                isRunning = false
//                Behaviors.same
//            case Message.Status =>
//                context.log.info("Application is {} running", if (isRunning) "" else "not")
//                Behaviors.same
//            case Message.Exit =>
//                context.log.info("Exiting")
//                isRunning = false
//                Behaviors.stopped
//        }
//    }
//}

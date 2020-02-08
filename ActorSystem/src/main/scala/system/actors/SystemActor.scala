package system.actors

import akka.actor.typed.Behavior
import akka.actor.typed.scaladsl.Behaviors
import commons.temporal.Timer
import system.message.Message

class SystemActor(var timer: Timer) {
    def receive(): Behavior[Message] = Behaviors.receive { (context, message) =>
        message match {
            case Message.TestMessage() =>
                context.log.info("Test message received at {}", timer.getTimestamp)
                Behaviors.same
            case Message.StartTimer() =>
                context.log.info("Timer started")
                timer = timer.start
                Behaviors.same
            case Message.StopTimer() =>
                context.log.info("Timer stopped")
                timer = timer.stop
                Behaviors.same
            case Message.Exit() =>
                context.log.info("Exit")
                Behaviors.stopped
        }
    }
}

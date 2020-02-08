package actors

import akka.actor.typed.scaladsl.Behaviors
import akka.actor.typed.{ActorRef, ActorSystem, Behavior}

object PingPong extends App {
    
    sealed abstract class PingPongMessage
    
    final case class Ping(i: Int, replyTo: ActorRef[PingPongMessage]) extends PingPongMessage
    
    final case class Pong(i: Int, replyTo: ActorRef[PingPongMessage]) extends PingPongMessage
    
    object PingPongActor {
        def apply(): Behavior[PingPongMessage] = Behaviors.receive { (context, message) =>
            message match {
                case Ping(i, replyTo) =>
                    if (i > 0) {
                        context.log.info("PING")
                        replyTo ! Pong(i, context.self)
                        Behaviors.same
                    } else {
                        context.log.info("OUT")
                        Behaviors.stopped
                    }
                case Pong(i, replyTo) =>
                    context.log.info("PONG")
                    replyTo ! Ping(i - 1, context.self)
                    Behaviors.same
            }
        }
    }
    
    val pingPongActor: ActorSystem[PingPongMessage] = ActorSystem(PingPongActor(), "PingPongActor")
    
    pingPongActor ! Ping(5, pingPongActor)
    
}

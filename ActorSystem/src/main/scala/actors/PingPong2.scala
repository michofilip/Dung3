package actors

import akka.actor.typed.scaladsl.Behaviors
import akka.actor.typed.{ActorSystem, Behavior}

object PingPong2 extends App {
    
    sealed abstract class PingPongMessage2
    
    final case class Ping() extends PingPongMessage2
    
    final case class Pong() extends PingPongMessage2
    
    class PingPongActor2(var i: Int) {
        def receive(): Behavior[PingPongMessage2] = Behaviors.receive { (context, message) =>
            message match {
                case Ping() =>
                    if (i > 0) {
                        context.log.info("PING")
                        context.self ! Pong()
                        Behaviors.same
                    } else {
                        context.log.info("OUT")
                        Behaviors.stopped
                    }
                case Pong() =>
                    context.log.info("PONG")
                    i = i - 1
                    context.self ! Ping()
                    Behaviors.same
            }
        }
    }
    
    val pingPongA = new PingPongActor2(5)
    
    val pingPongActor: ActorSystem[PingPongMessage2] = ActorSystem(pingPongA.receive(), "PingPongActor2")
    
    pingPongActor ! Ping()
    
}

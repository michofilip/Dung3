package system

import akka.actor.typed.ActorSystem
import commons.temporal.{Timer, Timestamp}
import system.actors.SystemActor
import system.message.Message

object Main extends App {
    
    val mainActor = new SystemActor(Timer(Timestamp.zero, isRunning = false))
    
    val actorSystem: ActorSystem[Message] = ActorSystem(mainActor.receive(), "SystemActor")
    
    //    val actors = new Actors
    
    actorSystem ! Message.TestMessage()
    Thread.sleep(1000)
    actorSystem ! Message.StartTimer()
    Thread.sleep(1000)
    actorSystem ! Message.TestMessage()
    Thread.sleep(1000)
    actorSystem ! Message.TestMessage()
    Thread.sleep(1000)
    actorSystem ! Message.StopTimer()
    Thread.sleep(1000)
    actorSystem ! Message.TestMessage()
    Thread.sleep(1000)
    actorSystem ! Message.Exit()
}

package system.actors

import akka.actor.typed.Behavior
import akka.actor.typed.scaladsl.Behaviors
import system.messages.DisplayMessage.DisplayWorldFrame

object DisplayActor {
    
    def receive: Behavior[DisplayWorldFrame] = Behaviors.receiveMessage {
        case DisplayWorldFrame(worldFrame) =>
            println(worldFrame)
            Behaviors.same
    }
}

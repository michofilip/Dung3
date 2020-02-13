package system.actors

import akka.actor.typed.Behavior
import akka.actor.typed.scaladsl.Behaviors
import system.messages.WorldFrameCalculatorMessage
import system.messages.WorldFrameCalculatorMessage.NextFrame
import system.messages.WorldFrameMessage.SetWorldFrame

object WorldFrameCalculatorActor {
    
    def apply(): Behavior[WorldFrameCalculatorMessage] = Behaviors.receiveMessage {
        case NextFrame(worldFrame, externalEvents, timestamp, replayTo) =>
            val nextFrame = worldFrame.nextFrame(externalEvents, timestamp)
            replayTo ! SetWorldFrame(nextFrame)
            Behaviors.same
    }
    
}

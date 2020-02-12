package system.messages

import akka.actor.typed.ActorRef
import commons.temporal.Timestamp
import events.Event
import world.WorldFrame

sealed abstract class WorldFrameCalculatorMessage

object WorldFrameCalculatorMessage {
    
    final case class NextFrame(worldFrame: WorldFrame,
                               externalEvents: Vector[Event],
                               timestamp: Timestamp,
                               replayTo: ActorRef[WorldFrameMessage])
            extends WorldFrameCalculatorMessage
    
}
package system.actors

import akka.actor.typed.Behavior
import akka.actor.typed.scaladsl.Behaviors
import commons.temporal.{Timer, Timestamp}
import events.Event
import system.messages.DisplayMessage.DisplayWorldFrame
import system.messages.WorldFrameCalculatorMessage.NextFrame
import system.messages.WorldFrameMessage
import system.messages.WorldFrameMessage.SetWorldFrame
import world.WorldFrame

object WorldFrameActor {
    
    //    private var worldFrameOpt: Option[WorldFrame] = None
    //    private var externalEvents: Vector[Event] = Vector.empty
    //    private var timer: Timer = Timer(Timestamp.zero, isRunning = true)
    
    def receive: Behavior[WorldFrameMessage] = Behaviors.setup { context =>
        var worldFrameOpt: Option[WorldFrame] = None
        var externalEvents: Vector[Event] = Vector.empty
        var timer: Timer = Timer(Timestamp.zero, isRunning = true)
        
        val worldFrameCalculatorActor = context.spawn(WorldFrameCalculatorActor.receive, "WorldFrameCalculatorActor")
        val displayActor = context.spawn(DisplayActor.receive, "DisplayActor")
        
        Behaviors.receiveMessage {
            case SetWorldFrame(worldFrame) =>
                worldFrameOpt = Some(worldFrame)
                worldFrameCalculatorActor ! NextFrame(worldFrame, externalEvents, timer.getTimestamp, context.self)
                displayActor ! DisplayWorldFrame(worldFrame)
                Thread.sleep(1000)
                Behaviors.same
        }
    }
    
}

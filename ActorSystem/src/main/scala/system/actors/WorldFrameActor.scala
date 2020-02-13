package system.actors

import akka.actor.typed.scaladsl.Behaviors
import akka.actor.typed.{ActorRef, Behavior}
import commons.temporal.{Timer, Timestamp}
import events.Event
import system.messages.DisplayMessage.DisplayWorldFrame
import system.messages.WorldFrameCalculatorMessage.NextFrame
import system.messages.WorldFrameMessage.SetWorldFrame
import system.messages.{WorldFrameCalculatorMessage, WorldFrameMessage}
import world.WorldFrame

object WorldFrameActor {
    
    //    private var worldFrameOpt: Option[WorldFrame] = None
    //    private var externalEvents: Vector[Event] = Vector.empty
    //    private var timer: Timer = Timer(Timestamp.zero, isRunning = true)
    
    def apply(): Behavior[WorldFrameMessage] = {
        Behaviors.setup { context =>
            val worldFrameCalculatorActor = context.spawn(WorldFrameCalculatorActor(), "WorldFrameCalculatorActor")
            val displayActor = context.spawn(DisplayActor(), "DisplayActor")
            receive(None, Vector.empty, Timer(Timestamp.zero, isRunning = true), worldFrameCalculatorActor, displayActor, context.self)
        }
        
    }
    
    //    def receive: Behavior[WorldFrameMessage] = Behaviors.setup { context =>
    //        var worldFrameOpt: Option[WorldFrame] = None
    //        var externalEvents: Vector[Event] = Vector.empty
    //        var timer: Timer = Timer(Timestamp.zero, isRunning = true)
    //
    //        val worldFrameCalculatorActor = context.spawn(WorldFrameCalculatorActor(), "WorldFrameCalculatorActor")
    //        val displayActor = context.spawn(DisplayActor(), "DisplayActor")
    //
    //        Behaviors.receiveMessage {
    //            case SetWorldFrame(worldFrame) =>
    //                worldFrameOpt = Some(worldFrame)
    //                worldFrameCalculatorActor ! NextFrame(worldFrame, externalEvents, timer.getTimestamp, context.self)
    //                displayActor ! DisplayWorldFrame(worldFrame)
    //                Thread.sleep(1000)
    //                Behaviors.same
    //        }
    //    }
    
    def receive(worldFrameOpt: Option[WorldFrame], externalEvents: Vector[Event], timer: Timer,
                worldFrameCalculatorActor: ActorRef[WorldFrameCalculatorMessage],
                displayActor: ActorRef[DisplayWorldFrame],
                self: ActorRef[WorldFrameMessage]): Behavior[WorldFrameMessage] = {
        
        Behaviors.receiveMessage {
            case SetWorldFrame(worldFrame) =>
                //                    worldFrameOpt = Some(worldFrame)
                worldFrameCalculatorActor ! NextFrame(worldFrame, externalEvents, timer.getTimestamp, self)
                displayActor ! DisplayWorldFrame(worldFrame)
                Thread.sleep(1000)
                receive(Some(worldFrame), Vector.empty, timer, worldFrameCalculatorActor, displayActor, self)
            //                    Behaviors.same
            
        }
    }
    
}

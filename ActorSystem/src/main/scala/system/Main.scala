package system

import akka.actor.typed.ActorSystem
import commons.temporal.Timestamp
import entity.Entity
import events.{Event, PositionEvents}
import model.position.{Coordinates, Direction, Position, PositionContainer}
import repository.EntityRepository
import system.actors.WorldFrameActor
import system.messages.WorldFrameMessage
import world.WorldFrame

object Main extends App {
    val actorSystem: ActorSystem[WorldFrameMessage] = ActorSystem(WorldFrameActor.receive, "WorldFrameActor")
    
    val worldFrame = makeWorldFrame()
    
    actorSystem ! WorldFrameMessage.SetWorldFrame(worldFrame)
    
    private def makeWorldFrame(): WorldFrame = {
        val initialTimestamp = Timestamp.zero
        val position = PositionContainer(Position(Coordinates(10, 20), Direction.North), initialTimestamp)
        val entity = Entity(id = 1, name = "Test entity", initialTimestamp = Timestamp.now, positionContainerOpt = Some(position))
        
        val entityRepository = EntityRepository(Seq(entity))
        val events = Vector[Event](PositionEvents.Step(1, Direction.South))
        WorldFrame(initialTimestamp, 1, entityRepository, events)
    }
}

import engine.entity.EntityFactory
import engine.entity.parts.position.{Coordinates, Direction, Position}
import engine.events.{Event, PositionEvents}
import engine.repository.{AnimationRepository, AnimationSelectorRepository, EntityRepository, PhysicsRepository, PhysicsSelectorRepository, SpriteRepository}
import engine.temporal.Timestamp
import engine.{GameContext, GameService}

import scala.annotation.tailrec

object MainV3 extends App {
    implicit val physicsRepository: PhysicsRepository = new PhysicsRepository()
    implicit val physicsSelectorRepository: PhysicsSelectorRepository = new PhysicsSelectorRepository()
    implicit val spriteRepository: SpriteRepository = new SpriteRepository()
    implicit val animationRepository: AnimationRepository = new AnimationRepository()
    implicit val animationSelectorRepository: AnimationSelectorRepository = new AnimationSelectorRepository()

    val gameService: GameService = new GameService

    val entityFactory = new EntityFactory()

    val initialTimestamp = Timestamp.now
    val position = Position(Coordinates(10, 20), Direction.North)
    val entity = entityFactory.makePlayer(1, initialTimestamp, position)

    val entityRepository = EntityRepository(Seq(entity))
    val events = Vector[Event](PositionEvents.Step(1, Direction.South))
    //    val world0 = GameContext(initialTimestamp, 1, entityRepository, events)
    //    val world1 = gameService.nextFrame(Vector(), initialTimestamp)(world0)
    //    val world2 = gameService.nextFrame(Vector(), initialTimestamp)(world1)
    //
    //    println(world0)
    //    println(world1)
    //    println(world2)

    @tailrec
    def processGame(i: Int)(implicit gameContext: GameContext): Unit = {
        println(gameContext)
        if (i > 0) {
            processGame(i - 1)(gameService.nextFrame(Vector(), initialTimestamp))
        }
    }

    processGame(2)(GameContext(initialTimestamp, 1, entityRepository, events))
}

import commons.temporal.Timestamp
import entity.EntityFactory
import events.{Event, PositionEvents}
import model.position.{Coordinates, Direction, Position}
import repository.{AnimationRepository, AnimationSelectorRepository, EntityRepository, PhysicsRepository, PhysicsSelectorRepository, SpriteRepository}
import world.WorldFrame

object MainV3 extends App {
    implicit val physicsRepository: PhysicsRepository = new PhysicsRepository()
    implicit val physicsSelectorRepository: PhysicsSelectorRepository = new PhysicsSelectorRepository()
    implicit val spriteRepository: SpriteRepository = new SpriteRepository()
    implicit val animationRepository: AnimationRepository = new AnimationRepository()
    implicit val animationSelectorRepository: AnimationSelectorRepository = new AnimationSelectorRepository()
    
    val entityFactory = new EntityFactory()
    
    val initialTimestamp = Timestamp.now
    val position = Position(Coordinates(10, 20), Direction.North)
    val entity = entityFactory.makePlayer(1, initialTimestamp, position)
    
    val entityRepository = EntityRepository(Seq(entity))
    val events = Vector[Event](PositionEvents.Step(1, Direction.South))
    val world0 = WorldFrame(initialTimestamp, 1, entityRepository, events)
    val world1 = world0.nextFrame(Vector(), initialTimestamp)
    val world2 = world1.nextFrame(Vector(), initialTimestamp)
    
    println(world0)
    println(world1)
    println(world2)
}

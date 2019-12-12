import entity.EntityFactory
import entity.EntityManager._
import repositories.{AnimationRepository, AnimationSelectorRepository, EntityTypeRepository, ImageRepository, PhysicsRepository, PhysicsSelectorRepository}
import commons.temporal.Duration.ImplicitDuration
import commons.temporal.Timestamp

object Main extends App {
    implicit val imageRepository: ImageRepository = new ImageRepository()
    implicit val entityTypeRepository: EntityTypeRepository = new EntityTypeRepository()
    implicit val physicsRepository: PhysicsRepository = new PhysicsRepository()
    implicit val physicsSelectorRepository: PhysicsSelectorRepository = new PhysicsSelectorRepository()
    implicit val animationRepository: AnimationRepository = new AnimationRepository()
    implicit val animationSelectorRepository: AnimationSelectorRepository = new AnimationSelectorRepository()
    
    val creationTimestamp = Timestamp.zero
    val stateChangeTimestamp = creationTimestamp + 1.seconds
    val getFrameTimestamp = creationTimestamp + 3.seconds
    
    val entityFactory = new EntityFactory()
    val testDoor = entityFactory.makeTestDoor(creationTimestamp)
    println(testDoor)
    println(testDoor.getFrame(getFrameTimestamp))
    println(testDoor.close(stateChangeTimestamp))
    println(testDoor.close(stateChangeTimestamp).getFrame(getFrameTimestamp))
}

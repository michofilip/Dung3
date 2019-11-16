import entity.EntityFactory
import entity.EntityManager._
import repositories.{AnimationRepository, AnimationSelectorRepository, EntityTypeRepository, ImageRepository, PhysicsRepository, PhysicsSelectorRepository}

object Main extends App {
    implicit val imageRepository: ImageRepository = new ImageRepository()
    implicit val entityTypeRepository: EntityTypeRepository = new EntityTypeRepository()
    implicit val physicsRepository: PhysicsRepository = new PhysicsRepository()
    implicit val physicsSelectorRepository: PhysicsSelectorRepository = new PhysicsSelectorRepository()
    implicit val animationRepository: AnimationRepository = new AnimationRepository()
    implicit val animationSelectorRepository: AnimationSelectorRepository = new AnimationSelectorRepository()
    
    val entityFactory = new EntityFactory()
    val testDoor = entityFactory.makeTestDoor()
    println(testDoor)
    println(testDoor.animationOpt.map(a => a.frame(2000)))
    println(testDoor.close())
    println(testDoor.close().animationOpt.map(a => a.frame(2000)))
}

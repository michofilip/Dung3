//package repository.old
//
//import model.EntityName.TestDoor
//import model.EntityState.{Close, Open}
//import model.graphics.AnimationSelector
//import model.state.State.Open
//import model.{AnimationSelector, EntityName}
//
//class AnimationSelectorRepository(implicit animationRepository: AnimationRepository) {
//
//    private val testDoorAnimationSelector = AnimationSelector(
//        (Some(Open), None) -> animationRepository.testDoorAnimationOpen,
//        (Some(Close), None) -> animationRepository.testDoorAnimationClose
//    )
//
//    private val animationSelectors: Map[EntityName, AnimationSelector] = Map(TestDoor -> testDoorAnimationSelector)
//
//    def getAnimationSelector(entityName: EntityName): Option[AnimationSelector] = animationSelectors.get(entityName)
//
//}

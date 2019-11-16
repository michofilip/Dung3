package repositories

import model.EntityName.TestDoor
import model.EntityState.{Close, Open}
import model.{AnimationSelector, EntityName}

class AnimationSelectorRepository(implicit animationRepository: AnimationRepository) {
    
    private val testDoorAnimationSelector = AnimationSelector(
        (Some(Open), None) -> animationRepository.testDoorAnimation1,
        (Some(Close), None) -> animationRepository.testDoorAnimation2
    )
    
    private val animationSelectors: Map[EntityName, AnimationSelector] = Map(TestDoor -> testDoorAnimationSelector)
    
    def getAnimationSelector(entityName: EntityName): Option[AnimationSelector] = animationSelectors.get(entityName)
    
}

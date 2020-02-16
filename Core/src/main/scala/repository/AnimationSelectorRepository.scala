package repository

import model.NameKey
import model.NameKey.WoodenDoor
import model.animation.AnimationSelector
import model.state.State.{Close, Open}

class AnimationSelectorRepository(implicit animationRepository: AnimationRepository) {
    
    private val testDoorAnimationSelector = AnimationSelector(
        (Some(Open), None) -> animationRepository.testDoorAnimationOpen,
        (Some(Close), None) -> animationRepository.testDoorAnimationClose
    )
    
    private val animationSelectors: Map[NameKey, AnimationSelector] = Map(WoodenDoor -> testDoorAnimationSelector)
    
    def getAnimationSelector(nameKey: NameKey): Option[AnimationSelector] = animationSelectors.get(nameKey)
    
}

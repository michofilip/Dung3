package engine.repository

import engine.entity.parts.NameKey
import engine.entity.parts.NameKey.WoodenDoor
import engine.entity.parts.animation.AnimationSelector
import engine.entity.parts.state.State.{Close, Open}

class AnimationSelectorRepository(implicit animationRepository: AnimationRepository) {

    private val testDoorAnimationSelector = AnimationSelector(
        (Some(Open), None) -> animationRepository.testDoorAnimationOpen,
        (Some(Close), None) -> animationRepository.testDoorAnimationClose
    )

    private val animationSelectors: Map[NameKey, AnimationSelector] = Map(WoodenDoor -> testDoorAnimationSelector)

    def getAnimationSelector(nameKey: NameKey): Option[AnimationSelector] = animationSelectors.get(nameKey)

}

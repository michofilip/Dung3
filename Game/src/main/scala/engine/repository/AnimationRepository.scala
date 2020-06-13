package engine.repository

import engine.entity.parts.animation.Animation.{LoopingAnimation, OneWayAnimation}
import engine.entity.parts.animation.{Animation, Frame}

class AnimationRepository(implicit spriteRepository: SpriteRepository) {
    val testDoorAnimationOpen: Animation = LoopingAnimation(fps = .5, Vector(
        Frame(spriteRepository.testDoor01),
        Frame(spriteRepository.testDoor02),
        Frame(spriteRepository.testDoor03),
        Frame(spriteRepository.testDoor04)
    ))
    val testDoorAnimationClose: Animation = OneWayAnimation(fps = .5, Vector(
        Frame(spriteRepository.testDoor05),
        Frame(spriteRepository.testDoor06),
        Frame(spriteRepository.testDoor07),
        Frame(spriteRepository.testDoor08)
    ))
}

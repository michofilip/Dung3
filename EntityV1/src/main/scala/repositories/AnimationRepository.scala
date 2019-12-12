package repositories

import model.{Animation, Frame}

class AnimationRepository(implicit imageRepository: ImageRepository) {
    val testDoorAnimationOpen: Animation = Animation(looped = true, fps = .5,
        Frame(imageRepository.testDoor01),
        Frame(imageRepository.testDoor02),
        Frame(imageRepository.testDoor03),
        Frame(imageRepository.testDoor04)
    )
    val testDoorAnimationClose: Animation = Animation(looped = false, fps = .5,
        Frame(imageRepository.testDoor05),
        Frame(imageRepository.testDoor06),
        Frame(imageRepository.testDoor07),
        Frame(imageRepository.testDoor08)
    )
}

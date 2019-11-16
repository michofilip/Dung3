package repositories

import model.Animation

class AnimationRepository(implicit imageRepository: ImageRepository) {
    val testDoorAnimation1: Animation = Animation(looped = true, fps = 30)(model.Frame(imageRepository.testDoor01))
    val testDoorAnimation2: Animation = Animation(looped = false, fps = 30)(model.Frame(imageRepository.testDoor02))
}

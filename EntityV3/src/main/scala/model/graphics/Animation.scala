package model.graphics

import commons.utils.MathUtils.{bound, floor, mod}

case class Animation(looped: Boolean, fps: Double, frames: Vector[Frame]) {
    private val length = frames.length
    
    def frame(time: Long): Frame = {
        val frameNo = floor(time * fps / 1000)
        val frameIndex = if (looped) mod(frameNo, length) else bound(frameNo, 0, length - 1)
        frames(frameIndex)
    }
}

object Animation {
    def apply(looped: Boolean, fps: Double, frames: Frame*): Animation = new Animation(looped, fps, frames.toVector)
}

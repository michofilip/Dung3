package model

case class Animation(looped: Boolean, fps: Double, frames: Vector[Frame])

object Animation {
    def apply(looped: Boolean, fps: Double)(frames: Frame*): Animation = new Animation(looped, fps, frames.toVector)
}

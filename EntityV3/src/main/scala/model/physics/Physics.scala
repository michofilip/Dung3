package model.physics

case class Physics(solid: Boolean, opaque: Boolean)

object Physics {
    val initialPhysics: Physics = Physics(solid = false, opaque = false)
}

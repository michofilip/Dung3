package engine.repository

import engine.entity.parts.physics.Physics

class PhysicsRepository {
    val physicsFF: Physics = Physics(solid = false, opaque = false)
    val physicsFT: Physics = Physics(solid = false, opaque = true)
    val physicsTF: Physics = Physics(solid = true, opaque = false)
    val physicsTT: Physics = Physics(solid = true, opaque = true)
}

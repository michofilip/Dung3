package repositories

import model.Physics

class PhysicsRepository {
    val physicsFF = Physics(solid = false, opaque = false)
    val physicsFT = Physics(solid = false, opaque = true)
    val physicsTF = Physics(solid = true, opaque = false)
    val physicsTT = Physics(solid = true, opaque = true)
}

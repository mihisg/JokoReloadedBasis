package EntityComponentSystem.components

import Physics.*
import org.jbox2d.common.Vec2

class PhysicsComponent() : Component {

    var velocity = Vec2()
    var lastFrameVelocity = Vec2()
    var position = Vec2()
    var lastFramePosition = Vec2()
    var mass = 1.0f

    var inverseMass: Float
        get() {
            if (mass != 0.0f) return 1.0f / mass
            return 0.0f
        }
        set(value) {}

    var sumOfAllForces = Vec2()
    var frictionX = 2.0
    var frictionY = 0.5

    var isGrounded = false

}

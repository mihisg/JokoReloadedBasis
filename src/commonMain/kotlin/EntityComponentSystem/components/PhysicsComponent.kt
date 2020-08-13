package EntityComponentSystem.components

import Physics.*
import org.jbox2d.common.Vec2

class PhysicsComponent(var damping: Double = 1.0) : Component {

    override val type: ComponentType = ComponentType.PHYSICS

    var velocity = Vec2()
    private var force = Vec2()
    var kineticEnergy = 0.0

    var disableGravity: Boolean = false

    override fun update() {

    }
}

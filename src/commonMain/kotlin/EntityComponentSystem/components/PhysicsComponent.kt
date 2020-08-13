package EntityComponentSystem.components

import Physics.*
import org.jbox2d.common.Vec2

class PhysicsComponent() : Component {

    override val type: ComponentType = ComponentType.PHYSICS

    var velocity = Vec2()
    var position = Vec2()

    override fun update() {

    }
}

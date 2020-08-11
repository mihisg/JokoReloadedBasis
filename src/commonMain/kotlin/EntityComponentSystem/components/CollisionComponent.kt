package EntityComponentSystem.components

import EntityComponentSystem.Component
import EntityComponentSystem.ComponentType
import EntityComponentSystem.Entity
import Physics.getTiledObjectCollisionObjects

class CollisionComponent(override val view: Entity) : Component {

    override val type: ComponentType = ComponentType.COLLISION

    init {
        //Check for Collision with TiledMapObjects -> see Physics/TiledObjectCollision.kt
        //view.getTiledObjectCollisionObjects()
    }

    override fun update(ms: Double) {

    }
}
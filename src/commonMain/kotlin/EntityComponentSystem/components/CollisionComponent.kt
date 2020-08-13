package EntityComponentSystem.components

class CollisionComponent() : Component {

    override val type: ComponentType = ComponentType.COLLISION
    override fun update() {

    }


    init {
        //Check for Collision with TiledMapObjects -> see Physics/TiledObjectCollision.kt
        //view.getTiledObjectCollisionObjects()
    }


}
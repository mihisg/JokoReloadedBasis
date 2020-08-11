package EntityComponentSystem.components

import EntityComponentSystem.Component
import EntityComponentSystem.ComponentType
import EntityComponentSystem.Entity
import com.soywiz.korge.view.View


//TODO: add the ability to display Sprites via Spine(?)
class SpriteComponent(override val view: Entity) : Component {

    override val type: ComponentType = ComponentType.SPRITE

    override fun update(ms: Double) {
        TODO("Not yet implemented")
    }

}
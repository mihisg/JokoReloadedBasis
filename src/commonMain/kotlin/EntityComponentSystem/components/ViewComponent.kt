package EntityComponentSystem.components

import com.soywiz.korge.view.*
import com.soywiz.korim.color.*

class ViewComponent() : Component {
    override val type: ComponentType = ComponentType.VIEW
    val view : View = SolidRect(100.0, 100.0, Colors.GREEN)

    init {
    }

    override fun update() {

    }
}
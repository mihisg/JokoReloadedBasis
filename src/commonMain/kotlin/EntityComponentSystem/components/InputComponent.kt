package EntityComponentSystem.components

import EntityComponentSystem.Component
import EntityComponentSystem.ComponentType
import EntityComponentSystem.Entity
import com.soywiz.klock.seconds
import com.soywiz.korev.Key
import com.soywiz.korge.input.keys
import com.soywiz.korge.view.View
import com.soywiz.korgw.delay
import org.jbox2d.common.Vec2

class InputComponent(override val view: Entity) : Component {

    override val type: ComponentType = ComponentType.INPUT

    init {
        //react to keys depending on if the view has a PhysicsComponent
        view.keys.down {
            if (!view.hasComponent(ComponentType.PHYSICS)) {
                when (it) {
                    //TODO("Adjust the distance per frame to get a smooth movement")
                    Key.UP -> view.y -= 4
                    Key.DOWN -> view.y += 4
                    Key.LEFT -> view.x -= 4
                    Key.RIGHT -> view.x += 4
                    else -> {}
                }
            } else {
                when (it) {
                    Key.LEFT -> view.getComponent<PhysicsComponent>().addForce(Vec2(-20f, 0f))
                    Key.RIGHT -> view.getComponent<PhysicsComponent>().addForce(Vec2(20f, 0f))
                    Key.SPACE -> {
                        view.getComponent<PhysicsComponent>().addForce(Vec2(0f, -700f))
                    }//Nur wenn man den Boden berührt, soll Springen möglich sein

                    else -> {
                    }
                }
            }
        }
    }

    override fun update(ms: Double) {

    }
}
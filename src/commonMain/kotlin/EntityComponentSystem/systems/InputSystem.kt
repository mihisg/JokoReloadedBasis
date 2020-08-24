package EntityComponentSystem.systems

import EntityComponentSystem.components.PhysicsComponent
import EntityComponentSystem.components.ViewComponent
import com.soywiz.korev.Key
import com.soywiz.korge.view.*
import org.jbox2d.common.Vec2

fun Stage.inputSystem() {
    InputSystem.stage = this
    addUpdater { InputSystem.update(0f) }
}

object InputSystem : System() {

    lateinit var stage: Stage

    override fun update(dt: Float) {
        entities.forEach { entity ->
            if (entity.hasComponent<ViewComponent>()) {
                if (entity.hasComponent<PhysicsComponent>()) {
                    if (stage.views.input.keys[Key.LEFT]) PhysicsSystem.addForce(Vec2(-10f, 0f), entity)
                    if (stage.views.input.keys[Key.RIGHT]) PhysicsSystem.addForce(Vec2(10f, 0f), entity)
                    if (stage.views.input.keys[Key.SPACE]) {
                        if (entity.getComponent<PhysicsComponent>().isGrounded) PhysicsSystem.addForce(
                            Vec2(0f, -700f), entity
                        )
                    }
                } else {
                    if (stage.views.input.keys[Key.LEFT]) entity.getComponent<ViewComponent>().view.x -= 4
                    if (stage.views.input.keys[Key.RIGHT]) entity.getComponent<ViewComponent>().view.x += 4
                    if (stage.views.input.keys[Key.UP]) entity.getComponent<ViewComponent>().view.y -= 4
                    if (stage.views.input.keys[Key.DOWN]) entity.getComponent<ViewComponent>().view.y += 4
                }
            }
        }
    }

}
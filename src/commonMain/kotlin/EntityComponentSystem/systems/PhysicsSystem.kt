package EntityComponentSystem.systems

import EntityComponentSystem.components.*
import EntityComponentSystem.entities.*
import Physics.*
import com.soywiz.korge.view.*
import org.jbox2d.common.*

fun Stage.physicsSystem(){
	ViewSystem.stage = this
	this.addUpdater { PhysicsSystem.update() }
}

object PhysicsSystem : System() {

	lateinit var stage: Stage
	val gravity = Vec2(0f, 0.1f)

	fun update() {
		applyPhysics()
	}

	fun applyPhysics() {
		entities.filter { it.hasComponent(ComponentType.PHYSICS) }.map { entity ->
			val dt = 16.6666

			val physicsComponent = entity.components[ComponentType.PHYSICS] as PhysicsComponent
			val viewComponent = entity.components[ComponentType.VIEW] as ViewComponent
			physicsComponent.run {

				velocity += gravity
				position += velocity

				viewComponent.view.x = (position.x.toDouble())
				viewComponent.view.y = (position.y.toDouble())

			}
		}

	}
}



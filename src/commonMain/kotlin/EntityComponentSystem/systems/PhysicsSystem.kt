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

object PhysicsSystem : System {

	lateinit var stage : Stage

	val entities = mutableListOf<Entity>()

	override fun add(entity: Entity) {
		entities.add(entity)
	}

	fun update(){
		applyPhysics()
	}

	fun applyPhysics(){
		//ms in seconds
		//val dt = ms / 1000.0

		/*
			if (!disableGravity) {
				force += Vec2(0f, 9.81f)
			}
			velocity += force * dt
			velocity *= (1 / (1 + damping * dt))
			checkCollision()
			if (disableGravity && velocity.y > 0) velocity.y = 0f
			view.x += (force.x * 0.5 * dt * dt + velocity.x * dt) * 100
			view.y += (force.y * 0.5 * dt * dt + velocity.y * dt) * 100
			kineticEnergy = velocity.lengthSquared() * 0.5
			//reset force
			force = Vec2()

		 */
	}

	fun addForce(f: Vec2) {
		force += f
	}
	}

}
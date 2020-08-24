package EntityComponentSystem.systems

import EntityComponentSystem.components.*
import EntityComponentSystem.entities.*
import Physics.*
import Physics.forces.Damping
import Physics.forces.Gravity
import com.soywiz.klock.DateTime
import com.soywiz.korev.Key
import com.soywiz.korge.input.keys
import com.soywiz.korge.view.*
import org.jbox2d.common.*

fun Stage.physicsSystem() {
    var lastUpdateRun = DateTime.nowUnix()
    ViewSystem.stage = this
    addUpdater {
        println((DateTime.nowUnix() - lastUpdateRun).toFloat())
        PhysicsSystem.update((DateTime.nowUnix() - lastUpdateRun).toFloat())
        lastUpdateRun = DateTime.nowUnix()
    }
}

object PhysicsSystem : System() {

    private var forceRegistry = ForceRegistry()
    private var gravity = Gravity(Vec2(0f, 9.81f))
    private var damping = Damping()
    private var fixedUpdateTime = 0f

    override fun add(entity: Entity) {
        entities.add(entity)
        forceRegistry.add(entity, gravity)
        forceRegistry.add(entity, damping)
        entity.getComponent<ViewComponent>().view.keys.down { if (it == Key.ENTER) forceRegistry.remove(entity, gravity) }
    }

    override fun update(dt: Float) {
        fixedUpdateTime = dt    //fix that
        fixedUpdate(fixedUpdateTime)
    }

    fun fixedUpdate(dt: Float) {
        forceRegistry.updateForces(dt)
        applyPhysics(dt)
    }

    fun applyPhysics(dt: Float) {
        val ms = dt/1000.0f
        entities.filter { it.hasComponent<ViewComponent>() }.forEach { entity ->
            val physicsComponent = entity.getComponent<PhysicsComponent>()
            val viewComponent = entity.getComponent<ViewComponent>()
            if (physicsComponent.mass == 0.0f) return

            //Calculate velocity
            val acceleration = Vec2(physicsComponent.sumOfAllForces * physicsComponent.inverseMass)
            physicsComponent.velocity += acceleration * ms

            //Update position
            physicsComponent.position += physicsComponent.velocity * ms * 120

            physicsComponent.isGrounded = false
            EntityManager.entities.filter { it.hasComponent<ViewComponent>() && it != entity }.forEach {
                onCollisionWithStaticView(entity, it.getComponent<ViewComponent>().view)
            }

            viewComponent.view.x = physicsComponent.position.x.toDouble()
            viewComponent.view.y = physicsComponent.position.y.toDouble()
            physicsComponent.sumOfAllForces.setZero()
        }
    }


    fun addForce(force: Vec2, entity: Entity) {
        if (entities.contains(entity)) entity.getComponent<PhysicsComponent>().sumOfAllForces += force
    }

    fun onCollisionWithStaticView(entity: Entity, other: View) {
        val physicsComponent = entity.getComponent<PhysicsComponent>()
        val viewComponent = entity.getComponent<ViewComponent>()
        val md = Rechteck(physicsComponent.position.x, physicsComponent.position.y, viewComponent.view.width, viewComponent.view.height).minkowskiDifference(other)
        if (md.x <= 0 && md.y <= 0 && md.x + md.width >= 0 && md.y + md.height >= 0) {
            val solvingVector = md.closestPointOnBoundsToPoint(Vec2(0f, 0f))
            if (solvingVector.x == 0f) {
                if (solvingVector.y > 0.00f) {
                    if (physicsComponent.velocity.y >= 0f) physicsComponent.velocity.y = 0f
                    physicsComponent.isGrounded = true
                    physicsComponent.position.y = (other.y - viewComponent.view.height).toFloat()
                } else {
                    physicsComponent.velocity.y = 2f
                    physicsComponent.sumOfAllForces.y = PhysicsSystem.gravity.gravityAcc.y * physicsComponent.mass
                }
            } else if (solvingVector.y == 0f) {
                if (solvingVector.x > 0f && physicsComponent.velocity.x > 0) {
                    println("Kollidiert links")
                    physicsComponent.velocity.x *= -0.3f
                    if (physicsComponent.position.x + viewComponent.view.width > other.x) physicsComponent.position.x =
                        (other.x - viewComponent.view.width).toFloat()
                } else if (solvingVector.x < 0 && physicsComponent.velocity.x < 0) {
                    println("Kollidiert rechts")
                    physicsComponent.velocity.x *= -0.3f
                    if (physicsComponent.position.x < other.width + other.x) physicsComponent.position.x =
                        (other.x + other.width).toFloat()

                }
            }
        }

    }

}



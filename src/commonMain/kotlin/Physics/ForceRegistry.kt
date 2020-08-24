package Physics

import EntityComponentSystem.components.PhysicsComponent
import EntityComponentSystem.entities.Entity
import org.jbox2d.common.Vec2

/**
 * The ForceRegistry manages all ForceGenerators and calls their [ForceGenerator.updateForce] method
 */
class ForceRegistry {
    val forceRegistrations = mutableListOf<ForceRegistration>()

    fun add(entity: Entity, forceGenerator: ForceGenerator) {
        val fr = ForceRegistration(forceGenerator, entity)
        forceRegistrations.add(fr)
    }

    fun remove(entity: Entity, forceGenerator: ForceGenerator) {
        //works because of the equals-method of ForceRegistration
        val fr = ForceRegistration(forceGenerator, entity)
        forceRegistrations.remove(fr)
    }

    fun clear() {
        forceRegistrations.clear()
    }

    fun updateForces(dt: Float) {
        forceRegistrations.forEach { fr ->
            fr.forceGenerator.updateForce(fr.entity, dt)
        }
    }

    fun zeroForces() {
        forceRegistrations.forEach {
            it.entity.getComponent<PhysicsComponent>().sumOfAllForces = Vec2()
        }
    }
}
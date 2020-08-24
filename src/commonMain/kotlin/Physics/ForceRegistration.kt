package Physics

import EntityComponentSystem.components.PhysicsComponent
import EntityComponentSystem.entities.Entity

/**
 * Just a wrapper class to manage a [ForceGenerator] and its belonging [Entity]
 * So a [ForceRegistration] is basically a connection between the ForceGenerator and the Entity the
 * force will be attached to
 * The [ForceRegistry] works with these ForceRegistrations to get access to both [ForceGenerator] and its [Entity]
 */

class ForceRegistration(val forceGenerator: ForceGenerator, val entity: Entity) {

    override fun equals(other: Any?): Boolean {
        if (other == null) return false
        if (other::class != ForceRegistration::class) return false

        val fr = other as ForceRegistration
        return fr.entity == this.entity && fr.forceGenerator == this.forceGenerator
    }

    override fun hashCode(): Int {
        var result = forceGenerator.hashCode()
        result = 31 * result + entity.hashCode()
        return result
    }
}
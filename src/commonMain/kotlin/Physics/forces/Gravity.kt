package Physics.forces

import EntityComponentSystem.components.PhysicsComponent
import EntityComponentSystem.entities.Entity
import EntityComponentSystem.systems.PhysicsSystem
import Physics.ForceGenerator
import Physics.times
import org.jbox2d.common.Vec2

class Gravity(val gravityAcc: Vec2) : ForceGenerator {

    override fun updateForce(entity: Entity, deltaTime: Float) {
        if (entity.hasComponent<PhysicsComponent>()) PhysicsSystem.addForce(gravityAcc * entity.getComponent<PhysicsComponent>().mass, entity)
    }

}
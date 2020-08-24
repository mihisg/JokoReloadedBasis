package Physics.forces

import EntityComponentSystem.components.PhysicsComponent
import EntityComponentSystem.entities.Entity
import EntityComponentSystem.systems.PhysicsSystem
import Physics.ForceGenerator
import Physics.times
import org.jbox2d.common.Vec2

class Damping : ForceGenerator {

    /*
    * How does damping(air-resistance) work? The damping-force is calculated
    * F = -k * v    (Stokes' Law)
    * where v is the actual velocity and k is a constant(the force is multiplied by -1 because its direction is
    * the opposite of the direction of v -> slows down the object
    * F = -k * v        -> m * a = -k * v       -> a = (-k/m) * v
    * Because -k/m is also a constant, let's replace it with -c
    * a = -c * v        -> dv/dt = -c * v       Differential equation(solved with separating v and t)
    * dv = -c * v * dt      -> dv/v = -c * dt   Integrate on both sides
    * integral(1/v) * dv = integral(-c) * dt
    * ln(v) + constant1 = -c * t + constant2   Adding the constants        -> ln(v) = -c * t + constant
    * v = exp(-c * t) * exp(constant)      where exp(constant) = v0
    * v(t) = v0 * exp(-c * t)
    * Let's define v1 = v(t) -> next frame velocity: v2 = v(t + dt) = v0 * exp(-c * (t + dt))
    * = v0 * exp(-c * t -c * dt) = v0 * exp(-c * t) * exp(-c * dt) = v1 * exp(-c * dt)
    * -> v2 = v1 * exp(-c * dt)
    * Almost the same(and faster to calculate) is
    * v2 = v1 * 1/(1 + c * dt)
    * The velocity depends only on one value c which is called friction-coefficient -> We already have friction in PhysicsComponent
    * So when c = friction  but also c = k/m (we defined it earlier) -> friction = k/m
    * -> k = friction * m
    * -> Finally the Damping force F = -k * v = -friction * m * v
    *
    * */


    override fun updateForce(entity: Entity, deltaTime: Float) {
        if (entity.hasComponent<PhysicsComponent>()) PhysicsSystem.addForce(
            Vec2(
                (entity.getComponent<PhysicsComponent>().velocity.x * entity.getComponent<PhysicsComponent>().mass * entity.getComponent<PhysicsComponent>().frictionX * -1).toFloat(),
                (entity.getComponent<PhysicsComponent>().velocity.y * entity.getComponent<PhysicsComponent>().mass * entity.getComponent<PhysicsComponent>().frictionY * -1).toFloat()
            ),
            entity
        )
    }
}
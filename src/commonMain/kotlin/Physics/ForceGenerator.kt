package Physics

import EntityComponentSystem.components.PhysicsComponent
import EntityComponentSystem.entities.Entity

/**
 * Simple interface which deals with a type of force, for example there is a forceGenerator which adds gravity to the objects,
 * another one will add damping, another one will add an Input-force(when the Player is controlled by the keyboard), etc.
 * Every type of Force will implement this Interface and override the [updateForce]-method to apply its force correctly.
 */
interface ForceGenerator {
    /**
     * Updates the forces for an [Entity] by taking its physicsComponent as a Parameter
     * @param entity the [Entity] on which the force will be applied(must have a [PhysicsComponent])
     * @param deltaTime the time between the frames -> necessary for a frame-independent movement
     */
    fun updateForce(entity: Entity, deltaTime: Float)
}
package EntityComponentSystem.entities

import EntityComponentSystem.*
import EntityComponentSystem.components.*
import EntityComponentSystem.entities.EntityManager.add
import EntityComponentSystem.systems.*
import com.soywiz.korge.view.*
import org.jbox2d.common.Vec2
import kotlin.reflect.KClass


class Entity() {

    //All components the Entity has are stored here
    val components = mutableMapOf<KClass<out Component>, Component>()

    init {
        /** add the Entity to the [EntityManager]       */
        EntityManager.add(this)
    }

    //destroy the entity
    fun destroy() {
        EntityManager.remove(this)
    }

    //returns true if the Entity has a component of specific type
    inline fun <reified T: Component> hasComponent(): Boolean {
        return components[T::class] != null
        /*if (T::class == PhysicsComponent::class) return components[ComponentType.PHYSICS] != null
        if (T::class == ViewComponent::class) return components[ComponentType.VIEW] != null
        if (T::class == InputComponent::class) return components[ComponentType.INPUT] != null
        // . . .*/
        //else return false
    }


    fun addComponent(component: Component) {
        components.put(component::class, component)
        when (component) {
            is ViewComponent -> ViewSystem.add(this)
            is PhysicsComponent -> {
                if (hasComponent<ViewComponent>()) component.position = Vec2(getComponent<ViewComponent>().view.x.toFloat(),
                    getComponent<ViewComponent>().view.y.toFloat()
                )
                PhysicsSystem.add(this)
            }
            is InputComponent -> InputSystem.add(this)
        }

    }

    fun removeComponent(component: Component) {
        if (components.containsValue(component)) {
            when (component) {
                is ViewComponent -> ViewSystem.remove(this)
                is PhysicsComponent -> PhysicsSystem.remove(this)
                is InputComponent -> InputSystem.remove(this)
            }
            components.remove(component::class)
        }
    }

    inline fun < reified T: Component> getComponent() : T {
        if (components[T::class] != null) return components[T::class] as T
        /*if (T::class == PhysicsComponent::class) return components[ComponentType.PHYSICS] as T
        if (T::class == ViewComponent::class) return components[ComponentType.VIEW] as T
        if (T::class == InputComponent::class) return components[ComponentType.INPUT] as T
        // . . .*/
        error("The entity has no component of this type -> cannot getComponent<${T::class}>()")
    }

}




package EntityComponentSystem.entities

import EntityComponentSystem.*
import EntityComponentSystem.components.*
import EntityComponentSystem.entities.EntityManager.add
import EntityComponentSystem.systems.*
import com.soywiz.korge.view.*


//Verbesserungsvorschläge erwünscht!!!!! (v.a. die unteren Methoden, falls es da elegantere Möglichkeiten gibt)


class Entity() {
    //If this gets false, the Entity will be removed from the game
    private var active = true

    //All components the Entity has are stored here
    val components = mutableMapOf<ComponentType, Component>()

    init {
        /** add the Entity to the [EntityManager]       */
        EntityManager.add(this)
    }

    //destroy the entity
    fun destroy() {
        active = false
        EntityManager.remove(this)
    }

    //returns true if the Entity has a component of specific type
    fun hasComponent(componentType: ComponentType) = components[componentType] != null

    fun addComponent(component: Component)  {
        components.put(component.type, component)
        when (component) {
            is ViewComponent -> ViewSystem.add(this)
            is PhysicsComponent -> PhysicsSystem.add(this)

        }

    }
        fun update(){
            components.map { it.value.update() }
        }
    }




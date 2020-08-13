package EntityComponentSystem.entities

import EntityComponentSystem.*
import EntityComponentSystem.components.*
import EntityComponentSystem.entities.EntityManager.add
import EntityComponentSystem.systems.*
import com.soywiz.korge.view.*


//Verbesserungsvorschläge erwünscht!!!!! (v.a. die unteren Methoden, falls es da elegantere Möglichkeiten gibt)


class Entity() : Container(){
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

    //returns true if the Entity has a component of this type or null
    fun hasComponent(componentType: ComponentType) = components[componentType]

    fun addComponent(component: Component)  {
        when (component.type) {
            ComponentType.VIEW -> ViewSystem.add(this)
            ComponentType.PHYSICS -> PhysicsSystem.add(this)

        }
        components.put(component.type, component)
    }
        fun update(){
            components.map { it.value.update() }
        }
    }




package EntityComponentSystem

import EntityComponentSystem.components.InputComponent
import EntityComponentSystem.components.PhysicsComponent
import EntityComponentSystem.components.SpriteComponent
import Physics.plus
import Physics.Rechteck
import com.soywiz.korim.color.Colors
import org.jbox2d.common.Vec2

/**
 * An Entity is basically just a [Rechteck]. But it can become more than that by adding
 * components to it. So an Entity is just a collection of components you can display on Scree.
 */

//Verbesserungsvorschläge erwünscht!!!!! (v.a. die unteren Methoden, falls es da elegantere Möglichkeiten gibt)
open class Entity(x: Number = 0, y: Number = 0, width: Number, height: Number, hasDisplayedRectangle: Boolean = false) :
        Rechteck(
                x, y, width, height, fillColor = if (!hasDisplayedRectangle) Colors.TRANSPARENT_WHITE else Colors.BLUE,
                strokeColor = Colors.TRANSPARENT_WHITE, strokeThickness = 0.0
        ) {

    //If this gets false, the Entity will be removed from the game
    private var active = true
    //All components the Entity has are stored here
    var componentList = mutableListOf<Component>()

    init {
        /** add the Entity to the [EntityManager]       */
        EntityManager.addEntity(this)
    }

    //destroy the entity
    fun destroy() {
        active = false
        EntityManager.removeEntity(this)
    }

    //create a new Component and add it to the Entity
    fun createComponent(component: Component) {
        //check if the Entity already has a component of this type
        componentList.forEach { if (it.type == component.type) return }
        componentList.add(component)
        this.addComponent(component)
    }

    //returns true if the Entity has a component of this type
    fun hasComponent(componentType: ComponentType): Boolean {
        componentList.forEach {
            if (it.type == componentType) return true
        }
        return false
    }

    //Returns the component specified as T if the Entity has a component of this type
    inline fun <reified T : Component> getComponent(): T {
        componentList.forEach { if (it is T) return it }
        throw Error("The Entity has no Component of this type")
    }

    //delete a component and remove it from the Entity
    fun deleteComponent(componentType: ComponentType) {
        val component = componentList.filter { it.type == componentType }
        component.forEach {
            componentList.remove(it)
            this.removeComponent(it)
        }
    }
}



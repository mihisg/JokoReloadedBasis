package EntityComponentSystem.entities

import EntityComponentSystem.entities.*
import EntityComponentSystem.systems.InputSystem
import EntityComponentSystem.systems.PhysicsSystem
import EntityComponentSystem.systems.physicsSystem
import com.soywiz.klock.hr.*
import com.soywiz.korge.view.*

//Manages all Entities by adding them to a list

fun Stage.entityManager(refreshRate : HRTimeSpan){
    this.addHrUpdater {
        //EntityManager.entities.map { it.update() }
    }
}

object EntityManager {
    val entities = mutableListOf<Entity>()

    fun add(entity: Entity) =  entities.add(entity)

    fun remove(entity: Entity) = entities.remove(entity)

}


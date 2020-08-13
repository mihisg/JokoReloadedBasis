package EntityComponentSystem.systems

import EntityComponentSystem.entities.*

abstract class System {
	val entities = mutableListOf<Entity>()

	open fun add (entity: Entity){
		entities.add(entity)
	}
}


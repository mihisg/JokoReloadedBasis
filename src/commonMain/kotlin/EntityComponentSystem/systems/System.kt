package EntityComponentSystem.systems

import EntityComponentSystem.entities.*

abstract class System {
	val entities = mutableListOf<Entity>()

	open fun add (entity: Entity){
		entities.add(entity)
	}

	open fun remove(entity: Entity) {
		if (entities.contains(entity)) entities.remove(entity)
	}

	abstract fun update(dt: Float)
}


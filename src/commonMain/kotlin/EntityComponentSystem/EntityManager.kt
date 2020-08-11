package EntityComponentSystem

//Manages all Entities by adding them to a list
object EntityManager {

    val entities = arrayListOf<Entity>()

    fun getEntities() = entities.toTypedArray()

    fun addEntity(entity: Entity) {
        entities.add(entity)
    }

    fun removeEntity(entity: Entity) {
        entities.remove(entity)
    }

}
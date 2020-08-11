import EntityComponentSystem.ComponentType
import EntityComponentSystem.Entity
import EntityComponentSystem.components.InputComponent
import EntityComponentSystem.components.PhysicsComponent
import EntityComponentSystem.components.SpriteComponent
import Physics.getTiledObjectCollisionObjects
import Physics.onTiledObjectCollision
import com.soywiz.korev.Key
import com.soywiz.korge.Korge
import com.soywiz.korge.input.keys
import com.soywiz.korge.tiled.readTiledMap
import com.soywiz.korge.tiled.tiledMapView
import com.soywiz.korge.view.addUpdater
import com.soywiz.korge.view.solidRect
import com.soywiz.korge.view.xy
import com.soywiz.korim.color.Colors
import com.soywiz.korio.file.std.resourcesVfs

suspend fun main() = Korge(width = 1800, height = 1000, bgcolor = Colors["#2b2b2b"]) {

    //SAMPLE

    val testEntity = Entity(10, 10, 10, 10, true)
    addChild(testEntity)
    //add 2 new components to the Entity
    testEntity.createComponent(InputComponent(testEntity))  //Entity can be moved with arrow-keys
    testEntity.createComponent(PhysicsComponent(testEntity, damping = 1.0)) //entity reacts to physics

    //You can check if the Entity has a component by calling:
    //hasComponent() returns true:
    if (testEntity.hasComponent(ComponentType.PHYSICS)) println("Hat Physik-Komponente")
    //hasComponent() returns false:
    if (testEntity.hasComponent(ComponentType.SPRITE)) println("Hat Sprite-Komponente")

    //keyListener to enable/disable gravity
    keys.down {
        when(it) {
            //you can get an Entity's Component and access its properties by calling
            //      entity.getComponent<SPECIFY THE COMPONENT HERE>()
            Key.ENTER -> testEntity.getComponent<PhysicsComponent>().disableGravity = true
            Key.BACKSPACE -> testEntity.getComponent<PhysicsComponent>().disableGravity = false
        }
    }
}


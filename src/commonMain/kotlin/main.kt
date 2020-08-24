import EntityComponentSystem.components.*
import EntityComponentSystem.entities.Entity
import EntityComponentSystem.entities.entityManager
import EntityComponentSystem.systems.*
import Physics.collidesWithTiledObject
import Physics.levelMap
import Physics.onTiledObjectCollision
import Utils.Direction
import com.soywiz.klock.hr.hrMilliseconds
import com.soywiz.korev.Key
import com.soywiz.korge.Korge
import com.soywiz.korge.input.keys
import com.soywiz.korge.tiled.readTiledMap
import com.soywiz.korge.view.View
import com.soywiz.korge.view.addUpdater
import com.soywiz.korim.color.Colors
import com.soywiz.korio.file.std.resourcesVfs

suspend fun main() = Korge(width = 1800, height = 1000, bgcolor = Colors["#2b2b2b"]) {

    val map = levelMap(resourcesVfs["level_1.tmx"].readTiledMap(), 1.525)
    entityManager(10.hrMilliseconds)
    viewSystem()
    physicsSystem()
    inputSystem()
    //collisionSystem()


    val ent = Entity()
    val viewc = ViewComponent( 0, 500, 800, 60)
    viewc.view.color = Colors.RED
    ent.addComponent(viewc)

    val ent2 = Entity()
    val viewc2 = ViewComponent(800, 600, 600, 70)
    viewc2.view.color = Colors.BLUE
    ent2.addComponent(viewc2)

    val ent3 = Entity()
    val viewc3 = ViewComponent(300, 100, 600, 70)
    viewc3.view.color = Colors.YELLOW
    ent3.addComponent(viewc3)

    val testEntity = Entity()
    val viewComponent = ViewComponent( 0, 0,50, 76)
    testEntity.addComponent(viewComponent)
    val inputComponent = InputComponent()
    testEntity.addComponent(inputComponent)
    val physicsComponent = PhysicsComponent().apply {mass = 0.5f; frictionY = 0.5}
    testEntity.addComponent(physicsComponent)

    val testEntity2 = Entity()
    val view2 = ViewComponent(100, 0, 50, 76)
    testEntity2.addComponent(view2)
    val phy2 = PhysicsComponent().apply { mass = 1000.0f }
    testEntity2.addComponent(phy2)

    /*keys.down {
        if (it == Key.V) testEntity.removeComponent(viewComponent)
        if (it == Key.P) testEntity.removeComponent(physicsComponent)
        if (it == Key.I) testEntity.removeComponent(inputComponent)
        if (it == Key.W) testEntity.addComponent(viewComponent)
        if (it == Key.B) testEntity.addComponent(physicsComponent)
        if (it == Key.E) testEntity.addComponent(inputComponent)
    }*/

}


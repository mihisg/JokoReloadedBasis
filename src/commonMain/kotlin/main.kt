import EntityComponentSystem.*
import EntityComponentSystem.components.*
import EntityComponentSystem.entities.*
import EntityComponentSystem.systems.*
import com.soywiz.klock.hr.*
import com.soywiz.korge.Korge
import com.soywiz.korim.color.Colors

suspend fun main() = Korge(width = 1800, height = 1000, bgcolor = Colors["#2b2b2b"]) {

    entityManager(10.hrMilliseconds)
    viewSystem()
    physicsSystem()

    val testEntity = Entity()
    val viewComponent = ViewComponent()
    val physicsComponent = PhysicsComponent()
    testEntity.addComponent(viewComponent)
    testEntity.addComponent(physicsComponent)


}


package EntityComponentSystem.components

import EntityComponentSystem.Component
import EntityComponentSystem.ComponentType
import EntityComponentSystem.Entity
import EntityComponentSystem.EntityManager
import Physics.*
import com.soywiz.klock.hr.HRTimeSpan
import com.soywiz.korge.view.collidesWith
import com.soywiz.korio.async.suspendTest
import org.jbox2d.common.Vec2

class PhysicsComponent(override val view: Entity, var damping: Double = 1.0) : Component {

    override val type: ComponentType = ComponentType.PHYSICS

    var velocity = Vec2()
    private var force = Vec2()
    var kineticEnergy = 0.0

    var disableGravity: Boolean = false

    override fun update(ms: Double) {
        //ms in seconds
        val dt = ms / 1000.0
        if (!disableGravity) {
            force += Vec2(0f, 9.81f)
        }
        velocity += force * dt
        velocity *= (1 / (1 + damping * dt))
        checkCollision()
        if (disableGravity && velocity.y > 0) velocity.y = 0f
        view.x += (force.x * 0.5 * dt * dt + velocity.x * dt) * 100
        view.y += (force.y * 0.5 * dt * dt + velocity.y * dt) * 100
        kineticEnergy = velocity.lengthSquared() * 0.5
        //reset force
        force = Vec2()
    }

    fun addForce(f: Vec2) {
        force += f
    }

    fun checkCollision() {
        /*TODO: hier muss auf kollision mit anderen Entities(Rechtecken) geprüft werden,
        dabei sollte nicht jeden Frame auf Kollision mit ALLEN anderen anwesenden Entities
        geprüft werden -> das könnte die Performance erhöhen. Vielleicht prüft man nur auf Kollision
        mit Entities, die einen bestimmten Abstand zu dieser Entity hier haben(z.B. nur auf
        Kollision prüfen, wenn die Entfernung zwischen den beiden Views < 200 ist
        Außerdem ist hier wichtig, die Richtung zu bestimmen, aus der ein View den anderen berührt,
        damit mann dann physikalische Ereignisse, wie z.B. Abprallen in verschidene Richtungen
        durchführen kann -> aus welcher Richtung kam die Kollsion?
        */
    }

}

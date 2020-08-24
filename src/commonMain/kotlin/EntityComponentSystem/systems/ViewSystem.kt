package EntityComponentSystem.systems

import EntityComponentSystem.components.*
import EntityComponentSystem.entities.*
import Physics.minkowskiDifference
import Utils.Direction
import com.soywiz.korge.view.*
import com.soywiz.korim.color.Colors
import org.jbox2d.common.Vec2

fun Stage.viewSystem(){
	ViewSystem.stage = this
	//addUpdater { ViewSystem.update() }
}

object ViewSystem : System() {
	lateinit var stage : Stage

	override fun add(entity: Entity) {
		if (entity.hasComponent<ViewComponent>()) {
			val v = (entity.getComponent<ViewComponent>()).view

			super.add(entity)
			stage.addChild(v)
		}
	}

	override fun remove(entity: Entity) {
		super.remove(entity)
		stage.removeChild(entity.getComponent<ViewComponent>().view)
	}

	override fun update(dt: Float) {

	}
}
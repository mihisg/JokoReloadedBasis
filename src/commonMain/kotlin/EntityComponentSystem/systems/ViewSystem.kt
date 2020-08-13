package EntityComponentSystem.systems

import EntityComponentSystem.components.*
import EntityComponentSystem.entities.*
import com.soywiz.korge.view.*

fun Stage.viewSystem(){
	ViewSystem.stage = this
}

object ViewSystem : System() {
	lateinit var stage : Stage

	override fun add(entity: Entity) {
		if (entity.hasComponent(ComponentType.VIEW)){
			val v = (entity.components[ComponentType.VIEW] as ViewComponent).view

			stage.addChild(v)
		}
	}
}
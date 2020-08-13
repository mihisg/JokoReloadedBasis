package EntityComponentSystem.systems

import EntityComponentSystem.components.*
import EntityComponentSystem.entities.*
import com.soywiz.korge.view.*

fun Stage.viewSystem(){
	ViewSystem.stage = this
}

object ViewSystem : System {

	lateinit var stage : Stage

	val views = mutableListOf<View>()

	override fun add(entity: Entity) {
		val v = (entity.components[ComponentType.VIEW] as ViewComponent).view
		views.add(v)
		stage.addChild(v)
	}


}
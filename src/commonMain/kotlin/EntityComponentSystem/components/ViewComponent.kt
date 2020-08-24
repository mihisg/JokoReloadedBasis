package EntityComponentSystem.components

import Physics.onTiledObjectCollision
import com.soywiz.korev.Key
import com.soywiz.korge.input.keys
import com.soywiz.korge.view.*
import com.soywiz.korim.color.*
import com.soywiz.korma.geom.Rectangle

class ViewComponent(x: Number, y: Number, width: Number, height: Number) : Component {
    val view : SolidRect = SolidRect(width.toDouble(), height.toDouble(), Colors.GREEN).xy(x, y)

}
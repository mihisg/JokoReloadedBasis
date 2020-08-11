package Physics

import com.soywiz.korge.view.View
import org.jbox2d.common.Vec2

//simple math functions stored in this .kt-file

operator fun Vec2.times(value: Number) : Vec2 {
    return Vec2(this.x * value.toFloat(), this.y * value.toFloat())
}

operator fun Vec2.plus(other: Vec2): Vec2 {
    return Vec2(this.x + other.x, this.y + other.y)
}

fun distanceBetweenViewsSqared(viewA: View, viewB: View): Double {
    return (((viewA.x - viewB.x) * (viewA.x - viewB.x)) + ((viewA.y - viewB.y) * (viewA.y - viewB.y)))
}
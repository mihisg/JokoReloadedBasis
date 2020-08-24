package Physics

import com.soywiz.korge.view.View
import com.soywiz.korim.color.Colors
import com.soywiz.korma.geom.Rectangle
import org.jbox2d.common.Vec2

//simple math functions stored in this .kt-file

operator fun Vec2.times(value: Number) : Vec2 {
    return Vec2(this.x * value.toFloat(), this.y * value.toFloat())
}

operator fun Vec2.plus(other: Vec2): Vec2 {
    return Vec2(this.x + other.x, this.y + other.y)
}

operator fun Vec2.div(value: Number) : Vec2 {
    val tmp = 1.0/value.toDouble()
    return this.times(tmp)
}

fun distanceBetweenViewsSqared(viewA: View, viewB: View): Double {
    return (((viewA.x - viewB.x) * (viewA.x - viewB.x)) + ((viewA.y - viewB.y) * (viewA.y - viewB.y)))
}

fun View.minkowskiDifference(other: View): Rechteck {
    val x = this.x - (other.x + other.width)
    val y = this.y - (other.y + other.height)
    val width = this.width + other.width
    val height = this.height + other.height
    return Rechteck(x, y, width, height)
}

/*fun View.minkowskiDifference(other: Rectangle): Rechteck {
    val x = this.x - (other.x + other.width)
    val y = this.y - (other.y + other.height)
    val width = this.width + other.width
    val height = this.height + other.height
    return Rechteck(x, y, width, height, Colors.GREEN, Colors.GREEN, 0)
}*/
package Physics

import com.soywiz.korge.view.Container
import com.soywiz.korge.view.Graphics
import com.soywiz.korge.view.View
import com.soywiz.korge.view.addTo
import com.soywiz.korim.color.Colors
import com.soywiz.korim.color.RGBA
import com.soywiz.korim.vector.Context2d
import com.soywiz.korim.vector.paint.ColorPaint
import com.soywiz.korma.geom.vector.rect
import org.jbox2d.common.Vec2
import kotlin.math.abs

open class Rechteck(xPos: Number,
                    yPos: Number,
                    width: Number,
                    height: Number,
                    fillColor: RGBA = Colors.GREEN,
                    strokeColor: RGBA = Colors.GREEN,
                    strokeThickness: Number = 0) : Graphics(true) {

    init {
        this.x = xPos.toDouble()
        this.y = yPos.toDouble()
        apply {
            fillStroke(
                ColorPaint(fillColor),
                ColorPaint(strokeColor),
                Context2d.StrokeInfo(strokeThickness.toDouble())
            ) {
                rect(0.0, 0.0, width.toDouble(), height.toDouble())
            }
        }
    }

    fun closestPointOnBoundsToPoint(point: Vec2) : Vec2 {
        var minDist = abs(point.x - this.x)
        var boundsPoint = Vec2(-minDist.toFloat(), 0f)
        if (abs(this.x + this.width - point.x) < minDist) {
            minDist = abs(this.x + this.width - point.x)
            boundsPoint = Vec2(minDist.toFloat(), 0f)
        }
        if (abs(this.y + this.height - point.y) < minDist) {
            minDist = abs(this.y + this.height - point.y)
            boundsPoint = Vec2(0f, minDist.toFloat())
        }
        if (abs(this.y - point.y) < minDist) {
            minDist = abs(this.y - point.y)
            boundsPoint = Vec2(0f, -minDist.toFloat())
        }
        return boundsPoint
    }
}

inline fun Container.rechteck(
    x: Number = 0,
    y: Number = 0,
    width: Number = 100,
    height: Number = 100,
    fillColor: RGBA = Colors.RED,
    strokeColor: RGBA = Colors.RED,
    strokeThickness: Number = 0,
    callback: Rechteck.() -> Unit = {}
) : Rechteck = Rechteck(x, y, width, height, fillColor, strokeColor, strokeThickness).addTo(this).apply(callback)
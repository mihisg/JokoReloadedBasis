package Physics

import com.soywiz.korge.view.Container
import com.soywiz.korge.view.Graphics
import com.soywiz.korge.view.addTo
import com.soywiz.korim.color.Colors
import com.soywiz.korim.color.RGBA
import com.soywiz.korim.vector.Context2d
import com.soywiz.korim.vector.paint.ColorPaint
import com.soywiz.korma.geom.vector.rect

open class Rechteck(xPos: Number,
                    yPos: Number,
                    width: Number,
                    height: Number,
                    fillColor: RGBA,
                    strokeColor: RGBA,
                    strokeThickness: Number) : Graphics(true) {

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

    fun intersects(other: Rechteck): Boolean {
        //TODO
        return true
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
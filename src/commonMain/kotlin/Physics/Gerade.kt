package Physics

import com.soywiz.korge.view.Container
import com.soywiz.korge.view.Graphics
import com.soywiz.korge.view.addTo
import com.soywiz.korge.view.sgraphics
import com.soywiz.korim.color.Colors
import com.soywiz.korim.color.RGBA
import com.soywiz.korim.vector.Context2d
import com.soywiz.korim.vector.paint.ColorPaint
import com.soywiz.korma.geom.vector.line

class Gerade(xPos: Number, yPos: Number, toX: Number, toY: Number, fillColor: RGBA = Colors.RED, thickness: Double = 0.0) : Graphics(true) {

    private var graphics = sgraphics()

    init {
        this.x = xPos.toDouble()
        this.y = yPos.toDouble()
        apply {
            fillStroke(
                ColorPaint(fillColor),
                ColorPaint(fillColor),
                Context2d.StrokeInfo(thickness)
            ) {
                moveTo(0.0, 0.0)
                line(0.0, 0.0, toX.toDouble() - x, toY.toDouble() - y)
            }
        }
    }
}


inline fun Container.gerade(
    x: Number = 0,
    y: Number = 0,
    toX: Number = 0,
    toY: Number = 0,
    fillColor: RGBA = Colors.BLUE,
    thickness: Number = 3,
    callback: Gerade.() -> Unit = {}
) : Gerade = Gerade(x, y, toX, toY, fillColor, thickness.toDouble()).addTo(this).apply(callback)
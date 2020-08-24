package Physics

import com.soywiz.korge.tiled.TiledMap
import com.soywiz.korge.tiled.TiledMapView
import com.soywiz.korge.tiled.bounds
import com.soywiz.korge.tiled.tiledMapView
import com.soywiz.korge.view.Stage
import com.soywiz.korge.view.View
import com.soywiz.korge.view.addUpdater
import com.soywiz.korma.geom.Rectangle

fun Stage.levelMap(tiledMap: TiledMap, scale: Double): LevelMap {
    return LevelMap(tiledMap, scale, stage = this)
}

class LevelMap(var tiledMap: TiledMap, val scl: Double, val stage: Stage) {

    init {
        stage.tiledMapView(tiledMap) { scale = scl }
    }

    /**
     * This function returns a mutable list of [Rectangle] which holds the position and size of all tiledMap-objects which are
     * needed for collision. You can configure them with this function
     * @param layerName the name of the ObjectLayer in Tiled Level Editor. The Rectangles returned by this function can only contain
     * objects from this object layer
     * @param objectNames an array of all names of the objects which are added to the collidable list. Here you have to put all
     * the names of the objects you need of your layer(defined by [layerName]) which will be checked for collision(the name is one
     * of the parameters of objects in "Tiled Level Editor"). If you want to have all objects from your specified layer, you
     * don't have to write all the names: instead set the value of [objectNames] to [null] -> all objects of the layer are checked
     * for collision with the [View].
     * Don't forget to add another function after this one to apply the collision checks(either [View.collidesWithTiledObject] or
     * [View.onTiledObjectCollision]
     */
    fun getCollisionObjects(
        layerName: String,
        objectNames: Array<String>? = null
    ): MutableList<Rectangle?> {
        val collidables = mutableListOf<Rectangle?>()

        if (objectNames != null) {
            objectNames.forEach {
                val tmp = tiledMap.getLayerByName(layerName).getByName(it)?.bounds
                if (tmp != null) {
                    collidables.add(
                        Rectangle(
                            tmp.x * scl,
                            tmp.y * scl,
                            tmp.width * scl,
                            tmp.height * scl
                        )
                    )
                }
            }
        } else {
            for (i in 0 until (tiledMap.getLayerByName(layerName).objects.size)) {
                val tmp = tiledMap.getLayerByName(layerName).objects.get(i).bounds
                collidables.add(
                    Rectangle(
                        tmp.x * scl,
                        tmp.y * scl,
                        tmp.width * scl,
                        tmp.height * scl
                    )
                )
            }
        }

        return collidables
    }

}


/**
 * Checks for collision between a [View] and a [TiledMap.objectLayers]-object.
 * Returns true if there is a collision with one of the [Rectangle]s passed as a parameter
 * @param collisionObjects A mutable list of [Rectangle] objects which represent the tiled-object-collidables.
 * You can get the mutable list by calling [View.getTiledObjectCollisionObjects]. This function takes parameters to configure
 * the map and the objects on a specific object-layer and returns a mutable list of [Rectangle]-collidables
 */
fun View.collidesWithTiledObject(
    map: LevelMap, layerName: String, objectNames: Array<String>? = null
): Boolean {

    val collisionObjects = map.getCollisionObjects(layerName, objectNames)

    collisionObjects.onEach {
        if (this collidesWithRectangle it) {
            return true
        }
    }
    return false
}


/**
 * Does the same like [View.collidesWithTiledObject] but this function takes a callback-function as a parameter which
 * will be executed every time there is a collision with an object from [collisionObjects]. This function is executed once
 * per frame and checks for the collision -> apply callback
 * @param collisionObjects mutable list of [Rectangle] objects which represents the tiled objects the View can collide with.
 * @see [View.collidesWithTiledObject] for more explanation
 * @param collisionCallback the function which is executed every time a collision between the View and one object of
 * [collisionObjects] is detected
 */
fun <T : View> T.onTiledObjectCollision(
    map: LevelMap, layerName: String, objectNames: Array<String>? = null,
    collisionCallback: T.() -> Unit
) {

    this.addUpdater {
        if (this.collidesWithTiledObject(map, layerName, objectNames)) {
            collisionCallback()
        }
    }
}


/**
 * Simple function to check for collision between a [View] and a [Rectangle]. The Rectanlge isn't
 * displayed on Screen because it's no [View], but this only checks if the View collides with Coordinates
 * matching an imaginary Rectangle -> Can be used for Collisionchecking with TiledMapObjects, because these
 * are transformed into a [Rectanlge]
 */
private infix fun View.collidesWithRectangle(other: Rectangle?): Boolean {
    return if (other != null) {
        //val md = this.minkowskiDifference(other)
        //md.x <= 0 && md.y <= 0 && md.x + md.width >= 0 && md.y + md.height >= 0
        true
    } else false
}


fun TiledMap.getLayerByName(name: String): TiledMap.Layer.Objects {
    val names = this.objectLayers.associateBy { it.name }
    return names[name] ?: error("The Object-Layer $name does not exist")
}



package Physics

import com.soywiz.korge.tiled.TiledMap
import com.soywiz.korge.tiled.bounds
import com.soywiz.korge.view.View
import com.soywiz.korge.view.addUpdater
import com.soywiz.korma.geom.Rectangle


/**
 * Checks for collision between a [View] and a [TiledMap.objectLayers]-object.
 * Returns true if there is a collision with one of the [Rectangle]s passed as a parameter
 * @param collisionObjects A mutable list of [Rectangle] objects which represent the tiled-object-collidables.
 * You can get the mutable list by calling [View.getTiledObjectCollisionObjects]. This function takes parameters to configure
 * the map and the objects on a specific object-layer and returns a mutable list of [Rectangle]-collidables
 */
fun View.collidesWithTiledObject(
    collisionObjects: MutableList<Rectangle?>
): Boolean {

    collisionObjects.onEach {
        if (this collidesWithMapObject it) {
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
    collisionObjects: MutableList<Rectangle?>,
    collisionCallback: T.() -> Unit
) {

    this.addUpdater {
        if (this.collidesWithTiledObject(collisionObjects)) {
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
private infix fun View.collidesWithMapObject(other: Rectangle?): Boolean {
    if (other != null) {
        if (this.x + this.width < other.x) return false
        else if (this.y + this.height < other.y) return false
        else if (other.x + other.width < this.x) return false
        else return other.y + other.height >= this.y
    } else return false
}


/**
 * This function returns a mutable list of [Rectangle] which holds the position and size of all tiledMap-objects which are
 * needed for collision. You can configure them with this function
 * @param currentMap the map(.tmx) on which the objects you need for collision are placed
 * @param mapScale the scale of the map. It is needed to adjust the position and size of the Rectangles correctly
 * with the correct scale
 * @param layerIndex the index of the object layer on which the needed objects are placed in "Tiled Level Editor".
 * You get the correct index number by counting the object(!)-layers from bottom to top in "Tiled Level Editor". You have
 * to start with 0 and count your layers to get the correct number for your layer. This function can only deal with objects
 * from one layer, so if you have more layers, you will have to make own mutableLists for each layer -> call this function more
 * than once
 * @param objectNames an array of all names of the objects which are added to the collidable list. Here you have to put all
 * the names of the objects you need of your layer(defined by [layerIndex]) which will be checked for collision(the name is one
 * of the parameters of objects in "Tiled Level Editor"). If you want to have all objects from your specified layer, you
 * don't have to write all the names: instead set the value of [objectNames] to [null] -> all objects of the layer are checked
 * for collision with the [View].
 * Don't forget to add another function after this one to apply the collision checks(either [View.collidesWithTiledObject] or
 * [View.onTiledObjectCollision]
 */
fun View.getTiledObjectCollisionObjects(
    currentMap: TiledMap,
    mapScale: Double,
    layerIndex: Int,
    objectNames: Array<String>? = null
): MutableList<Rectangle?> {
    val collidables = mutableListOf<Rectangle?>()

    currentMap.apply {
        if (objectNames != null) {
            objectNames.forEach {
                val tmp = currentMap.objectLayers[layerIndex].getByName(it)?.bounds
                if (tmp != null) {
                    collidables.add(
                        Rectangle(
                            tmp.x * mapScale,
                            tmp.y * mapScale,
                            tmp.width * mapScale,
                            tmp.height * mapScale
                        )
                    )
                }
            }
        } else {
            for (i in 0 until currentMap.objectLayers[layerIndex].objects.size) {
                val tmp = currentMap.objectLayers[layerIndex].objects[i].bounds
                collidables.add(
                    Rectangle(
                        tmp.x * mapScale,
                        tmp.y * mapScale,
                        tmp.width * mapScale,
                        tmp.height * mapScale
                    )
                )
            }
        }
    }

    return collidables
}


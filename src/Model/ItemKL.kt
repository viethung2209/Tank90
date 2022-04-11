package Model

import java.awt.Image
import javax.swing.ImageIcon

class ItemKL :Object2DKL(){
    var typeID:Int = 0
//    var timeToTouchDie:Long = 0
    var timeToTouchDie = 0L
    var animation:Animation ?= null
    companion object{
        const val ROCK = 5
        const val BRICK = 1
        const val WATER = 2
        const val TREE = 4
        const val HOME = 9
    }
   fun isTouchDie():Boolean {
       return animation != null
   }
    fun getImage(typeID :Int): Image? {
        when (typeID) {
            BRICK -> {
                return ImageIcon(
                        ItemKL::class.java.getResource(
                                "/img/brick.png"
                        )
                ).image
            }
            WATER -> {
                return ImageIcon(
                        ItemKL::class.java.getResource(
                                "/img/water.png"
                        )
                ).image
            }
            TREE -> {
                return ImageIcon(
                        ItemKL::class.java.getResource(
                                "/img/tree.png"
                        )
                ).image
            }
            HOME -> {
                return ImageIcon(
                        ItemKL::class.java.getResource(
                                "/img/bird.png"
                        )
                ).image
            }
            ROCK -> {
                return ImageIcon(
                        ItemKL::class.java.getResource(
                                "/img/rock.png"
                        )
                ).image
            }
            else -> return null
        }
    }
}
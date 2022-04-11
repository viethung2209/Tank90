package Model

import java.awt.Graphics2D
import java.awt.Image

open class Object2DKL {
    var x:Int = 0
    var y:Int = 0
    var w:Int = 0
    var h:Int = 0
    var image:Image ?= null //Dau "?" : co the NULL
    fun test():Boolean{
        return true;
    }
    open fun draw(graphics2D: Graphics2D){
        graphics2D.drawImage(image, x, y , w, h , null)
    }
}
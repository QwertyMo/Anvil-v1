package core.scene

import core.model.Color
import core.obj.AnvilObject
import core.render.Draw
import org.joml.Vector2f
import org.joml.Vector2i

abstract class AnvilScene(scene:()-> Unit) : IAnvilScene{

    private val sceneObjects: MutableMap<String, AnvilObject> = mutableMapOf()

    fun getObjects():MutableMap<String, AnvilObject>{
        return sceneObjects
    }

    fun addObject(name: String, obj: AnvilObject){
        if(sceneObjects[name]==null)
            sceneObjects[name] = obj
        else {
            //TODO: Log that we can't rewrite object
        }
    }

    fun removeObject(name: String){
        sceneObjects.remove(name)
    }

    fun getObject(name:String):AnvilObject?{
        return sceneObjects[name]
    }

    fun line(
        point1: Vector2i,
        point2: Vector2i,
        color: Color
    ){
        Draw.line(
            Vector2f((point1.x/size().x).toFloat(), (point1.y/size().y).toFloat()),
            Vector2f((point2.x/size().x).toFloat(), (point2.y/size().y).toFloat()),
            color
        )
    }

    init {
        scene()
    }
}
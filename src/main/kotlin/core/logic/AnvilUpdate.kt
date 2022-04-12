package core.logic

import core.common.SyncTimer
import core.obj.AnvilObject
import org.lwjgl.glfw.GLFW

class AnvilUpdate(
    private val window: Long,
    private val tps: Double
) {
    private var TPSTimer = SyncTimer(SyncTimer.LWJGL_GLFW)

    private var sceneObjects: MutableMap<String, AnvilObject> = mutableMapOf()

    fun setObjectList(objectList: MutableMap<String, AnvilObject>){
        sceneObjects = objectList
    }

    fun run(){
        while (!GLFW.glfwWindowShouldClose(window)) {
            update()
            TPSTimer.sync(tps)
        }
    }

    private fun update(){
        for(i in sceneObjects) i.value.update()
    }
}
package core.logic

import core.common.SyncTimer
import core.obj.AnvilObject
import org.lwjgl.glfw.GLFW

class AnvilUpdate(
    private val window: Long,
    private val tps: Double
) {
    private var TPSTimer = SyncTimer(SyncTimer.LWJGL_GLFW)

    private val objList = mutableListOf<AnvilObject>()

    fun registerObject(obj: AnvilObject){
        objList.add(obj)
    }

    fun run(){
        while (!GLFW.glfwWindowShouldClose(window)) {
            update()
            TPSTimer.sync(tps)
        }
    }

    private fun update(){
        for(i in objList) i.update()
    }
}
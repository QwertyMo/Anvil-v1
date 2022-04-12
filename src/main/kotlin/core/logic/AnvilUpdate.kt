package core.logic

import core.common.SyncTimer
import core.scene.AnvilScene
import core.scene.EmptyScene
import org.lwjgl.glfw.GLFW

class AnvilUpdate(
    private val window: Long,
    private val tps: Double
) {
    private var tpsTimer = SyncTimer(SyncTimer.LWJGL_GLFW)

    private var scene: AnvilScene = EmptyScene {}

    fun setScene(scene: AnvilScene){
        this.scene = scene
    }

    fun run(){
        while (!GLFW.glfwWindowShouldClose(window)) {
            update()
            tpsTimer.sync(tps)
        }
    }

    private fun update(){
        for(i in scene.getObjects()) i.value.update()
    }
}
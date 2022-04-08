package core

import org.lwjgl.glfw.GLFW

class AnvilUpdate(
    private val window: Long,
    private val tps: Double
) {
    private var TPSTimer = SyncTimer(SyncTimer.LWJGL_GLFW)

    init {
        while (!GLFW.glfwWindowShouldClose(window)) {
            update()
            TPSTimer.sync(tps)
        }
    }

    private fun update(){

    }
}
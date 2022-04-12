package core.render

import core.obj.AnvilObject
import core.common.SyncTimer
import org.lwjgl.glfw.GLFW
import org.lwjgl.opengl.GL
import org.lwjgl.opengl.GL11

class AnvilRender(
    private val window: Long,
    private val fps: Double
) {
    private var FPSTimer = SyncTimer(SyncTimer.LWJGL_GLFW)
    private var sceneObjects: MutableMap<String, AnvilObject> = mutableMapOf()

    fun setObjectList(objectList: MutableMap<String, AnvilObject>){
        sceneObjects = objectList
    }

    fun run(){
        GL.createCapabilities()
        GL11.glClearColor(0f, 0f, 0.0f, 0.0f)
        while (!GLFW.glfwWindowShouldClose(window)) {
            GL11.glClear(GL11.GL_COLOR_BUFFER_BIT or GL11.GL_DEPTH_BUFFER_BIT)
            render()
            GLFW.glfwSwapBuffers(window)
            GLFW.glfwPollEvents()
            FPSTimer.sync(fps);
        }
    }

    private fun render() {
        for(i in sceneObjects) i.value.render()
    }

}
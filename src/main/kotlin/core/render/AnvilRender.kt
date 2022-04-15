package core.render

import core.common.SyncTimer
import core.scene.AnvilScene
import core.scene.EmptyScene
import org.joml.Vector2f
import org.lwjgl.glfw.GLFW
import org.lwjgl.opengl.GL
import org.lwjgl.opengl.GL11

class AnvilRender(
    private val window: Long,
    private val fps: Double
) {
    private var fpsTimer = SyncTimer(SyncTimer.LWJGL_GLFW)
    private var scene: AnvilScene = EmptyScene {}

    fun setScene(scene: AnvilScene){
        this.scene = scene
    }
    fun run(){
        GL.createCapabilities()
        GL11.glClearColor(0f, 0f, 0.0f, 0.0f)
        while (!GLFW.glfwWindowShouldClose(window)) {
            GL11.glClear(GL11.GL_COLOR_BUFFER_BIT or GL11.GL_DEPTH_BUFFER_BIT)
            render()
            GLFW.glfwSwapBuffers(window)
            GLFW.glfwPollEvents()
            fpsTimer.sync(fps)
        }
    }

    private fun render() {

        for(i in scene.getObjects()) {
            val obj = i.value.render()
            val scale = i.value.scale
            val rotation = (i.value.rotation * Math.PI/180).toFloat()
            if(obj is Line){
                Draw.line(
                    Vector2f((obj.point1.x/scene.size().x), (obj.point1.y/scene.size().y)),
                    Vector2f((obj.point2.x/scene.size().x), (obj.point2.y/scene.size().y)),
                    obj.color,
                    scale,
                    rotation)
            }
            else if(obj is Rectangle){
                Draw.rectangle(
                    Vector2f((obj.point1.x/scene.size().x) , (obj.point1.y/scene.size().y)),
                    Vector2f((obj.point2.x/scene.size().x), (obj.point2.y/scene.size().y)),
                    obj.color,
                    scale,
                    rotation)
            }

        }
    }

}
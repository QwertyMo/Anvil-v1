package core

import core.model.Color
import core.model.Point
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import org.lwjgl.glfw.Callbacks
import org.lwjgl.glfw.GLFW
import org.lwjgl.glfw.GLFWErrorCallback
import org.lwjgl.opengl.GL
import org.lwjgl.opengl.GL11
import org.lwjgl.system.MemoryStack
import org.lwjgl.system.MemoryUtil
import java.util.concurrent.Flow
import kotlin.math.cos
import kotlin.math.sin

class Anvil{
    val TPS    = 120.0
    val FPS    = 60.0

    val height = 480
    val width  = 600

    val title  = "Anvil"

    private var FPSTimer = SyncTimer(SyncTimer.LWJGL_GLFW)

    private var window: Long = 0

    suspend fun run() {
        coroutineScope {
            init()
            launch { AnvilUpdate(
                window,
                TPS
            ) }
            renderLoop()

            Callbacks.glfwFreeCallbacks(window)
            GLFW.glfwDestroyWindow(window)

            GLFW.glfwTerminate()
            GLFW.glfwSetErrorCallback(null)!!.free()
        }
    }

    private fun init() {
        GLFWErrorCallback.createPrint(System.err).set()

        check(GLFW.glfwInit()) { "Unable to initialize GLFW" }

        GLFW.glfwDefaultWindowHints() // optional, the current window hints are already the default
        GLFW.glfwWindowHint(GLFW.GLFW_VISIBLE, GLFW.GLFW_FALSE) // the window will stay hidden after creation
        GLFW.glfwWindowHint(GLFW.GLFW_RESIZABLE, GLFW.GLFW_FALSE) // the window will be resizable

        window = GLFW.glfwCreateWindow(width, height, title, MemoryUtil.NULL, MemoryUtil.NULL)
        if (window == MemoryUtil.NULL) throw RuntimeException("Failed to create the GLFW window")

        keyCallback()
        MemoryStack.stackPush().use { stack ->
            val pWidth = stack.mallocInt(1)
            val pHeight = stack.mallocInt(1)

            GLFW.glfwGetWindowSize(window, pWidth, pHeight)

            val vidmode = GLFW.glfwGetVideoMode(GLFW.glfwGetPrimaryMonitor())

            GLFW.glfwSetWindowPos(
                window,
                (vidmode!!.width() - pWidth[0]) / 2,
                (vidmode.height() - pHeight[0]) / 2
            )
        }

        GLFW.glfwMakeContextCurrent(window)
        GLFW.glfwSwapInterval(1)
        GLFW.glfwShowWindow(window)
    }

    private fun keyCallback(){
        GLFW.glfwSetKeyCallback(
            window
        ) { window: Long, key: Int, scancode: Int, action: Int, mods: Int ->
            if (key == GLFW.GLFW_KEY_ESCAPE && action == GLFW.GLFW_RELEASE) GLFW.glfwSetWindowShouldClose(
                window,
                true
            )
        }

    }

    private fun renderLoop() {
        GL.createCapabilities()
        GL11.glClearColor(0f, 0f, 0.0f, 0.0f)
        while (!GLFW.glfwWindowShouldClose(window)) {
            GL11.glClear(GL11.GL_COLOR_BUFFER_BIT or GL11.GL_DEPTH_BUFFER_BIT)
            render()
            GLFW.glfwSwapBuffers(window)
            GLFW.glfwPollEvents()
            FPSTimer.sync(FPS);
        }
    }

    fun line(point1: Point, point2: Point, color: Color){
        val c = color.glColor()
        GL11.glColor3f(c.r, c.g, c.b)
        GL11.glBegin(GL11.GL_LINES)
        GL11.glVertex2f(point1.x, point1.y);
        GL11.glVertex2f(point2.x, point2.y)
        GL11.glEnd()
    }

    private fun render() {
        line(
            Point(0.0f,0.0f),
            Point(1.0f,1.0f),
            Color(255,128,255)
        )
    }

}
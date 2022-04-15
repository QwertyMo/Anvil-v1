package core

import core.logic.AnvilUpdate
import core.render.AnvilRender
import core.scene.AnvilScene
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import org.lwjgl.glfw.Callbacks
import org.lwjgl.glfw.GLFW
import org.lwjgl.glfw.GLFWErrorCallback
import org.lwjgl.system.MemoryStack
import org.lwjgl.system.MemoryUtil

class Anvil{
    private val tps    = 120.0
    private val fps    = 60.0

    private val height = 500
    private val width  = 600

    private val title  = "Anvil"

    private var window : Long        = 0

    private lateinit var update: AnvilUpdate
    private lateinit var render: AnvilRender

    fun runScene(scene: AnvilScene){
        update.setScene(scene)
        render.setScene(scene)
    }

    suspend fun run(game: (engine: Anvil)->Unit) {
        coroutineScope {
            init()

            update = AnvilUpdate(window, tps)
            render = AnvilRender(window, fps)

            launch {
                update.run()
            }

            launch{
                game(this@Anvil)
            }

            render.run()

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

}

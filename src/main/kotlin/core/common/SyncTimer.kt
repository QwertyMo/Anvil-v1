package core.common
import org.lwjgl.glfw.GLFW


class SyncTimer(mode: Int) {
    var mode = 0
        private set
    private var timeThen = 0.0
    var isEnabled = true

    init {
        setNewMode(mode)
    }

    private val resolution: Double
        private get() {
            when (mode) {
                JAVA_NANO -> return NANO_RESOLUTION
                LWJGL_GLFW -> return GLFW_RESOLUTION
            }
            return 0.0
        }
    private val time: Double
        private get() {
            when (mode) {
                JAVA_NANO -> return System.nanoTime().toDouble()
                LWJGL_GLFW -> return GLFW.glfwGetTime()
            }
            return 0.0
        }

    fun setNewMode(timerMode: Int) {
        mode = timerMode
        timeThen = time
        println("Timer mode set to $modeString timer")
    }

    val modeString: String?
        get() {
            when (mode) {
                JAVA_NANO -> return "NANO"
                LWJGL_GLFW -> return "LWJGL"
            }
            return null
        }

    @Throws(Exception::class)
    fun sync(fps: Double): Int {
        val resolution = resolution
        var timeNow = time
        var updates = 0
        if (isEnabled) {
            var gapTo = resolution / fps + timeThen
            while (gapTo < timeNow) {
                gapTo = resolution / fps + gapTo
                updates++
            }
            while (gapTo > timeNow) {
                Thread.sleep(1)
                timeNow = time
            }
            updates++
            timeThen = gapTo
        } else {
            while (timeThen < timeNow) {
                timeThen = resolution / fps + timeThen
                updates++
            }
        }
        return updates
    }

    companion object {
        const val NANO_RESOLUTION = 1000000000.0
        const val GLFW_RESOLUTION = 1.0
        const val JAVA_NANO = 1
        const val LWJGL_GLFW = 2
    }
}
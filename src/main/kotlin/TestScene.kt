import core.scene.AnvilScene
import org.joml.Vector2i

class TestScene(scene: () -> Unit) : AnvilScene(scene) {
    override fun size(): Vector2i {
        return Vector2i(10000,10000)
    }

    init {
        addObject("test",LineObject())
    }

}
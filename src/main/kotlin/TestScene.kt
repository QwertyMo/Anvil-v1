import core.model.Color
import core.scene.AnvilScene
import org.joml.Vector2f
import org.joml.Vector2i

class TestScene(scene: () -> Unit) : AnvilScene(scene) {
    override fun size(): Vector2i {
        return Vector2i(600,500)
    }

    init {
        addObject("test", RectangleObject(
            Vector2f(200f,200f),
            Vector2f(400f,400f),
            Color(255,0,255)
        ){
            it.rotation = 45f
        })
        addObject("test1", LineObject(
            Vector2f(200f,200f),
            Vector2f(400f,400f),
            Color(0,255,0)
        ){
            it.rotation = -45f
        })
        addObject("test2", LineObject(
            Vector2f(200f,200f),
            Vector2f(400f,400f),
            Color(0,255,0)
        ){
            it.rotation = 45f
        })
    }

}
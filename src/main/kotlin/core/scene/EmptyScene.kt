package core.scene

import org.joml.Vector2i

class EmptyScene(scene: () -> Unit) : AnvilScene(scene) {
    override fun size(): Vector2i {
        return Vector2i(0,0)
    }
}
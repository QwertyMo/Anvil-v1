import core.model.Color
import core.obj.AnvilObject
import core.render.AnvilFigure
import core.render.Rectangle
import org.joml.Vector2f

class RectangleObject(
    var point1: Vector2f,
    var point2: Vector2f,
    var color: Color,
    code: (obj: RectangleObject)->Unit
) : AnvilObject() {

    override fun render(): AnvilFigure {

        return Rectangle(
            Vector2f(point1.x, point1.y),
            Vector2f(point2.x, point2.y),
            color
        )
    }

    init {
        code(this)
    }

    override fun update() {
        rotation = 45f
    }
}
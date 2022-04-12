import core.model.Color
import core.model.Point
import core.obj.AnvilObject
import core.render.Draw
import org.joml.Vector2f
import kotlin.math.cos
import kotlin.math.sin

class LineObject : AnvilObject() {
    var x = 100.0f;
    var y = 100.0f

    init{

    }

    override fun render() {

    }

    override fun update() {

    }

    override fun pos(): Point {
        return Point(x,y)
    }
}
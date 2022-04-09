import core.model.Color
import core.model.Point
import core.obj.AnvilObject
import core.render.Draw
import kotlin.math.cos
import kotlin.math.sin

class LineObject : AnvilObject() {
    var x = 0.0;

    init{

    }

    override fun render() {
        Draw.line(
            Point(0.0f,0.0f),
            Point(sin(x).toFloat(), cos(x).toFloat()),
            Color(255,128,255)
        )
    }

    override fun update() {
        x+=0.1;
        if(x>360)x=0.0
    }
}
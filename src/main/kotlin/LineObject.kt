import core.model.Color
import core.obj.AnvilObject
import core.render.AnvilFigure
import core.render.Line
import org.joml.Vector2f

class LineObject : AnvilObject() {

    private var x = 0
    private var move = true

    override fun size(): Vector2f {
        return Vector2f(500f,500f)
    }

    override fun render(): AnvilFigure {
        return Line(
            Vector2f(0f,0f),
            Vector2f(x.toFloat(), size().y),
            Color(255,255,0)
        )
    }

    override fun update() {

        if(x>size().x || x<0) move=!move

        if(move) x+=1
        else x-=1

    }
}
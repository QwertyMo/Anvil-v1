package core.obj

import core.render.AnvilFigure
import org.joml.Vector2f

interface IAnvilObject {
    fun render(): AnvilFigure
    fun update()
}
package core.render

import core.model.Color
import org.joml.Vector2f
import org.lwjgl.opengl.GL11

class Draw {
    companion object{
        fun line(point1: Vector2f, point2: Vector2f, color: Color){
            val c = color.glColor()
            GL11.glColor3f(c.r, c.g, c.b)
            GL11.glBegin(GL11.GL_LINES)
            run{
                GL11.glVertex2f(point1.x-1, point1.y-1)
                GL11.glVertex2f(point2.x-1, point2.y-1)
            }
            GL11.glEnd()
        }

        fun rectangle(point1: Vector2f, point2: Vector2f, color: Color){
            val c = color.glColor()
            GL11.glColor3f(c.r, c.g, c.b)
            GL11.glBegin(GL11.GL_QUADS)
            run {
                GL11.glVertex2f(point1.x, point1.y)
                GL11.glVertex2f(point2.x, point1.y)
                GL11.glVertex2f(point2.x, point2.y)
                GL11.glVertex2f(point1.x, point2.y)
            }
            GL11.glEnd()
        }
    }
}
open class AnvilFigure

data class Line(val point1: Vector2f, val point2: Vector2f, val color: Color) : AnvilFigure()
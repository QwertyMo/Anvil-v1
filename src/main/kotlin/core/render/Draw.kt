package core.render

import core.model.Color
import org.joml.Vector2f
import org.lwjgl.opengl.GL11
import kotlin.math.cos
import kotlin.math.sin

class Draw {

    companion object{
        fun line(
            point1  : Vector2f,
            point2  : Vector2f,
            color   : Color,
            scale   : Float    = 1f,
            rotation: Float    = 0f,
            p0      : Vector2f = Vector2f((point1.x + point2.x)/2, (point1.y + point2.y)/2)
        ){
            val c = color.glColor()
            GL11.glColor3f(c.r, c.g, c.b)
            GL11.glBegin(GL11.GL_LINES)
            run{
                GL11.glVertex2f((rotateX(scale(point1, scale, p0), p0, rotation) * 2f - 1), (rotateY(scale(point1, scale, p0), p0, rotation) * 2f - 1))
                GL11.glVertex2f((rotateX(scale(point2, scale, p0), p0, rotation) * 2f - 1), (rotateY(scale(point2, scale, p0), p0, rotation) * 2f - 1))
            }
            GL11.glEnd()
        }

        fun rectangle(
            point1  : Vector2f,
            point2  : Vector2f,
            color   : Color,
            scale   : Float    = 1f,
            rotation: Float    = 0f,
            p0      : Vector2f = Vector2f((point1.x + point2.x)/2, (point1.y + point2.y)/2)
        ){
            val c = color.glColor()
            println(p0.x)
            GL11.glColor3f(c.r, c.g, c.b)
            GL11.glBegin(GL11.GL_QUADS)
            run {
                GL11.glVertex2f((rotateX(point1, p0,  -rotation)* 2f-1), (rotateY(point1, p0,  -rotation)* 2f-1))
                GL11.glVertex2f((rotateX(point2, p0, rotation)* 2f-1), (rotateY(point1, p0, rotation)* 2f-1))
                GL11.glVertex2f((rotateX(point2, p0,  -rotation)* 2f-1), (rotateY(point2, p0,  -rotation)* 2f-1))
                GL11.glVertex2f((rotateX(point1, p0, rotation)* 2f-1), (rotateY(point2, p0, rotation)* 2f-1))
                /*
                    GL11.glVertex2f((rotateX(point1, p0, rotation)-1), (rotateY(point1, p0, rotation)-1))
                    GL11.glVertex2f((rotateX(point1, p0, -rotation)-1), (rotateY(point2, p0, -rotation)-1))
                    GL11.glVertex2f((rotateX(point2, p0, rotation)-1), (rotateY(point2, p0, rotation)-1))
                    GL11.glVertex2f((rotateX(point2, p0, -rotation)-1), (rotateY(point1, p0, -rotation)-1))
                */
            }
            GL11.glEnd()
        }

        private fun rotateX(point:Vector2f, p0:Vector2f, rotation: Float):Float{
            return p0.x + (point.x - p0.x) * cos(rotation) - (point.y - p0.y) * sin(rotation)
        }

        private fun rotateY(point:Vector2f, p0:Vector2f, rotation: Float):Float{
            return p0.y + (point.x - p0.x) * sin(rotation) + (point.y - p0.y) * cos(rotation)
        }

        private fun scale(point: Vector2f, scale: Float, p0: Vector2f):Vector2f{
            return Vector2f(p0.x + (point.x - p0.x) * scale, p0.y + (point.y - p0.y) * scale)
        }
    }
}
open class AnvilFigure

data class Line(val point1: Vector2f, val point2: Vector2f, val color: Color) : AnvilFigure()
data class Rectangle(val point1: Vector2f, val point2: Vector2f, val color: Color) : AnvilFigure()
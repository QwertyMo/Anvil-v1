package core

import core.model.Color
import core.model.Point
import org.lwjgl.opengl.GL11

class Draw {
    companion object{
        fun line(point1: Point, point2: Point, color: Color){
            val c = color.glColor()
            GL11.glColor3f(c.r, c.g, c.b)
            GL11.glBegin(GL11.GL_LINES)
            GL11.glVertex2f(point1.x, point1.y);
            GL11.glVertex2f(point2.x, point2.y)
            GL11.glEnd()
        }
    }
}
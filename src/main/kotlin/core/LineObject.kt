package core

import core.model.Color
import core.model.Point
import kotlin.math.cos
import kotlin.math.sin

class LineObject : AnvilObject() {
    var x = 0.0;

    init{
        println("create")
    }

    override fun render() {
        println("render line")
        Draw.line(
            Point(0.0f,0.0f),
            Point(sin(x).toFloat(), cos(x).toFloat()),
            Color(255,128,255)
        )
    }

    override fun update() {
        println("update line")
        x+=0.1;
        if(x>360)x=0.0
    }
}
package core.obj

import org.joml.Vector2f

data class ObjectData(
    val name : String,
    var pos  : Vector2f = Vector2f(0f,0f)
)

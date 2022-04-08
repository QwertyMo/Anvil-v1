package core.model

data class GLColor(
    val r: Float,
    val g: Float,
    val b: Float,
    val a: Float = 1.0f
)

class Color(
    val r: Int,
    val g: Int,
    val b: Int,
    val a: Int = 255
){
    fun glColor():GLColor{
        return GLColor(
            this.r.toFloat()/255,
            this.g.toFloat()/255,
            this.b.toFloat()/255,
            this.a.toFloat()/255,
        )
    }
}
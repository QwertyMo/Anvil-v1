import core.Anvil
import kotlin.math.cos
import kotlin.math.sin

suspend fun main(args: Array<String>) {
    println(sin(3.14))
    Anvil().run {engine ->
        engine.runScene(TestScene{

        })
    }
}

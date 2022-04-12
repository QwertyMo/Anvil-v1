import core.Anvil

suspend fun main(args: Array<String>) {
    Anvil().run {engine ->
        engine.runScene(TestScene{

        })
    }
}

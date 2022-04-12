package core.obj

import core.model.Point

interface IAnvilObject {
    fun render()
    fun update()

    fun pos(): Point
}
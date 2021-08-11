package com.remodstudios.voidlands.util

import com.remodstudios.voidlands.mixin.SignTypeAccessor
import net.minecraft.util.SignType

object VoidlandsSignTypes {
    private fun register(name: String): SignType = SignTypeAccessor.invokeRegister(SignTypeAccessor.create(name))

    @JvmField
    val SHADEWOOD: SignType = register("shadewood")
    @JvmField
    val IGNEOUS: SignType = register("igneous")

    @JvmField
    val VALUES = setOf(SHADEWOOD, IGNEOUS)
}
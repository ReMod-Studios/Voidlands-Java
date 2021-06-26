package com.remodstudios.voidlands

import net.minecraft.util.Identifier

object Voidlands {
    const val MOD_ID = "voidlands"

    fun init() {
        printHelloWorld()
    }

    fun id(path: String): Identifier {
        return Identifier(MOD_ID, path)
    }
}
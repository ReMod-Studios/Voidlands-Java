package com.remodstudios.voidlands

import com.remodstudios.voidlands.block.VoidlandsBlocks
import com.remodstudios.voidlands.item.VoidlandsItems
import net.minecraft.util.Identifier

object Voidlands {
    const val MOD_ID = "voidlands"

    fun init() {
        VoidlandsBlocks.register()
        VoidlandsItems.register()
        VoidlandsBlocks.registerDispenserBehaviors()
        printHelloWorld()
    }

    fun id(path: String): Identifier {
        return Identifier(MOD_ID, path)
    }
}
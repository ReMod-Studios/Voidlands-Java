package com.remodstudios.voidlands

import com.remodstudios.voidlands.block.VoidlandsBlocks
import com.remodstudios.voidlands.item.VoidlandsItems
import com.remodstudios.voidlands.util.VoidlandsDyeColors
import net.minecraft.util.Identifier

object Voidlands {
    const val MOD_ID = "voidlands"

    fun init() {
        VoidlandsBlocks.register()
        VoidlandsItems.register()
        VoidlandsBlocks.registerDispenserBehaviors()
        printHelloWorld()
        println("CRAYOLA.id = ${VoidlandsDyeColors.CRAYOLA.id}")
    }

    @JvmStatic fun id(path: String) = Identifier(MOD_ID, path)
}
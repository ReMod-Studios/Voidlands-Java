package com.remodstudios.voidlands.fabric

import com.chocohead.mm.api.ClassTinkerers
import com.remodstudios.voidlands.util.VoidlandsDyeColors
import net.minecraft.util.DyeColor

@Suppress("unused")
object PlatformInterfaceImpl {
    @JvmStatic fun printHelloWorld() {
        println("Hello Fabric World!")
    }

    @JvmStatic fun getCrayolaDyeColor(): DyeColor =
        ClassTinkerers.getEnum(DyeColor::class.java, VoidlandsDyeColors.Values.CRAYOLA.fieldName)

    @JvmStatic fun getDarkRedDyeColor(): DyeColor =
        ClassTinkerers.getEnum(DyeColor::class.java, VoidlandsDyeColors.Values.DARK_RED.fieldName)
}
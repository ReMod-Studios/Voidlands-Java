package com.remodstudios.voidlands.fabric

import com.chocohead.mm.api.ClassTinkerers
import com.chocohead.mm.api.EnumAdder
import com.remodstudios.voidlands.util.VoidlandsDyeColors
import net.fabricmc.loader.api.FabricLoader
import net.minecraft.block.MapColor

@Suppress("unused")
object VoidlandsEarlyRiser : Runnable {
    override fun run() {
        val mr = FabricLoader.getInstance().mappingResolver
        val dyeColor = mr.mapClassName("intermediary", "net.minecraft.class_1767")
        val mapColor = mr.mapClassName("intermediary", "net.minecraft.class_3620")

        val adder =
            ClassTinkerers.enumBuilder(dyeColor, Int::class.java, String::class.java, Int::class.java, "L$mapColor;", Int::class.java, Int::class.java)
        addDyeColorEnum(adder, VoidlandsDyeColors.ColorValues.CRAYOLA)
        addDyeColorEnum(adder, VoidlandsDyeColors.ColorValues.DARK_RED)
        adder.build()
    }

    private fun addDyeColorEnum(adder: EnumAdder, values: VoidlandsDyeColors.ColorValues) {
        adder.addEnum(values.fieldName) { arrayOf(-1, values.name, values.color.value, MapColor.COLORS[values.mapColorId],
            values.signColor.value, values.fireworkColor.value) }
    }
}
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
        addDyeColorEnum(adder, VoidlandsDyeColors.Payloads.CRAYOLA)
        addDyeColorEnum(adder, VoidlandsDyeColors.Payloads.DARK_RED)
        adder.build()
    }

    // FIXME hardcoded IDs!! this will break with any other mod insane enough to add a DyeColor -ADCLeo
    private var id = 16 // id of last value of DyeColor (BLACK) + 1

    private fun addDyeColorEnum(adder: EnumAdder, payload: VoidlandsDyeColors.Payloads.DyeColorPayload) {
        adder.addEnum(payload.fieldName) { arrayOf(id, payload.name, payload.color.value, MapColor.COLORS[payload.mapColorId],
            payload.signColor.value, payload.fireworkColor.value) }
        id++
    }
}
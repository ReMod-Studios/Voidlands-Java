package com.remodstudios.voidlands.util

import com.remodstudios.voidlands.getCrayolaDyeColor
import com.remodstudios.voidlands.getDarkRedDyeColor
import io.github.remodstudios.remodcore.Color
import org.jetbrains.annotations.ApiStatus

object VoidlandsDyeColors {
    @ApiStatus.Internal
    object Payloads {
        @ApiStatus.Internal
        data class DyeColorPayload(
            val fieldName: String,
            val name: String, val color: Color, val mapColorId: Int,
            val fireworkColor: Color, val signColor: Color
        )

        val CRAYOLA =
            DyeColorPayload("CRAYOLA", "crayola",
                Color(r = 244, g = 194, b = 116),
                13,
                fireworkColor = Color(r = 255, g = 210, b = 128),
                signColor = Color(r = 255, g = 226, b = 180)
            )

        val DARK_RED =
            DyeColorPayload("DARK_RED", "dark_red",
                Color(r = 112, g = 12, b = 23),
                35,
                fireworkColor = Color(r = 140, g = 7, b = 40),
                signColor = Color(r = 156, g = 22, b = 49)
            )
    }

    // if you're wondering "why bother", shulker boxes require a dye color to render with the correct color
    val CRAYOLA by lazy(::getCrayolaDyeColor)
    val DARK_RED by lazy(::getDarkRedDyeColor)
}
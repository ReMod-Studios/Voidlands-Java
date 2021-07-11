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

        // TODO actual color values
        val CRAYOLA =
            DyeColorPayload("CRAYOLA", "crayola", Color(0), 0, Color(0), Color(0))
        val DARK_RED =
            DyeColorPayload("DARK_RED", "dark_red", Color(0), 0, Color(1), Color(0))
    }

    // if you're wondering "why bother", shulker boxes require a dye color to render with the correct color
    val CRAYOLA by lazy(::getCrayolaDyeColor)
    val DARK_RED by lazy(::getDarkRedDyeColor)
}
package com.remodstudios.voidlands.fabric.why;

import com.remodstudios.voidlands.util.VoidlandsDyeColors;
import net.minecraft.util.DyeColor;

@SuppressWarnings("unused")
public final class TransformerHooks {
    private TransformerHooks() { }

    public static boolean isCustomDyeColor(DyeColor color) {
        return color == VoidlandsDyeColors.CRAYOLA || color == VoidlandsDyeColors.DARK_RED;
    }
}

package com.remodstudios.voidlands.fabric;

import com.remodstudios.voidlands.util.VoidlandsDyeColors;
import net.minecraft.util.DyeColor;

public final class HatefulHooks {
    private HatefulHooks() { }

    public static boolean notMyDyeColor(DyeColor color) {
        return color != VoidlandsDyeColors.CRAYOLA && color != VoidlandsDyeColors.DARK_RED;
    }
}

package com.remodstudios.voidlands.mixin;

import net.minecraft.block.MapColor;
import net.minecraft.util.DyeColor;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(DyeColor.class)
public abstract class DyeColorMixin {
    @Shadow @Final @Mutable private int id;

    @Inject(method = "<init>", at = @At("RETURN"))
    public void setIdToOrdinal(String enumName, int ordinal, int j, String string2, int k, MapColor mapColor, int l, int m, CallbackInfo ci) {
        if (this.id < 0)
            this.id = ordinal;
    }
}

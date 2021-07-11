package com.remodstudios.voidlands.mixin;

import com.remodstudios.voidlands.block.VoidlandsBlocks;
import com.remodstudios.voidlands.util.VoidlandsDyeColors;
import net.minecraft.block.Block;
import net.minecraft.block.ShulkerBoxBlock;
import net.minecraft.util.DyeColor;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ShulkerBoxBlock.class)
public abstract class ShulkerBoxBlockMixin {
    @Inject(method = "get", at = @At("HEAD"), cancellable = true)
    private static void getCustomShulkerBoxes(DyeColor color, CallbackInfoReturnable<Block> cir) {
        // thank you mojank, very cool
        if (color == VoidlandsDyeColors.CRAYOLA)
            cir.setReturnValue(VoidlandsBlocks.CRAYOLA_SHULKER_BOX);
        else if (color == VoidlandsDyeColors.DARK_RED)
            cir.setReturnValue(VoidlandsBlocks.DARK_RED_SHULKER_BOX);
    }
}

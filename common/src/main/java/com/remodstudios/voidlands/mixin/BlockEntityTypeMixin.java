package com.remodstudios.voidlands.mixin;

import com.remodstudios.voidlands.block.VoidlandsBlocks;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.*;

import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(BlockEntityType.class)
public abstract class BlockEntityTypeMixin {
    @Shadow @Final public static BlockEntityType<ShulkerBoxBlockEntity> SHULKER_BOX;
    @Shadow @Final public static BlockEntityType<BedBlockEntity> BED;
    @Shadow @Final public static BlockEntityType<BannerBlockEntity> BANNER;
    @Shadow @Final public static BlockEntityType<SignBlockEntity> SIGN;

    @SuppressWarnings("ConstantConditions")
    @Inject(method = "supports", at = @At("RETURN"), cancellable = true)
    public void supportCustomBlocks(BlockState state, CallbackInfoReturnable<Boolean> cir) {
        final BlockEntityType<?> type = ((BlockEntityType<?>) (Object) this);
        if (type == SHULKER_BOX) {
            if (state.isOf(VoidlandsBlocks.CRAYOLA_SHULKER_BOX) || state.isOf(VoidlandsBlocks.DARK_RED_SHULKER_BOX))
                cir.setReturnValue(true);
        } else if (type == BED) {
            if (state.isOf(VoidlandsBlocks.CRAYOLA_BED) || state.isOf(VoidlandsBlocks.DARK_RED_BED))
                cir.setReturnValue(true);
        } else if (type == BANNER) {
            if (state.isOf(VoidlandsBlocks.CRAYOLA_BANNER) || state.isOf(VoidlandsBlocks.CRAYOLA_WALL_BANNER)
             || state.isOf(VoidlandsBlocks.DARK_RED_BANNER) || state.isOf(VoidlandsBlocks.DARK_RED_WALL_BANNER))
                cir.setReturnValue(true);
        } else if (type == SIGN) {
            if (state.isOf(VoidlandsBlocks.SHADEWOOD_SIGN) || state.isOf(VoidlandsBlocks.SHADEWOOD_WALL_SIGN)
             || state.isOf(VoidlandsBlocks.IGNEOUS_SIGN) || state.isOf(VoidlandsBlocks.IGNEOUS_WALL_SIGN))
                cir.setReturnValue(true);
        }
    }
}

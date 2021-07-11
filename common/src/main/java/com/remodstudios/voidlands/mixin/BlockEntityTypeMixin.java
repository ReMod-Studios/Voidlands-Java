package com.remodstudios.voidlands.mixin;

import com.remodstudios.voidlands.block.VoidlandsBlocks;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BedBlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.block.entity.ShulkerBoxBlockEntity;
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

    @SuppressWarnings("ConstantConditions")
    @Inject(method = "supports", at = @At("RETURN"), cancellable = true)
    public void supportCustomBlocks(BlockState state, CallbackInfoReturnable<Boolean> cir) {
        if (((Object) this) == SHULKER_BOX) {
            if (state.isOf(VoidlandsBlocks.CRAYOLA_SHULKER_BOX) || state.isOf(VoidlandsBlocks.DARK_RED_SHULKER_BOX))
                cir.setReturnValue(true);
        } else if (((Object) this) == BED) {
            if (state.isOf(VoidlandsBlocks.CRAYOLA_BED) || state.isOf(VoidlandsBlocks.DARK_RED_BED))
                cir.setReturnValue(true);
        }
    }
}

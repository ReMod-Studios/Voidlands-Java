package com.remodstudios.voidlands.mixin;

import com.remodstudios.voidlands.block.VoidlandsBlocks;
import com.remodstudios.voidlands.util.ArrayCombiner;
import net.minecraft.block.Block;
import net.minecraft.block.entity.BlockEntityType;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(BlockEntityType.class)
public abstract class BlockEntityTypeMixin {
    @SuppressWarnings("UnresolvedMixinReference") // shut up, <clinit> works
    @Redirect(method = "<clinit>",
            at = @At(value = "INVOKE",
                    target = "Lnet/minecraft/block/entity/BlockEntityType$Builder;create(Lnet/minecraft/block/entity/BlockEntityType$BlockEntityFactory;[Lnet/minecraft/block/Block;)Lnet/minecraft/block/entity/BlockEntityType$Builder;",
                    ordinal = 22))
    private static BlockEntityType.Builder<?> addShulkerBoxes(BlockEntityType.BlockEntityFactory<?> blockEntityFactory, Block[] blocks) {
        blocks = ArrayCombiner.combine(blocks, new Block[] {
                VoidlandsBlocks.DARK_RED_SHULKER_BOX, VoidlandsBlocks.CRAYOLA_SHULKER_BOX
        }, Block[]::new);
        return BlockEntityType.Builder.create(blockEntityFactory, blocks);
    }
}

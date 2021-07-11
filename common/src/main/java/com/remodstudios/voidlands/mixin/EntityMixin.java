package com.remodstudios.voidlands.mixin;

import com.remodstudios.voidlands.block.VoidlandsBlocks;
import net.minecraft.block.BlockState;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.Vec3d;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Entity.class)
public abstract class EntityMixin {
    @Unique private boolean isInDustCloud = false;

    @Inject(method = "slowMovement", at = @At("RETURN"))
    public void setIsInDustCloudFlag(BlockState blockState, Vec3d vec3d, CallbackInfo ci) {
        isInDustCloud = blockState.isOf(VoidlandsBlocks.DUST_CLOUD);
    }

    @Redirect(method = "move", at = @At(value = "INVOKE",
            target = "Lnet/minecraft/entity/Entity;setVelocity(Lnet/minecraft/util/math/Vec3d;)V", ordinal = 0))
    public void keepHorizontalVelocityIfInDustCloud(Entity self, Vec3d zero) {
        if (isInDustCloud) {
            isInDustCloud = false;
            Vec3d vel = self.getVelocity();
            self.setVelocity(new Vec3d(vel.x, 0, vel.z));
        } else
            self.setVelocity(zero);
    }
}

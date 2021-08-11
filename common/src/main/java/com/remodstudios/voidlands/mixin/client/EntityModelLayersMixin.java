package com.remodstudios.voidlands.mixin.client;

import com.remodstudios.voidlands.Voidlands;
import com.remodstudios.voidlands.util.VoidlandsSignTypes;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import net.minecraft.client.render.entity.model.EntityModelLayer;
import net.minecraft.client.render.entity.model.EntityModelLayers;
import net.minecraft.util.Identifier;
import net.minecraft.util.SignType;

@Mixin(EntityModelLayers.class)
public abstract class EntityModelLayersMixin {
    @Inject(method = "createSign", at = @At("RETURN"), cancellable = true)
    private static void fixCustomSignId(SignType signType, CallbackInfoReturnable<EntityModelLayer> cir) {
        if (VoidlandsSignTypes.VALUES.contains(signType))
            cir.setReturnValue(new EntityModelLayer(new Identifier(Voidlands.MOD_ID, "sign/" + signType.getName()), "main"));
    }
}

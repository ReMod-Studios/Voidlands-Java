package com.remodstudios.voidlands.mixin.client;

import com.google.common.collect.ImmutableList;
import com.remodstudios.voidlands.Voidlands;
import com.remodstudios.voidlands.util.VoidlandsDyeColors;
import net.minecraft.client.render.TexturedRenderLayers;
import net.minecraft.client.util.SpriteIdentifier;
import net.minecraft.util.DyeColor;
import net.minecraft.util.Identifier;
import net.minecraft.util.SignType;

import com.remodstudios.voidlands.util.VoidlandsSignTypes;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.List;

@Mixin(TexturedRenderLayers.class)
public abstract class TexturedRenderLayersMixin {
    @Shadow @Final public static Identifier SHULKER_BOXES_ATLAS_TEXTURE;
    @Shadow @Final public static Identifier BEDS_ATLAS_TEXTURE;
    @Shadow @Final public static Identifier SIGNS_ATLAS_TEXTURE;

    @Shadow @Final @Mutable public static List<SpriteIdentifier> COLORED_SHULKER_BOXES_TEXTURES;
    @Shadow @Final @Mutable public static SpriteIdentifier[] BED_TEXTURES;

    static {
        ImmutableList.Builder<SpriteIdentifier> shulkerBoxTexturesBuilder = ImmutableList.builder();
        shulkerBoxTexturesBuilder.addAll(COLORED_SHULKER_BOXES_TEXTURES);
        for (DyeColor value : VoidlandsDyeColors.VALUES) {
            shulkerBoxTexturesBuilder.add(
                    new SpriteIdentifier(SHULKER_BOXES_ATLAS_TEXTURE,
                            new Identifier(Voidlands.MOD_ID, "entity/shulker/shulker_" + value.getName())));
            BED_TEXTURES[value.getId()] = new SpriteIdentifier(BEDS_ATLAS_TEXTURE,
                    new Identifier(Voidlands.MOD_ID, "entity/bed/" + value.getName()));
        }
        COLORED_SHULKER_BOXES_TEXTURES = shulkerBoxTexturesBuilder.build();
    }

    @Inject(method = "createSignTextureId", at = @At("RETURN"), cancellable = true)
    private static void fixCustomSignTextureId(SignType signType, CallbackInfoReturnable<SpriteIdentifier> cir) {
        if (VoidlandsSignTypes.VALUES.contains(signType))
            cir.setReturnValue(new SpriteIdentifier(SIGNS_ATLAS_TEXTURE,
                    new Identifier(Voidlands.MOD_ID, "entity/signs/" + signType.getName())));
    }
}

package com.remodstudios.voidlands.fabric.why.mixin;

import com.google.common.collect.ImmutableList;
import com.remodstudios.voidlands.Voidlands;
import com.remodstudios.voidlands.fabric.why.DispenserBehaviorTransformer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.objectweb.asm.tree.ClassNode;
import org.spongepowered.asm.mixin.extensibility.IMixinConfigPlugin;
import org.spongepowered.asm.mixin.extensibility.IMixinInfo;

import java.util.List;
import java.util.Set;

/**
 * Stolen from LambdaAurora's Tesla Coil mod, originally written by Gegy.<p>
 *
 * Find the original <a href="https://github.com/LambdAurora/tesla-coil-fabric/blob/1.17/src/main/java/dev/lambdaurora/tesla_coil/help_me/TransformerMixinPlugin.java">here</a>.
 */
public final class TransformerMixinPlugin implements IMixinConfigPlugin {
    public static final Logger LOGGER = LogManager.getLogger(Voidlands.MOD_ID + ":TransformerMixinPlugin");

    @Override
    public void postApply(String targetClassName, ClassNode targetClass, String mixinClassName, IMixinInfo mixinInfo) {
        if ("DispenserBehaviorMarker".equals(mixinInfo.getName())) {
            if (!DispenserBehaviorTransformer.transform(targetClass)) {
                LOGGER.error("Failed to transform DispenserBehavior class!");
                System.exit(1);
            }
        }
    }

    // region stuff I don't implement lmao
    @Override
    public void onLoad(String mixinPackage) { }

    @Override
    public String getRefMapperConfig() { return null; }

    @Override
    public boolean shouldApplyMixin(String targetClassName, String mixinClassName) { return true; }

    @Override
    public void acceptTargets(Set<String> myTargets, Set<String> otherTargets) { }

    @Override
    public List<String> getMixins() { return ImmutableList.of(); }

    @Override
    public void preApply(String targetClassName, ClassNode targetClass, String mixinClassName, IMixinInfo mixinInfo) { }
    // endregion
}

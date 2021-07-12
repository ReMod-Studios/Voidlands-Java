package com.remodstudios.voidlands.fabric.why;

import com.google.common.collect.ImmutableList;
import com.remodstudios.voidlands.Voidlands;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.objectweb.asm.tree.ClassNode;
import org.spongepowered.asm.mixin.MixinEnvironment;
import org.spongepowered.asm.mixin.extensibility.IMixinConfigPlugin;
import org.spongepowered.asm.mixin.extensibility.IMixinInfo;
import org.spongepowered.asm.mixin.transformer.FabricMixinTransformerProxy;

import java.util.List;
import java.util.Set;

/**
 * Stolen from Lambda's Tesla Coil mod, originally written by Gegy.<p>
 *
 * Find the original <a href="https://github.com/LambdAurora/tesla-coil-fabric/blob/1.17/src/main/java/dev/lambdaurora/tesla_coil/help_me/TransformerMixinPlugin.java">here</a>.
 *
 * @author Gegy
 */
public class BadMixinPlugin implements IMixinConfigPlugin {
    public static final Logger LOGGER = LogManager.getLogger(Voidlands.MOD_ID + ":BadMixinPlugin");

    @Override
    public void onLoad(String mixinPackage) {
        var transformer = DispenserBehaviorClassVisitor.createTransformer();

        try {
            this.applyClassTransformer(transformer);
        } catch (ReflectiveOperationException e) {
            LOGGER.error("Failed to apply class transformer!", e);
        }
    }

    private void applyClassTransformer(ClassTransformer transformer) throws ReflectiveOperationException {
        var loader = this.getClass().getClassLoader();

        var knotClassLoader = Class.forName("net.fabricmc.loader.launch.knot.KnotClassLoader");
        var knotClassDelegate = Class.forName("net.fabricmc.loader.launch.knot.KnotClassDelegate");
        var mixinEnvironment = MixinEnvironment.class;

        var delegateField = knotClassLoader.getDeclaredField("delegate");
        delegateField.setAccessible(true);

        var mixinTransformerField = knotClassDelegate.getDeclaredField("mixinTransformer");
        mixinTransformerField.setAccessible(true);

        var activeEnvTransformerField = mixinEnvironment.getDeclaredField("transformer");
        activeEnvTransformerField.setAccessible(true);

        var delegate = delegateField.get(loader);
        var mixinTransformer = mixinTransformerField.get(delegate);

        if (mixinTransformer == null) {
            throw new IllegalStateException("mixin transformer not yet initialized!");
        }

        var lastTransformer = activeEnvTransformerField.get(null);

        try {
            activeEnvTransformerField.set(null, null);
            mixinTransformerField.set(delegate, new HookedProxy((FabricMixinTransformerProxy) mixinTransformer, transformer));
        } finally {
            activeEnvTransformerField.set(null, lastTransformer);
        }
    }

    private static class HookedProxy extends FabricMixinTransformerProxy {
        private final FabricMixinTransformerProxy parent;
        private final ClassTransformer transformer;

        HookedProxy(FabricMixinTransformerProxy parent, ClassTransformer transformer) {
            this.parent = parent;
            this.transformer = transformer;
        }

        @Override
        public byte[] transformClassBytes(String name, String transformedName, byte[] basicClass) {
            var bytes = this.parent.transformClassBytes(name, transformedName, basicClass);
            return this.transformer.transform(name, transformedName, bytes);
        }
    }

    // region stuff I don't implement lmao
    @Override
    public String getRefMapperConfig() { return null; }

    @Override
    public boolean shouldApplyMixin(String targetClassName, String mixinClassName) { return false; }

    @Override
    public void acceptTargets(Set<String> myTargets, Set<String> otherTargets) { }

    @Override
    public List<String> getMixins() { return ImmutableList.of(); }

    @Override
    public void preApply(String targetClassName, ClassNode targetClass, String mixinClassName, IMixinInfo mixinInfo) { }

    @Override
    public void postApply(String targetClassName, ClassNode targetClass, String mixinClassName, IMixinInfo mixinInfo) { }
    // endregion
}

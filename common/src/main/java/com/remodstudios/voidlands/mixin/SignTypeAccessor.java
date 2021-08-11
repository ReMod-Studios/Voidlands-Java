package com.remodstudios.voidlands.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;
import org.spongepowered.asm.mixin.gen.Invoker;

import net.minecraft.util.SignType;
import java.util.Set;

@Mixin(SignType.class)
public interface SignTypeAccessor {
    @Invoker("<init>")
    static SignType create(String name) {
        throw new AssertionError();
    }

    @Invoker
    static SignType invokeRegister(SignType signType) {
        throw new AssertionError();
    }
}

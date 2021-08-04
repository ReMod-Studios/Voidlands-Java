package com.remodstudios.voidlands.block

import net.minecraft.entity.Entity
import net.minecraft.util.math.Box
import net.minecraft.world.World

// FIXME these are supposed to be part of ReMod Core, but apparently we can't use them?! -ADCLeo
typealias Predicate<T> = (T) -> Boolean
inline fun <reified T: Entity> World.getEntities(box: Box, noinline predicate: Predicate<T>? = null): List<T>
        = getEntitiesByClass(T::class.java, box, predicate)

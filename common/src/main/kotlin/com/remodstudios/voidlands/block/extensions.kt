package com.remodstudios.voidlands.block

import net.minecraft.entity.Entity
import net.minecraft.util.math.Box
import net.minecraft.util.math.Direction
import net.minecraft.util.shape.VoxelShape
import net.minecraft.util.shape.VoxelShapes
import net.minecraft.world.World

// FIXME these are supposed to be part of ReMod Core, but apparently we can't use them?! -ADCLeo
typealias Predicate<T> = (T) -> Boolean
inline fun <reified T: Entity> World.getEntities(box: Box, noinline predicate: Predicate<T>? = null): List<T>
        = getEntitiesByClass(T::class.java, box, predicate)

fun VoxelShape.rotate(from: Direction, to: Direction): VoxelShape {
    if (from == to)
        return this
    val buffer = arrayOf(this, VoxelShapes.empty())
    val times = (to.horizontal - from.horizontal + 4) % 4
    for (i in 0..times) {
        buffer[0].forEachBox { minX, minY, minZ, maxX, maxY, maxZ ->
            buffer[1] = VoxelShapes.union(buffer[1], VoxelShapes.cuboid(1 - maxZ, minY, minX, 1 - minZ, maxY, maxX))
        }
        buffer[0] = buffer[1]
        buffer[1] = VoxelShapes.empty()
    }
    return buffer[0]
}

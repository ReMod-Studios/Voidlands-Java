package com.remodstudios.voidlands.block.util

import net.minecraft.block.Block
import net.minecraft.util.math.Direction
import net.minecraft.util.shape.VoxelShape
import kotlin.math.abs

// original by Azagwen, I converted what I needed to Kotlin -ADCLeo
// original: https://github.com/Azagwen/ATBYW/blob/f21aa502bfe90fe9f7870743247765fc72581dc0/src/main/java/net/azagwen/atbyw/block/shape/AxisShape.java
data class AxisShape(val minRadius: Double, val minDepth: Double, val maxRadius: Double, val maxDepth: Double) {
    private val minDepthMirror = abs(maxDepth - 16)
    private val maxDepthMirror = abs(minDepth - 16)

    // NOTE: these are _inverted_ from the source -ADCLeo
    private val eastShape = Block.createCuboidShape(minDepth, minRadius, minRadius, maxDepth, maxRadius, maxRadius)
    private val westShape = Block.createCuboidShape(minDepthMirror, minRadius, minRadius, maxDepthMirror, maxRadius, maxRadius)
    private val upShape = Block.createCuboidShape(minRadius, minDepth, minRadius, maxRadius, maxDepth, maxRadius)
    private val downShape = Block.createCuboidShape(minRadius, minDepthMirror, minRadius, maxRadius, maxDepthMirror, maxRadius)
    private val northShape = Block.createCuboidShape(minRadius, minRadius, minDepthMirror, maxRadius, maxRadius, maxDepthMirror)
    private val southShape = Block.createCuboidShape(minRadius, minRadius, minDepth, maxRadius, maxRadius, maxDepth)

    fun xShape(isWest: Boolean): VoxelShape = if (isWest) westShape else eastShape

    fun yShape(isDown: Boolean): VoxelShape = if (isDown) downShape else upShape

    fun zShape(isSouth: Boolean): VoxelShape = if (isSouth) southShape else northShape

    operator fun get(dir: Direction): VoxelShape = when (dir) {
        Direction.UP -> this.yShape(false)
        Direction.DOWN -> this.yShape(true)
        Direction.NORTH -> this.zShape(false)
        Direction.SOUTH -> this.zShape(true)
        Direction.EAST -> this.xShape(false)
        Direction.WEST -> this.xShape(true)
    }

    val map: Map<Direction, VoxelShape> by lazy { Direction.values().associateWith(::get) }
}

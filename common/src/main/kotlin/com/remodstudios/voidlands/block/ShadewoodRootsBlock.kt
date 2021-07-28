package com.remodstudios.voidlands.block

import net.minecraft.block.BlockState
import net.minecraft.block.HangingRootsBlock
import net.minecraft.block.ShapeContext
import net.minecraft.util.math.BlockPos
import net.minecraft.util.shape.VoxelShape
import net.minecraft.world.BlockView

class ShadewoodRootsBlock(settings: Settings) : HangingRootsBlock(settings) {
    companion object {
        private val SHAPE = createCuboidShape(1.0, 8.0, 1.0, 13.0, 16.0, 13.0)
    }

    override fun getOutlineShape(state: BlockState, world: BlockView, pos: BlockPos, ctx: ShapeContext): VoxelShape = SHAPE
}
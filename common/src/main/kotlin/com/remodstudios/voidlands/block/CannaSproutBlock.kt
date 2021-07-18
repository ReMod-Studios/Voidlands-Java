package com.remodstudios.voidlands.block

import net.minecraft.block.BlockState
import net.minecraft.block.PlantBlock
import net.minecraft.block.ShapeContext
import net.minecraft.util.math.BlockPos
import net.minecraft.util.shape.VoxelShape
import net.minecraft.world.BlockView

class CannaSproutBlock(settings: Settings?) : PlantBlock(settings) {
    companion object {
        private val SHAPE = createCuboidShape(2.0, 0.0, 2.0, 14.0, 13.0, 14.0)
    }

    override fun getOutlineShape(
        state: BlockState,
        world: BlockView,
        pos: BlockPos,
        ctx: ShapeContext
    ): VoxelShape {
        return SHAPE
    }
}
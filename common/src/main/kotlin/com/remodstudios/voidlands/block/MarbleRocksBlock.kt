package com.remodstudios.voidlands.block

import net.minecraft.block.Block
import net.minecraft.block.BlockState
import net.minecraft.block.HorizontalFacingBlock
import net.minecraft.block.ShapeContext
import net.minecraft.item.ItemPlacementContext
import net.minecraft.state.StateManager
import net.minecraft.util.math.BlockPos
import net.minecraft.util.math.Direction
import net.minecraft.util.shape.VoxelShape
import net.minecraft.world.BlockView

class MarbleRocksBlock(settings: Settings?) : HorizontalFacingBlock(settings) {
    companion object {
        private val SHAPE = Block.createCuboidShape(0.0, 0.0, 0.0, 16.0, 3.0, 16.0)
    }

    init {
        defaultState = defaultState.with(FACING, Direction.NORTH)
    }

    override fun getPlacementState(itemPlacementContext: ItemPlacementContext): BlockState {
        return defaultState.with(FACING, itemPlacementContext.playerFacing.opposite) as BlockState
    }

    override fun appendProperties(builder: StateManager.Builder<Block?, BlockState?>) {
        builder.add(FACING)
    }

    override fun getOutlineShape(
        blockState: BlockState?,
        blockView: BlockView?,
        blockPos: BlockPos?,
        shapeContext: ShapeContext?
    ): VoxelShape {
        return SHAPE
    }
}
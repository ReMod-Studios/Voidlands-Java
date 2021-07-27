package com.remodstudios.voidlands.block

import net.minecraft.block.*
import net.minecraft.item.ItemPlacementContext
import net.minecraft.state.StateManager
import net.minecraft.util.math.BlockPos
import net.minecraft.util.math.Direction
import net.minecraft.util.shape.VoxelShape
import net.minecraft.world.BlockView
import net.minecraft.world.WorldView

class MarbleRocksBlock(settings: Settings) : HorizontalFacingBlock(settings) {
    companion object {
        private val SHAPE = Block.createCuboidShape(0.0, 0.0, 0.0, 16.0, 3.0, 16.0)
    }

    init {
        defaultState = defaultState.with(FACING, Direction.NORTH)
    }

    override fun canPlaceAt(state: BlockState?, world: WorldView?, pos: BlockPos?): Boolean {
        return world!!.getBlockState(pos!!.down()).material.isSolid
    }

    override fun getPlacementState(ctx: ItemPlacementContext): BlockState {
        return defaultState.with(FACING, ctx.playerFacing.opposite)
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
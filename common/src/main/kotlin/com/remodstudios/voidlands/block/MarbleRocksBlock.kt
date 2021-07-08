package com.remodstudios.voidlands.block

import net.minecraft.block.Block
import net.minecraft.block.BlockState
import net.minecraft.block.HorizontalFacingBlock
import net.minecraft.item.ItemPlacementContext
import net.minecraft.state.StateManager
import net.minecraft.util.math.Direction

class MarbleRocksBlock(settings: Settings?) : HorizontalFacingBlock(settings) {
    init {
        defaultState = defaultState.with(FACING, Direction.NORTH)
    }

    override fun getPlacementState(itemPlacementContext: ItemPlacementContext): BlockState {
        return defaultState.with(FACING, itemPlacementContext.playerFacing.opposite) as BlockState
    }

    override fun appendProperties(builder: StateManager.Builder<Block?, BlockState?>) {
        builder.add(FACING)
    }
}
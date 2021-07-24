package com.remodstudios.voidlands.block

import net.minecraft.block.*
import net.minecraft.block.enums.DoubleBlockHalf
import net.minecraft.server.world.ServerWorld
import net.minecraft.state.StateManager
import net.minecraft.state.property.IntProperty
import net.minecraft.state.property.Properties
import net.minecraft.util.math.BlockPos
import net.minecraft.util.shape.VoxelShape
import net.minecraft.world.BlockView
import net.minecraft.world.World
import java.util.*

class CannaSproutBlock(settings: Settings?) : PlantBlock(settings), Fertilizable {
    companion object {
        private val SHAPE = createCuboidShape(2.0, 0.0, 2.0, 14.0, 13.0, 14.0)

        @JvmField val AGE: IntProperty = Properties.AGE_2

        @JvmStatic private fun doGrowth(world: ServerWorld, pos: BlockPos, state: BlockState) {
            val age = state[AGE]
            if (age + 1 < 2)
                world.setBlockState(pos, state.with(AGE, age + 1), 3)
            else
                growIntoCanna(world, pos)
        }

        @JvmStatic private fun canGrowIntoCanna(world: World, pos: BlockPos): Boolean {
            return world.getBlockState(pos.up()).material.isReplaceable
        }

        @JvmStatic private fun growIntoCanna(world: ServerWorld, pos: BlockPos) {
            if (!canGrowIntoCanna(world, pos))
                return
            val pos2 = pos.up()
            world.setBlockState(pos, VoidlandsBlocks.CANNA.defaultState.with(CannaPlantBlock.HALF, DoubleBlockHalf.LOWER), 3)
            world.setBlockState(pos2, VoidlandsBlocks.CANNA.defaultState.with(CannaPlantBlock.HALF, DoubleBlockHalf.UPPER), 3)
        }
    }

    init {
        defaultState = defaultState.with(CannaPlantBlock.AGE, 0)
    }

    override fun appendProperties(builder: StateManager.Builder<Block, BlockState>) {
        super.appendProperties(builder)
        builder.add(CannaPlantBlock.AGE)
    }

    override fun getOutlineShape(
        state: BlockState,
        world: BlockView,
        pos: BlockPos,
        ctx: ShapeContext
    ): VoxelShape {
        return SHAPE
    }

    override fun hasRandomTicks(state: BlockState): Boolean = state[VoidBerryRootsBlock.AGE] < 2

    override fun randomTick(state: BlockState, world: ServerWorld, pos: BlockPos, random: Random) {
        if (random.nextInt(26) != 0)
            return
        doGrowth(world, pos, state)
    }

    override fun isFertilizable(
        world: BlockView,
        pos: BlockPos,
        state: BlockState,
        bl: Boolean
    ): Boolean = state[AGE] < 2

    override fun canGrow(world: World, random: Random, pos: BlockPos, state: BlockState): Boolean =
        canGrowIntoCanna(world, pos)

    override fun grow(world: ServerWorld, random: Random, pos: BlockPos, state: BlockState) =
        doGrowth(world, pos, state)
}
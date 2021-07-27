package com.remodstudios.voidlands.block

import com.remodstudios.voidlands.item.VoidlandsItems
import net.minecraft.block.*
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.item.ItemPlacementContext
import net.minecraft.item.ItemStack
import net.minecraft.server.world.ServerWorld
import net.minecraft.state.StateManager
import net.minecraft.state.property.DirectionProperty
import net.minecraft.state.property.IntProperty
import net.minecraft.state.property.Properties
import net.minecraft.util.ActionResult
import net.minecraft.util.Hand
import net.minecraft.util.hit.BlockHitResult
import net.minecraft.util.math.BlockPos
import net.minecraft.util.math.Direction
import net.minecraft.util.shape.VoxelShape
import net.minecraft.util.shape.VoxelShapes
import net.minecraft.world.BlockView
import net.minecraft.world.World
import net.minecraft.world.WorldView
import java.util.*

class VoidBerryRootsBlock(settings: Settings) : FacingBlock(settings), Fertilizable {
    companion object {
        private val SHAPE = createCuboidShape(0.0, 0.0, 0.0, 16.0, 1.0, 16.0)
        private val SHAPE_WITH_BERRY = VoxelShapes.union(
            createCuboidShape(0.0, 0.0, 0.0, 16.0, 1.0, 16.0),
            createCuboidShape(6.0, 1.0, 6.0, 10.0, 5.0, 10.0)
        )

        private val SHAPES = Direction.values().associateWith { SHAPE.rotate(Direction.UP, it) }
        private val SHAPES_WITH_BERRY = Direction.values().associateWith { SHAPE_WITH_BERRY.rotate(Direction.UP, it) }

        @JvmField val AGE: IntProperty = Properties.AGE_2
        @JvmField val FACING: DirectionProperty = FacingBlock.FACING

        @JvmStatic private fun doGrowth(world: ServerWorld, pos: BlockPos, state: BlockState) {
            var age = state[AGE] + 1
            if (age > 2)
                age = 2
            world.setBlockState(pos, state.with(AGE, age), 3)
        }
    }

    init {
        defaultState = defaultState.with(AGE, 0).with(FACING, Direction.UP)
    }

    override fun appendProperties(builder: StateManager.Builder<Block, BlockState>) {
        builder.add(AGE, FACING)
    }

    override fun getPlacementState(context: ItemPlacementContext): BlockState {
        return defaultState.with(FACING, context.side.opposite)
    }

    override fun canPlaceAt(state: BlockState, world: WorldView, pos: BlockPos): Boolean {
        val direction = state.get(FACING)
        val pos2 = pos.offset(direction.opposite)
        val state2 = world.getBlockState(pos2)
        return state2.isSideSolidFullSquare(world, pos2, direction)
    }

    override fun onUse(
        state: BlockState,
        world: World,
        pos: BlockPos,
        player: PlayerEntity,
        hand: Hand,
        hitResult: BlockHitResult
    ): ActionResult {
        if (state.get(AGE) < 2)
            return ActionResult.PASS
        player.inventory.offerOrDrop(ItemStack(VoidlandsItems.VOID_BERRY))
        world.setBlockState(pos, state.with(AGE, 0), 3)
        return ActionResult.SUCCESS
    }

    override fun hasRandomTicks(state: BlockState): Boolean = state[AGE] < 2

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

    override fun canGrow(world: World, random: Random, pos: BlockPos, state: BlockState): Boolean = true

    override fun grow(world: ServerWorld, random: Random, pos: BlockPos, state: BlockState) =
        doGrowth(world, pos, state)

    // FIXME rotate shape to match facing -ADCLeo
    override fun getOutlineShape(
        state: BlockState,
        world: BlockView,
        pos: BlockPos,
        shapeCtx: ShapeContext
    ): VoxelShape = (if (state[AGE] >= 2) SHAPES_WITH_BERRY[state[FACING]] else SHAPES[state[FACING]])
        ?: throw AssertionError("Unhandled facing ${state[FACING]} in state $state!")
}
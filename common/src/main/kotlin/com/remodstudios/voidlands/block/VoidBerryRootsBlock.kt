package com.remodstudios.voidlands.block

import com.remodstudios.voidlands.block.util.AxisShape
import com.remodstudios.voidlands.item.VoidlandsItems
import net.minecraft.block.*
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.item.ItemPlacementContext
import net.minecraft.item.ItemStack
import net.minecraft.server.world.ServerWorld
import net.minecraft.sound.SoundCategory
import net.minecraft.sound.SoundEvents
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
        private fun VoxelShape.offsetPixels(x: Int, y: Int, z: Int) =
            offset(x.toDouble() / 16, y.toDouble() / 16, z.toDouble() / 16)
        private fun VoxelShape.offsetPixels(dir: Direction, multiplier: Int = 1) =
            offsetPixels(dir.offsetX * multiplier, dir.offsetY * multiplier, dir.offsetZ * multiplier)

        private val SHAPES = AxisShape(0.0, 0.0, 16.0, 1.0).map
        private val SHAPES_WITH_BERRY: Map<Direction, VoxelShape>

        init {
            val asBerry = AxisShape(6.0, 6.0, 10.0, 10.0)
            val shapesWithBerry = HashMap<Direction, VoxelShape>()
            for ((dir, base) in SHAPES) {
                val berry = asBerry[dir].offsetPixels(dir, -5)
                shapesWithBerry[dir] = VoxelShapes.union(base, berry)
            }
            SHAPES_WITH_BERRY = shapesWithBerry.toMap()
        }

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

    override fun getPlacementState(context: ItemPlacementContext): BlockState =
        defaultState.with(FACING, context.side)

    override fun canPlaceAt(state: BlockState, world: WorldView, pos: BlockPos): Boolean {
        val direction = state.get(FACING).opposite
        val pos2 = pos.offset(direction)
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
        world.playSound(player.x, player.y, player.z, SoundEvents.ENTITY_ITEM_PICKUP, SoundCategory.PLAYERS,
            0.2F, (player.random.nextFloat() - player.random.nextFloat()) * 1.4F + 2.0F, false)
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

    override fun getOutlineShape(
        state: BlockState,
        world: BlockView,
        pos: BlockPos,
        shapeCtx: ShapeContext
    ): VoxelShape = (if (state[AGE] >= 2) SHAPES_WITH_BERRY[state[FACING]] else SHAPES[state[FACING]])
        ?: throw AssertionError("Unhandled facing ${state[FACING]} in state $state!")
}
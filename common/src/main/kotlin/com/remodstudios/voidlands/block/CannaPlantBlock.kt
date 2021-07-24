package com.remodstudios.voidlands.block

import com.remodstudios.voidlands.item.VoidlandsItems
import net.minecraft.block.Block
import net.minecraft.block.BlockState
import net.minecraft.block.Fertilizable
import net.minecraft.block.TallPlantBlock
import net.minecraft.block.enums.DoubleBlockHalf
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.item.ItemStack
import net.minecraft.item.Items
import net.minecraft.server.world.ServerWorld
import net.minecraft.sound.SoundCategory
import net.minecraft.sound.SoundEvents
import net.minecraft.state.StateManager
import net.minecraft.state.property.EnumProperty
import net.minecraft.state.property.IntProperty
import net.minecraft.state.property.Properties
import net.minecraft.util.ActionResult
import net.minecraft.util.Hand
import net.minecraft.util.hit.BlockHitResult
import net.minecraft.util.math.BlockPos
import net.minecraft.world.BlockView
import net.minecraft.world.World
import java.util.*

class CannaPlantBlock(settings: Settings?) : TallPlantBlock(settings), Fertilizable {
    companion object {
        @JvmField val HALF: EnumProperty<DoubleBlockHalf> = TallPlantBlock.HALF
        @JvmField val AGE: IntProperty = Properties.AGE_2
    }

    init {
        defaultState = defaultState.with(AGE, 0)
    }

    override fun appendProperties(builder: StateManager.Builder<Block, BlockState>) {
        super.appendProperties(builder)
        builder.add(AGE)
    }

    override fun onUse(
        state: BlockState,
        world: World,
        pos: BlockPos,
        player: PlayerEntity,
        hand: Hand,
        hitResult: BlockHitResult
    ): ActionResult {
        if (state.get(HALF) == DoubleBlockHalf.UPPER) {
            if (state.get(AGE) < 2)
                return ActionResult.PASS
            val stack = player.getStackInHand(hand)
            if (stack.item != Items.GLASS_BOTTLE)
                return ActionResult.PASS
            world.playSound(player, pos.x.toDouble(), pos.y.toDouble(), pos.z.toDouble(),
                SoundEvents.ITEM_BOTTLE_FILL, SoundCategory.NEUTRAL,
                1f, 1f)
            stack.decrement(1)
            player.inventory.offerOrDrop(ItemStack(VoidlandsItems.CANNA_BLOOM_IN_A_BOTTLE))
            world.setBlockState(pos, state.with(AGE, 0), 3)
            return ActionResult.SUCCESS
        } else {
            val pos2 = pos.up()
            return onUse(world.getBlockState(pos2), world, pos2, player, hand, hitResult)
        }
    }

    override fun hasRandomTicks(state: BlockState): Boolean {
        return state[HALF] == DoubleBlockHalf.UPPER && state[AGE] < 2
    }

    override fun randomTick(state: BlockState, world: ServerWorld, pos: BlockPos, random: Random) {
        val age = state[AGE]
        if (age < 2) {
            if (random.nextInt(26) == 0)
                world.setBlockState(pos, state.with(AGE, age + 1), 3)
        }
    }

    override fun isFertilizable(
        world: BlockView,
        pos: BlockPos,
        state: BlockState,
        bl: Boolean
    ): Boolean {
        return if (state[HALF] == DoubleBlockHalf.UPPER)
            state[AGE] < 2
        else
            world.getBlockState(pos.up())[AGE] < 2
    }

    override fun canGrow(world: World, random: Random, pos: BlockPos, state: BlockState): Boolean {
        return true
    }

    override fun grow(world: ServerWorld, random: Random, pos: BlockPos, state: BlockState) {
        if (state[HALF] == DoubleBlockHalf.UPPER) {
            var age = state[AGE]
            age += if (random.nextInt(3) == 0) 1 else 0
            if (age > 2)
                age = 2
            world.setBlockState(pos, state.with(AGE, age), 3)
        } else {
            val pos2 = pos.up()
            grow(world, random, pos2, world.getBlockState(pos2))
        }
    }
}
package com.remodstudios.voidlands.block

import com.remodstudios.voidlands.item.VoidlandsItems
import net.minecraft.block.Block
import net.minecraft.block.BlockState
import net.minecraft.block.TallPlantBlock
import net.minecraft.block.enums.DoubleBlockHalf
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.item.ItemPlacementContext
import net.minecraft.item.ItemStack
import net.minecraft.item.Items
import net.minecraft.sound.SoundCategory
import net.minecraft.sound.SoundEvents
import net.minecraft.state.StateManager
import net.minecraft.state.property.IntProperty
import net.minecraft.state.property.Properties
import net.minecraft.util.ActionResult
import net.minecraft.util.Hand
import net.minecraft.util.hit.BlockHitResult
import net.minecraft.util.math.BlockPos
import net.minecraft.world.World

class CannaPlantBlock(settings: Settings?) : TallPlantBlock(settings) {
    companion object {
        val AGE: IntProperty = Properties.AGE_2
    }

    init {
        defaultState = defaultState.with(AGE, 0)
    }

    override fun appendProperties(builder: StateManager.Builder<Block, BlockState>?) {
        super.appendProperties(builder)
        builder?.add(AGE)
    }

    override fun canReplace(state: BlockState?, ctx: ItemPlacementContext?): Boolean {
        return false
    }

    override fun onUse(
        state: BlockState?,
        world: World?,
        pos: BlockPos?,
        player: PlayerEntity?,
        hand: Hand?,
        hitResult: BlockHitResult?
    ): ActionResult {
        if (state == null || world == null || pos == null || player == null || hand == null)
            return ActionResult.PASS
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
            player.inventory.offerOrDrop(world, ItemStack(VoidlandsItems.CANNA_BLOOM_IN_A_BOTTLE))
            world.setBlockState(pos, state.with(AGE, 0), 3)
            return ActionResult.SUCCESS
        } else {
            val pos2 = pos.up()
            val state2 = world.getBlockState(pos2)
            return onUse(state2, world, pos2, player, hand, hitResult)
        }
    }

    // TODO bloom mechanic
}
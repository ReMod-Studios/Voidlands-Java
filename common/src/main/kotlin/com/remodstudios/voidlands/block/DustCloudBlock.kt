package com.remodstudios.voidlands.block

import net.minecraft.block.Block
import net.minecraft.block.BlockState
import net.minecraft.entity.Entity
import net.minecraft.entity.ai.pathing.NavigationType
import net.minecraft.util.math.BlockPos
import net.minecraft.util.math.Vec3d
import net.minecraft.world.BlockView
import net.minecraft.world.World

class DustCloudBlock(settings: Settings) : Block(settings) {
    companion object {
        private val SLOW_FACTOR = Vec3d(1.0, 0.05000000074505806, 1.0)
    }

    override fun onEntityCollision(blockState: BlockState?, world: World?, blockPos: BlockPos?, entity: Entity) {
        entity.slowMovement(blockState, SLOW_FACTOR)
    }

    override fun canPathfindThrough(
        state: BlockState,
        world: BlockView,
        pos: BlockPos,
        navType: NavigationType
    ): Boolean {
        // pretend we're air
        return true
    }
}
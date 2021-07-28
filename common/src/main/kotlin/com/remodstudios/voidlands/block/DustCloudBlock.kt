package com.remodstudios.voidlands.block

import net.minecraft.block.BlockState
import net.minecraft.block.ShapeContext
import net.minecraft.block.TransparentBlock
import net.minecraft.entity.Entity
import net.minecraft.entity.ai.pathing.NavigationType
import net.minecraft.util.math.BlockPos
import net.minecraft.util.math.Vec3d
import net.minecraft.util.shape.VoxelShape
import net.minecraft.util.shape.VoxelShapes
import net.minecraft.world.BlockView
import net.minecraft.world.World

class DustCloudBlock(settings: Settings) : TransparentBlock(settings) {
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
    ): Boolean = true

    override fun getCameraCollisionShape(state: BlockState, world: BlockView, pos: BlockPos, ctx: ShapeContext): VoxelShape =
        VoxelShapes.empty()

    override fun getAmbientOcclusionLightLevel(state: BlockState, world: BlockView, pos: BlockPos) =
        1.0f

    override fun isTranslucent(state: BlockState, world: BlockView, pos: BlockPos): Boolean = true
}
package com.remodstudios.archskeleton.fabric

import com.remodstudios.archskeleton.ArchitecturySkeleton
import com.remodstudios.archskeleton.client.ArchitecturySkeletonClient
import net.fabricmc.api.ClientModInitializer
import net.fabricmc.api.EnvType
import net.fabricmc.api.Environment
import net.fabricmc.api.ModInitializer

@Suppress("unused")
object ArchitecturySkeletonFabric: ModInitializer {
    override fun onInitialize() {
        ArchitecturySkeleton.init()
    }
}

@Suppress("unused")
@Environment(EnvType.CLIENT)
object ArchitecturySkeletonFabricClient: ClientModInitializer {
    override fun onInitializeClient() {
        ArchitecturySkeletonClient.init()
    }
}
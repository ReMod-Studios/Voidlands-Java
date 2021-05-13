package com.remodstudios.archskeleton.forge

import com.remodstudios.archskeleton.ArchitecturySkeleton
import com.remodstudios.archskeleton.client.ArchitecturySkeletonClient
import me.shedaniel.architectury.platform.forge.EventBuses
import net.minecraftforge.fml.common.Mod
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent
import thedarkcolour.kotlinforforge.forge.MOD_BUS

@Mod(ArchitecturySkeleton.MOD_ID)
object ArchitecturySkeletonForge {
    init {
        EventBuses.registerModEventBus(ArchitecturySkeleton.MOD_ID, MOD_BUS);
        MOD_BUS.addListener(::onClientSetup)

        ArchitecturySkeleton.init();
    }

    private fun onClientSetup(event: FMLClientSetupEvent) {
        ArchitecturySkeletonClient.init()
    }
}
package com.remodstudios.voidlands.forge

import com.remodstudios.voidlands.Voidlands
import com.remodstudios.voidlands.client.VoidlandsClient
import dev.architectury.platform.forge.EventBuses
import net.minecraftforge.fml.common.Mod
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent
import thedarkcolour.kotlinforforge.forge.MOD_BUS

@Mod(Voidlands.MOD_ID)
object VoidlandsForge {
    init {
        EventBuses.registerModEventBus(Voidlands.MOD_ID, MOD_BUS);
        MOD_BUS.addListener(::onClientSetup)

        Voidlands.init();
    }

    private fun onClientSetup(event: FMLClientSetupEvent) {
        VoidlandsClient.init()
    }
}
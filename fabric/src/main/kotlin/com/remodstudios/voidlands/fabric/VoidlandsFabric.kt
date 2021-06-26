package com.remodstudios.voidlands.fabric

import com.remodstudios.voidlands.Voidlands
import com.remodstudios.voidlands.client.VoidlandsClient
import net.fabricmc.api.ClientModInitializer
import net.fabricmc.api.EnvType
import net.fabricmc.api.Environment
import net.fabricmc.api.ModInitializer

@Suppress("unused")
object VoidlandsFabric: ModInitializer {
    override fun onInitialize() {
        Voidlands.init()
    }
}

@Suppress("unused")
@Environment(EnvType.CLIENT)
object VoidlandsFabricClient: ClientModInitializer {
    override fun onInitializeClient() {
        VoidlandsClient.init()
    }
}
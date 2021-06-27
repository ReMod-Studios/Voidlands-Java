package com.remodstudios.voidlands.client

import com.remodstudios.voidlands.block.VoidlandsBlocks

object VoidlandsClient {
    fun init() {
        VoidlandsBlocks.registerRenderTypes()
    }
}
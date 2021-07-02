package com.remodstudios.voidlands.block

import com.remodstudios.voidlands.item.VoidlandsItems
import net.minecraft.block.Block

class VoidBerryBlock(settings: Settings?) : Block(settings) {
    override fun getTranslationKey(): String {
        return VoidlandsItems.VOID_BERRY.translationKey;
    }
}
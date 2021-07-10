package com.remodstudios.voidlands.block

import net.minecraft.block.Block
import net.minecraft.block.CandleCakeBlock
import net.minecraft.block.PaneBlock

// protected constructor workarounds
class ModPaneBlock(settings: Settings?) : PaneBlock(settings)
class ModCandleCakeBlock(block: Block?, settings: Settings?) : CandleCakeBlock(block, settings)
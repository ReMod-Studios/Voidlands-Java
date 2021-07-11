package com.remodstudios.voidlands.block

import net.minecraft.block.Block
import net.minecraft.block.CandleCakeBlock
import net.minecraft.block.DyedCarpetBlock
import net.minecraft.util.DyeColor

// protected constructor workarounds

class ModDyedCarpetBlock(dyeColor: DyeColor?, settings: Settings?) : DyedCarpetBlock(dyeColor, settings)
class ModCandleCakeBlock(block: Block?, settings: Settings?) : CandleCakeBlock(block, settings)
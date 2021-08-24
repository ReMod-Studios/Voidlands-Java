package com.remodstudios.voidlands.block

import net.minecraft.block.*
import net.minecraft.util.DyeColor

// protected constructor workarounds

class ModDyedCarpetBlock(color: DyeColor, settings: Settings) : DyedCarpetBlock(color, settings)
class ModCandleCakeBlock(block: Block, settings: Settings) : CandleCakeBlock(block, settings)
class ModStairsBlock(baseBlock: BlockState, settings: Settings) : StairsBlock(baseBlock, settings)
class ModWoodenButtonBlock(settings: Settings) : WoodenButtonBlock(settings)
class ModDoorBlock(settings: Settings) : DoorBlock(settings)
class ModPressurePlateBlock(activationRule: ActivationRule, settings: Settings) : PressurePlateBlock(activationRule, settings)
class ModTrapdoorBlock(settings: Settings) : TrapdoorBlock(settings)

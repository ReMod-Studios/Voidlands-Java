package com.remodstudios.voidlands.block

import net.minecraft.block.Block
import net.minecraft.block.BlockState
import net.minecraft.block.PaneBlock
import net.minecraft.state.StateManager

// protected constructor workaround
class ModPaneBlock(settings: Settings?) : PaneBlock(settings) { }
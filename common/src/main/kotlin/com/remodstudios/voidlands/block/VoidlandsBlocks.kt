package com.remodstudios.voidlands.block

import com.remodstudios.voidlands.Voidlands
import io.github.remodstudios.remodcore.registry.BlockRegistryHelper
import me.shedaniel.architectury.registry.BlockProperties
import me.shedaniel.architectury.registry.RenderTypes
import net.minecraft.block.*
import net.minecraft.client.render.RenderLayer

// TODO unfuck map colors probably
object VoidlandsBlocks : BlockRegistryHelper(Voidlands.MOD_ID) {
    val CANNA = add("canna", CannaPlantBlock(BlockProperties.copy(Blocks.TALL_GRASS)))
    val CANNA_SPROUT = add("canna_sprout", CannaSproutBlock(BlockProperties.copy(Blocks.GRASS)))

    //val DARK_RED_CANDLE = addCopy("dark_red_candle", Blocks.STONE) // TODO lads, candles don't exist in 1.16
    val DARK_RED_CONCRETE = addCopy("dark_red_concrete", Blocks.BLACK_CONCRETE)
    val DARK_RED_CONCRETE_POWDER = add("dark_red_concrete_powder",
        ConcretePowderBlock(DARK_RED_CONCRETE, BlockProperties.copy(Blocks.BLACK_CONCRETE_POWDER)))
    val DARK_RED_GLAZED_TERRACOTTA = addCopy("dark_red_glazed_terracotta", Blocks.BLACK_GLAZED_TERRACOTTA)
    val DARK_RED_SHULKER_BOX = add("dark_red_shulker_box", Block(BlockProperties.copy(Blocks.BLACK_SHULKER_BOX))) // TODO custom shulker box??
    val DARK_RED_STAINED_GLASS = addCopy("dark_red_stained_glass", Blocks.BLACK_STAINED_GLASS)
    val DARK_RED_STAINED_GLASS_PANE = add("dark_red_stained_glass_pane", ModPaneBlock(BlockProperties.copy(Blocks.BLACK_STAINED_GLASS_PANE)))
    val DARK_RED_TERRACOTTA = addCopy("dark_red_terracotta", Blocks.BLACK_TERRACOTTA)
    val DARK_RED_WOOL = addCopy("dark_red_wool", Blocks.BLACK_WOOL)

    //val SAFFRON_CANDLE = addCopy("saffron_candle", Blocks.STONE) // TODO lads, candles don't exist in 1.16
    val SAFFRON_CONCRETE = addCopy("saffron_concrete", Blocks.BLACK_CONCRETE)
    val SAFFRON_CONCRETE_POWDER = add("saffron_concrete_powder",
        ConcretePowderBlock(SAFFRON_CONCRETE, BlockProperties.copy(Blocks.BLACK_CONCRETE_POWDER)))
    val SAFFRON_GLAZED_TERRACOTTA = addCopy("saffron_glazed_terracotta", Blocks.BLACK_GLAZED_TERRACOTTA)
    val SAFFRON_SHULKER_BOX = add("saffron_shulker_box", Block(BlockProperties.copy(Blocks.BLACK_SHULKER_BOX))) // TODO custom shulker box??
    val SAFFRON_STAINED_GLASS = addCopy("saffron_stained_glass", Blocks.BLACK_STAINED_GLASS)
    val SAFFRON_STAINED_GLASS_PANE = add("saffron_stained_glass_pane", ModPaneBlock(BlockProperties.copy(Blocks.BLACK_STAINED_GLASS_PANE)))
    val SAFFRON_TERRACOTTA = addCopy("saffron_terracotta", Blocks.BLACK_TERRACOTTA)
    val SAFFRON_WOOL = addCopy("saffron_wool", Blocks.BLACK_WOOL)

    val ASHSTONE = addCopy("ashstone", Blocks.STONE)
    val ASHSTONE_BRICKS = addCopy("ashstone_bricks", Blocks.STONE_BRICKS)
    val CHISELED_ASHSTONE_BRICKS = addCopy("chiseled_ashstone_bricks", Blocks.CHISELED_STONE_BRICKS)
    val DUST_CLOUD = addCopyWithInit("dust_cloud", Blocks.SNOW) { noCollision() }
    val DRIED_ROOTS = addCopy("dried_roots", Blocks.DEAD_BUSH)
    val OSMIUM_BLOCK = addCopy("osmium_block", Blocks.IRON_BLOCK)
    val VOID_BERRY = addCopy("void_berry", VoidBerryBlock(BlockProperties.copy(Blocks.COCOA)))

    fun registerRenderTypes() {
        RenderTypes.register(RenderLayer.getCutout(),
            CANNA, CANNA_SPROUT)
        RenderTypes.register(RenderLayer.getTranslucent(),
            DARK_RED_STAINED_GLASS, DARK_RED_STAINED_GLASS_PANE,
            SAFFRON_STAINED_GLASS, SAFFRON_STAINED_GLASS_PANE,
            DUST_CLOUD)
    }
}
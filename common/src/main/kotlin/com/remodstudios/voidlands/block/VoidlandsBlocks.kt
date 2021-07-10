package com.remodstudios.voidlands.block

import com.remodstudios.voidlands.Voidlands
import io.github.remodstudios.remodcore.registry.BlockRegistryHelper
import dev.architectury.registry.block.BlockProperties
import dev.architectury.registry.client.rendering.RenderTypeRegistry
import net.minecraft.block.*
import net.minecraft.client.render.RenderLayer

// TODO unfuck map colors probably
object VoidlandsBlocks : BlockRegistryHelper(Voidlands.MOD_ID) {
    val CANNA = add("canna", CannaPlantBlock(BlockProperties.copy(Blocks.TALL_GRASS)))
    val CANNA_SPROUT = add("canna_sprout", CannaSproutBlock(BlockProperties.copy(Blocks.GRASS)))

    val DARK_RED_CANDLE = add("dark_red_candle", CandleBlock(BlockProperties.copy(Blocks.CANDLE)))
    val DARK_RED_CANDLE_CAKE = add("dark_red_candle_cake",
        ModCandleCakeBlock(DARK_RED_CANDLE, BlockProperties.copy(Blocks.CANDLE_CAKE)))
    val DARK_RED_CONCRETE = addCopy("dark_red_concrete", Blocks.BLACK_CONCRETE)
    val DARK_RED_CONCRETE_POWDER = add("dark_red_concrete_powder",
        ConcretePowderBlock(DARK_RED_CONCRETE, BlockProperties.copy(Blocks.BLACK_CONCRETE_POWDER)))
    val DARK_RED_GLAZED_TERRACOTTA = add("dark_red_glazed_terracotta",
        GlazedTerracottaBlock(BlockProperties.copy(Blocks.BLACK_GLAZED_TERRACOTTA)))
    val DARK_RED_SHULKER_BOX = add("dark_red_shulker_box", Block(BlockProperties.copy(Blocks.BLACK_SHULKER_BOX))) // TODO custom shulker box??
    val DARK_RED_STAINED_GLASS = add("dark_red_stained_glass", GlassBlock(BlockProperties.copy(Blocks.BLACK_STAINED_GLASS)))
    val DARK_RED_STAINED_GLASS_PANE = add("dark_red_stained_glass_pane", ModPaneBlock(BlockProperties.copy(Blocks.BLACK_STAINED_GLASS_PANE)))
    val DARK_RED_TERRACOTTA = addCopy("dark_red_terracotta", Blocks.BLACK_TERRACOTTA)
    val DARK_RED_WOOL = addCopy("dark_red_wool", Blocks.BLACK_WOOL)

    val SAFFRON_CANDLE = add("saffron_candle", CandleBlock(BlockProperties.copy(Blocks.CANDLE)))
    val SAFFRON_CANDLE_CAKE = add("saffron_candle_cake",
        ModCandleCakeBlock(SAFFRON_CANDLE, BlockProperties.copy(Blocks.CANDLE_CAKE)))
    val SAFFRON_CONCRETE = addCopy("saffron_concrete", Blocks.BLACK_CONCRETE)
    val SAFFRON_CONCRETE_POWDER = add("saffron_concrete_powder",
        ConcretePowderBlock(SAFFRON_CONCRETE, BlockProperties.copy(Blocks.BLACK_CONCRETE_POWDER)))
    val SAFFRON_GLAZED_TERRACOTTA = add("saffron_glazed_terracotta",
        GlazedTerracottaBlock(BlockProperties.copy(Blocks.BLACK_GLAZED_TERRACOTTA)))
    val SAFFRON_SHULKER_BOX = add("saffron_shulker_box", Block(BlockProperties.copy(Blocks.BLACK_SHULKER_BOX))) // TODO custom shulker box??
    val SAFFRON_STAINED_GLASS = add("saffron_stained_glass", GlassBlock(BlockProperties.copy(Blocks.BLACK_STAINED_GLASS)))
    val SAFFRON_STAINED_GLASS_PANE = add("saffron_stained_glass_pane", ModPaneBlock(BlockProperties.copy(Blocks.BLACK_STAINED_GLASS_PANE)))
    val SAFFRON_TERRACOTTA = addCopy("saffron_terracotta", Blocks.BLACK_TERRACOTTA)
    val SAFFRON_WOOL = addCopy("saffron_wool", Blocks.BLACK_WOOL)

    val ASHSTONE = addCopy("ashstone", Blocks.NETHER_BRICKS)
    val ASHSTONE_BRICKS = addCopy("ashstone_bricks", Blocks.NETHER_BRICKS)
    val ASHSTONE_TILES = addCopy("ashstone_tiles", Blocks.NETHER_BRICKS)
    val POLISHED_ASHSTONE = addCopy("polished_ashstone", Blocks.NETHER_BRICKS)
    val CHISELED_ASHSTONE_BRICKS = addCopy("chiseled_ashstone_bricks", Blocks.NETHER_BRICKS)
    val DUST_CLOUD = add("dust_cloud",
        DustCloudBlock(BlockProperties.copy(Blocks.WHITE_WOOL).noCollision().nonOpaque().strength(0.2f)))
    val DRIED_ROOTS = addCopy("dried_roots", Blocks.GRASS_BLOCK)
    val OSMIUM_BLOCK = addCopy("osmium_block", Blocks.IRON_BLOCK)
    val VOID_BERRY = addCopy("void_berry", VoidBerryBlock(BlockProperties.copy(Blocks.COCOA)))
    val MARBLE = addCopy("marble", Blocks.DEEPSLATE)
    val MARBLE_ROCKS = add("marble_rocks",
        MarbleRocksBlock(BlockProperties.copy(Blocks.DEEPSLATE).dropsNothing()))

    fun registerRenderTypes() {
        RenderTypeRegistry.register(RenderLayer.getCutout(),
            CANNA, CANNA_SPROUT)
        RenderTypeRegistry.register(RenderLayer.getTranslucent(),
            DARK_RED_STAINED_GLASS, DARK_RED_STAINED_GLASS_PANE,
            SAFFRON_STAINED_GLASS, SAFFRON_STAINED_GLASS_PANE,
            DUST_CLOUD)
    }
}

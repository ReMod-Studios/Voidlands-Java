package com.remodstudios.voidlands.block

import com.remodstudios.voidlands.Voidlands
import io.github.remodstudios.remodcore.registry.BlockRegistryHelper
import dev.architectury.registry.block.BlockProperties
import dev.architectury.registry.client.rendering.RenderTypeRegistry
import net.minecraft.block.*
import net.minecraft.block.piston.PistonBehavior
import net.minecraft.client.render.RenderLayer
import net.minecraft.sound.BlockSoundGroup

// TODO unfuck map colors probably
object VoidlandsBlocks : BlockRegistryHelper(Voidlands.MOD_ID) {
    @JvmField
    val MARBLE_ROCKS_MATERIAL = Material(Material.STONE.color, false, false, true, false, false, false, PistonBehavior.DESTROY)

    @JvmField
    val CANNA = add("canna", CannaPlantBlock(BlockProperties.copy(Blocks.TALL_GRASS)))
    @JvmField
    val CANNA_SPROUT = add("canna_sprout", CannaSproutBlock(BlockProperties.copy(Blocks.GRASS)))

    @JvmField
    val CRAYOLA_CARPET = add("crayola_carpet", CarpetBlock(BlockProperties.copy(Blocks.BLACK_CARPET)))
    @JvmField
    val CRAYOLA_CANDLE = add("crayola_candle", CandleBlock(BlockProperties.copy(Blocks.CANDLE)))
    @JvmField
    val CRAYOLA_CANDLE_CAKE = add("crayola_candle_cake",
        ModCandleCakeBlock(CRAYOLA_CANDLE, BlockProperties.copy(Blocks.CANDLE_CAKE)))
    @JvmField
    val CRAYOLA_CONCRETE = addCopy("crayola_concrete", Blocks.BLACK_CONCRETE)
    @JvmField
    val CRAYOLA_CONCRETE_POWDER = add("crayola_concrete_powder",
        ConcretePowderBlock(CRAYOLA_CONCRETE, BlockProperties.copy(Blocks.BLACK_CONCRETE_POWDER)))
    @JvmField
    val CRAYOLA_GLAZED_TERRACOTTA = add("crayola_glazed_terracotta",
        GlazedTerracottaBlock(BlockProperties.copy(Blocks.BLACK_GLAZED_TERRACOTTA)))
    @JvmField
    val CRAYOLA_SHULKER_BOX = add("crayola_shulker_box", Block(BlockProperties.copy(Blocks.BLACK_SHULKER_BOX))) // TODO custom shulker box??
    @JvmField
    val CRAYOLA_STAINED_GLASS = add("crayola_stained_glass", GlassBlock(BlockProperties.copy(Blocks.BLACK_STAINED_GLASS)))
    @JvmField
    val CRAYOLA_STAINED_GLASS_PANE = add("crayola_stained_glass_pane", ModPaneBlock(BlockProperties.copy(Blocks.BLACK_STAINED_GLASS_PANE)))
    @JvmField
    val CRAYOLA_TERRACOTTA = addCopy("crayola_terracotta", Blocks.BLACK_TERRACOTTA)
    @JvmField
    val CRAYOLA_WOOL = addCopy("crayola_wool", Blocks.BLACK_WOOL)

    @JvmField
    val DARK_RED_CARPET = add("dark_red_carpet", CarpetBlock(BlockProperties.copy(Blocks.BLACK_CARPET)))
    @JvmField
    val DARK_RED_CANDLE = add("dark_red_candle", CandleBlock(BlockProperties.copy(Blocks.CANDLE)))
    @JvmField
    val DARK_RED_CANDLE_CAKE = add("dark_red_candle_cake",
        ModCandleCakeBlock(DARK_RED_CANDLE, BlockProperties.copy(Blocks.CANDLE_CAKE)))
    @JvmField
    val DARK_RED_CONCRETE = addCopy("dark_red_concrete", Blocks.BLACK_CONCRETE)
    @JvmField
    val DARK_RED_CONCRETE_POWDER = add("dark_red_concrete_powder",
        ConcretePowderBlock(DARK_RED_CONCRETE, BlockProperties.copy(Blocks.BLACK_CONCRETE_POWDER)))
    @JvmField
    val DARK_RED_GLAZED_TERRACOTTA = add("dark_red_glazed_terracotta",
        GlazedTerracottaBlock(BlockProperties.copy(Blocks.BLACK_GLAZED_TERRACOTTA)))
    @JvmField
    val DARK_RED_SHULKER_BOX = add("dark_red_shulker_box", Block(BlockProperties.copy(Blocks.BLACK_SHULKER_BOX))) // TODO custom shulker box??
    @JvmField
    val DARK_RED_STAINED_GLASS = add("dark_red_stained_glass", GlassBlock(BlockProperties.copy(Blocks.BLACK_STAINED_GLASS)))
    @JvmField
    val DARK_RED_STAINED_GLASS_PANE = add("dark_red_stained_glass_pane", ModPaneBlock(BlockProperties.copy(Blocks.BLACK_STAINED_GLASS_PANE)))
    @JvmField
    val DARK_RED_TERRACOTTA = addCopy("dark_red_terracotta", Blocks.BLACK_TERRACOTTA)
    @JvmField
    val DARK_RED_WOOL = addCopy("dark_red_wool", Blocks.BLACK_WOOL)

    @JvmField
    val ASHSTONE = addCopy("ashstone", Blocks.NETHER_BRICKS)
    @JvmField
    val POLISHED_ASHSTONE = addCopy("polished_ashstone", Blocks.NETHER_BRICKS)
    @JvmField
    val ASHSTONE_BRICKS = addCopy("ashstone_bricks", Blocks.NETHER_BRICKS)
    @JvmField
    val ASHSTONE_TILES = addCopy("ashstone_tiles", Blocks.NETHER_BRICKS)
    @JvmField
    val CHISELED_ASHSTONE_BRICKS = addCopy("chiseled_ashstone_bricks", Blocks.NETHER_BRICKS)
    @JvmField
    val DUST_CLOUD = add("dust_cloud",
        DustCloudBlock(BlockProperties.copy(Blocks.WHITE_WOOL).noCollision().nonOpaque().strength(0.2f)))
    @JvmField
    val DRIED_ROOTS = addWoodlike("dried_roots") { Block(this.sounds(BlockSoundGroup.NETHER_STEM)) }
    @JvmField
    val OSMIUM_BLOCK = addCopy("osmium_block", Blocks.IRON_BLOCK)
    @JvmField
    val VOID_BERRY = addCopy("void_berry", VoidBerryBlock(BlockProperties.copy(Blocks.COCOA)))
    @JvmField
    val MARBLE = addCopy("marble", Blocks.DEEPSLATE)
    @JvmField
    val MARBLE_ROCKS = add("marble_rocks",
        MarbleRocksBlock(BlockProperties.of(MARBLE_ROCKS_MATERIAL).nonOpaque().dropsNothing()))

    fun registerRenderTypes() {
        RenderTypeRegistry.register(RenderLayer.getCutout(),
            CANNA, CANNA_SPROUT)
        RenderTypeRegistry.register(RenderLayer.getTranslucent(),
            DARK_RED_STAINED_GLASS, DARK_RED_STAINED_GLASS_PANE,
            CRAYOLA_STAINED_GLASS, CRAYOLA_STAINED_GLASS_PANE,
            DUST_CLOUD)
    }
}

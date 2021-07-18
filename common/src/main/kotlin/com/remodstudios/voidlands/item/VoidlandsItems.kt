package com.remodstudios.voidlands.item

import com.remodstudios.voidlands.Voidlands
import com.remodstudios.voidlands.block.VoidlandsBlocks
import com.remodstudios.voidlands.util.VoidlandsDyeColors
import dev.architectury.registry.CreativeTabRegistry
import io.github.remodstudios.remodcore.registry.ItemRegistryHelper
import net.minecraft.item.*

object VoidlandsItems : ItemRegistryHelper(Voidlands.MOD_ID) {
    @JvmField
    val GROUP = CreativeTabRegistry.create(Voidlands.id("group")) { ItemStack(VOID_BERRY) }

    override fun defaultSettings(): Item.Settings {
        return Item.Settings().group(GROUP)
    }

    @JvmField
    val ASHSTONE_ROCK = add("ashstone_rock")
    @JvmField
    val CANNA_BLOOM_IN_A_BOTTLE = add("canna_bloom_in_a_bottle") { it.maxCount(1) }
    @JvmField
    val CANNA_LEAF = add("canna_leaf")
    @JvmField
    val DRIED_ROOT_STICK = add("dried_root_stick")
    @JvmField
    val VOID_BERRY = add("void_berry") { it.food(FoodComponent.Builder().hunger(4).build()) }
    @JvmField
    val OSMIUM_INGOT = add("osmium_ingot")

    @JvmField
    val CRAYOLA_DYE = addWithFactory("crayola_dye") { DyeItem(VoidlandsDyeColors.CRAYOLA, it) }
    @JvmField
    val DARK_RED_DYE = addWithFactory("dark_red_dye") { DyeItem(VoidlandsDyeColors.DARK_RED, it) }

    // region Block Items
    @JvmField
    val ASHSTONE = add("ashstone", VoidlandsBlocks.ASHSTONE)
    @JvmField
    val POLISHED_ASHSTONE = add("polished_ashstone", VoidlandsBlocks.POLISHED_ASHSTONE)
    @JvmField
    val ASHSTONE_BRICKS = add("ashstone_bricks", VoidlandsBlocks.ASHSTONE_BRICKS)
    @JvmField
    val ASHSTONE_TILES = add("ashstone_tiles", VoidlandsBlocks.ASHSTONE_TILES)
    @JvmField
    val ASHSTONE_PILLAR = add("ashstone_pillar", VoidlandsBlocks.ASHSTONE_PILLAR)
    @JvmField
    val CHISELED_ASHSTONE_BRICKS = add("chiseled_ashstone_bricks", VoidlandsBlocks.CHISELED_ASHSTONE_BRICKS)
    @JvmField
    val CRACKED_ASHSTONE_BRICKS = add("cracked_ashstone_bricks", VoidlandsBlocks.CHISELED_ASHSTONE_BRICKS)
    
    @JvmField
    val ASHSTONE_SLAB = add("ashstone_slab", VoidlandsBlocks.ASHSTONE_SLAB)
    @JvmField
    val POLISHED_ASHSTONE_SLAB = add("polished_ashstone_slab", VoidlandsBlocks.POLISHED_ASHSTONE_SLAB)
    @JvmField
    val ASHSTONE_BRICK_SLAB = add("ashstone_brick_slab", VoidlandsBlocks.ASHSTONE_BRICK_SLAB)
    @JvmField
    val ASHSTONE_TILE_SLAB = add("ashstone_tile_slab", VoidlandsBlocks.ASHSTONE_TILE_SLAB)
    @JvmField
    val CHISELED_ASHSTONE_BRICK_SLAB = add("chiseled_ashstone_brick_slab", VoidlandsBlocks.CHISELED_ASHSTONE_BRICK_SLAB)
    @JvmField
    val CRACKED_ASHSTONE_BRICK_SLAB = add("cracked_ashstone_brick_slab", VoidlandsBlocks.CRACKED_ASHSTONE_BRICK_SLAB)

    @JvmField
    val ASHSTONE_STAIRS = add("ashstone_stairs", VoidlandsBlocks.ASHSTONE_STAIRS)
    @JvmField
    val POLISHED_ASHSTONE_STAIRS = add("polished_ashstone_stairs", VoidlandsBlocks.POLISHED_ASHSTONE_STAIRS)
    @JvmField
    val ASHSTONE_BRICK_STAIRS = add("ashstone_brick_stairs", VoidlandsBlocks.ASHSTONE_BRICK_STAIRS)
    @JvmField
    val ASHSTONE_TILE_STAIRS = add("ashstone_tile_stairs", VoidlandsBlocks.ASHSTONE_TILE_STAIRS)
    @JvmField
    val CHISELED_ASHSTONE_BRICK_STAIRS = add("chiseled_ashstone_brick_stairs", VoidlandsBlocks.CHISELED_ASHSTONE_BRICK_STAIRS)
    @JvmField
    val CRACKED_ASHSTONE_BRICK_STAIRS = add("cracked_ashstone_brick_stairs", VoidlandsBlocks.CRACKED_ASHSTONE_BRICK_STAIRS)

    @JvmField
    val ASHSTONE_WALL = add("ashstone_wall", VoidlandsBlocks.ASHSTONE_WALL)
    @JvmField
    val POLISHED_ASHSTONE_WALL = add("polished_ashstone_wall", VoidlandsBlocks.POLISHED_ASHSTONE_WALL)
    @JvmField
    val ASHSTONE_BRICK_WALL = add("ashstone_brick_wall", VoidlandsBlocks.ASHSTONE_BRICK_WALL)
    @JvmField
    val ASHSTONE_TILE_WALL = add("ashstone_tile_wall", VoidlandsBlocks.ASHSTONE_TILE_WALL)
    @JvmField
    val CHISELED_ASHSTONE_BRICK_WALL = add("chiseled_ashstone_brick_wall", VoidlandsBlocks.CHISELED_ASHSTONE_BRICK_WALL)
    @JvmField
    val CRACKED_ASHSTONE_BRICK_WALL = add("cracked_ashstone_brick_wall", VoidlandsBlocks.CRACKED_ASHSTONE_BRICK_WALL)

    @JvmField
    val CANNA = add("canna", VoidlandsBlocks.CANNA)
    @JvmField
    val CANNA_SPROUT = add("canna_sprout", VoidlandsBlocks.CANNA_SPROUT)

    @JvmField
    val CRAYOLA_BANNER = addWithFactory("crayola_banner")
    { BannerItem(VoidlandsBlocks.CRAYOLA_BANNER, VoidlandsBlocks.CRAYOLA_WALL_BANNER, it) }
    @JvmField
    val CRAYOLA_BED = add("crayola_bed", VoidlandsBlocks.CRAYOLA_BED)
    @JvmField
    val CRAYOLA_CARPET = add("crayola_carpet", VoidlandsBlocks.CRAYOLA_CARPET)
    @JvmField
    val CRAYOLA_CANDLE = add("crayola_candle", VoidlandsBlocks.CRAYOLA_CANDLE)
    @JvmField
    val CRAYOLA_CONCRETE = add("crayola_concrete", VoidlandsBlocks.CRAYOLA_CONCRETE)
    @JvmField
    val CRAYOLA_CONCRETE_POWDER = add("crayola_concrete_powder", VoidlandsBlocks.CRAYOLA_CONCRETE_POWDER)
    @JvmField
    val CRAYOLA_GLAZED_TERRACOTTA = add("crayola_glazed_terracotta", VoidlandsBlocks.CRAYOLA_GLAZED_TERRACOTTA)
    @JvmField
    val CRAYOLA_SHULKER_BOX = add("crayola_shulker_box", VoidlandsBlocks.CRAYOLA_SHULKER_BOX)
    @JvmField
    val CRAYOLA_STAINED_GLASS = add("crayola_stained_glass", VoidlandsBlocks.CRAYOLA_STAINED_GLASS)
    @JvmField
    val CRAYOLA_STAINED_GLASS_PANE = add("crayola_stained_glass_pane", VoidlandsBlocks.CRAYOLA_STAINED_GLASS_PANE)
    @JvmField
    val CRAYOLA_TERRACOTTA = add("crayola_terracotta", VoidlandsBlocks.CRAYOLA_TERRACOTTA)
    @JvmField
    val CRAYOLA_WOOL = add("crayola_wool", VoidlandsBlocks.CRAYOLA_WOOL)

    @JvmField
    val DARK_RED_BANNER = addWithFactory("dark_red_banner")
    { BannerItem(VoidlandsBlocks.DARK_RED_BANNER, VoidlandsBlocks.DARK_RED_WALL_BANNER, it) }
    @JvmField
    val DARK_RED_BED = add("dark_red_bed", VoidlandsBlocks.DARK_RED_BED)
    @JvmField
    val DARK_RED_CARPET = add("dark_red_carpet", VoidlandsBlocks.DARK_RED_CARPET)
    @JvmField
    val DARK_RED_CANDLE = add("dark_red_candle", VoidlandsBlocks.DARK_RED_CANDLE)
    @JvmField
    val DARK_RED_CONCRETE = add("dark_red_concrete", VoidlandsBlocks.DARK_RED_CONCRETE)
    @JvmField
    val DARK_RED_CONCRETE_POWDER = add("dark_red_concrete_powder", VoidlandsBlocks.DARK_RED_CONCRETE_POWDER)
    @JvmField
    val DARK_RED_GLAZED_TERRACOTTA = add("dark_red_glazed_terracotta", VoidlandsBlocks.DARK_RED_GLAZED_TERRACOTTA)
    @JvmField
    val DARK_RED_SHULKER_BOX = add("dark_red_shulker_box", VoidlandsBlocks.DARK_RED_SHULKER_BOX)
    @JvmField
    val DARK_RED_STAINED_GLASS = add("dark_red_stained_glass", VoidlandsBlocks.DARK_RED_STAINED_GLASS)
    @JvmField
    val DARK_RED_STAINED_GLASS_PANE = add("dark_red_stained_glass_pane", VoidlandsBlocks.DARK_RED_STAINED_GLASS_PANE)
    @JvmField
    val DARK_RED_TERRACOTTA = add("dark_red_terracotta", VoidlandsBlocks.DARK_RED_TERRACOTTA)
    @JvmField
    val DARK_RED_WOOL = add("dark_red_wool", VoidlandsBlocks.DARK_RED_WOOL)

    @JvmField
    val DRIED_ROOTS = add("dried_roots", VoidlandsBlocks.DRIED_ROOTS)
    @JvmField
    val OSMIUM_BLOCK = add("osmium_block", VoidlandsBlocks.OSMIUM_BLOCK)

    @JvmField
    val DUST_CLOUD = add("dust_cloud", VoidlandsBlocks.DUST_CLOUD)

    @JvmField
    val MARBLE = add("marble", VoidlandsBlocks.MARBLE)
    @JvmField
    val MARBLE_SLAB = add("marble_slab", VoidlandsBlocks.MARBLE_SLAB)
    @JvmField
    val MARBLE_STAIRS = add("marble_stairs", VoidlandsBlocks.MARBLE_STAIRS)
    @JvmField
    val MARBLE_WALL = add("marble_wall", VoidlandsBlocks.MARBLE_WALL)
    @JvmField
    val MARBLE_ROCKS = add("marble_rocks", VoidlandsBlocks.MARBLE_ROCKS)
    // endregion
}
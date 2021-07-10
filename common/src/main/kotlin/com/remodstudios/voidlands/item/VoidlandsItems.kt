package com.remodstudios.voidlands.item

import com.remodstudios.voidlands.Voidlands
import com.remodstudios.voidlands.block.VoidlandsBlocks
import dev.architectury.registry.CreativeTabRegistry
import io.github.remodstudios.remodcore.registry.ItemRegistryHelper
import net.minecraft.item.FoodComponent
import net.minecraft.item.Item
import net.minecraft.item.ItemStack

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

    // region Block Items
    @JvmField
    val ASHSTONE = add("ashstone", VoidlandsBlocks.ASHSTONE)
    @JvmField
    val ASHSTONE_BRICKS = add("ashstone_bricks", VoidlandsBlocks.ASHSTONE_BRICKS)
    @JvmField
    val CHISELED_ASHSTONE_BRICKS = add("chiseled_ashstone_bricks", VoidlandsBlocks.CHISELED_ASHSTONE_BRICKS)

    @JvmField
    val CANNA = add("canna", VoidlandsBlocks.CANNA)
    @JvmField
    val CANNA_SPROUT = add("canna_sprout", VoidlandsBlocks.CANNA_SPROUT)

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
    val DUST_CLOUD = add("dust_cloud", VoidlandsBlocks.DUST_CLOUD)

    @JvmField
    val MARBLE = add("marble", VoidlandsBlocks.MARBLE)
    @JvmField
    val MARBLE_ROCKS = add("marble_rocks", VoidlandsBlocks.MARBLE_ROCKS)
    // endregion
}
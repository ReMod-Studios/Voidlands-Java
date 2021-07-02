package com.remodstudios.voidlands.item

import com.remodstudios.voidlands.Voidlands
import com.remodstudios.voidlands.block.VoidlandsBlocks
import io.github.remodstudios.remodcore.registry.ItemRegistryHelper
import me.shedaniel.architectury.registry.CreativeTabs
import net.minecraft.item.Item
import net.minecraft.item.ItemStack

object VoidlandsItems : ItemRegistryHelper(Voidlands.MOD_ID) {
    val GROUP = CreativeTabs.create(Voidlands.id("group")) { ItemStack(VOID_BERRY) }

    override fun defaultSettings(): Item.Settings {
        return Item.Settings().group(GROUP)
    }

    val ASHSTONE_ROCK = add("ashstone_rock")
    val CANNA_BLOOM_IN_A_BOTTLE = add("canna_bloom_in_a_bottle") { it.maxCount(1) }
    val CANNA_LEAF = add("canna_leaf")
    val DRIED_ROOT_STICK = add("dried_root_stick")
    val VOID_BERRY = add("void_berry")

    // region Block Items
    val ASHSTONE = add("ashstone", VoidlandsBlocks.ASHSTONE)
    val ASHSTONE_BRICKS = add("ashstone_bricks", VoidlandsBlocks.ASHSTONE_BRICKS)
    val CHISELED_ASHSTONE_BRICKS = add("chiseled_ashstone_bricks", VoidlandsBlocks.CHISELED_ASHSTONE_BRICKS)

    val CANNA = add("canna", VoidlandsBlocks.CANNA)
    val CANNA_SPROUT = add("canna_sprout", VoidlandsBlocks.CANNA_SPROUT)

    //val DARK_RED_CANDLE = add("dark_red_candle", VoidlandsBlocks.DARK_RED_CANDLE)
    val DARK_RED_CONCRETE = add("dark_red_concrete", VoidlandsBlocks.DARK_RED_CONCRETE)
    val DARK_RED_CONCRETE_POWDER = add("dark_red_concrete_powder", VoidlandsBlocks.DARK_RED_CONCRETE_POWDER)
    val DARK_RED_GLAZED_TERRACOTTA = add("dark_red_glazed_terracotta", VoidlandsBlocks.DARK_RED_GLAZED_TERRACOTTA)
    val DARK_RED_SHULKER_BOX = add("dark_red_shulker_box", VoidlandsBlocks.DARK_RED_SHULKER_BOX)
    val DARK_RED_STAINED_GLASS = add("dark_red_stained_glass", VoidlandsBlocks.DARK_RED_STAINED_GLASS)
    val DARK_RED_STAINED_GLASS_PANE = add("dark_red_stained_glass_pane", VoidlandsBlocks.DARK_RED_STAINED_GLASS_PANE)
    val DARK_RED_TERRACOTTA = add("dark_red_terracotta", VoidlandsBlocks.DARK_RED_TERRACOTTA)
    val DARK_RED_WOOL = add("dark_red_wool", VoidlandsBlocks.DARK_RED_WOOL)

    val DRIED_ROOTS = add("dried_roots", VoidlandsBlocks.DRIED_ROOTS)
    val OSMIUM_BLOCK = add("osmium_block", VoidlandsBlocks.OSMIUM_BLOCK)

    //val SAFFRON_CANDLE = add("saffron_candle", VoidlandsBlocks.SAFFRON_CANDLE)
    val SAFFRON_CONCRETE = add("saffron_concrete", VoidlandsBlocks.SAFFRON_CONCRETE)
    val SAFFRON_CONCRETE_POWDER = add("saffron_concrete_powder", VoidlandsBlocks.SAFFRON_CONCRETE_POWDER)
    val SAFFRON_GLAZED_TERRACOTTA = add("saffron_glazed_terracotta", VoidlandsBlocks.SAFFRON_GLAZED_TERRACOTTA)
    val SAFFRON_SHULKER_BOX = add("saffron_shulker_box", VoidlandsBlocks.SAFFRON_SHULKER_BOX)
    val SAFFRON_STAINED_GLASS = add("saffron_stained_glass", VoidlandsBlocks.SAFFRON_STAINED_GLASS)
    val SAFFRON_STAINED_GLASS_PANE = add("saffron_stained_glass_pane", VoidlandsBlocks.SAFFRON_STAINED_GLASS_PANE)
    val SAFFRON_TERRACOTTA = add("saffron_terracotta", VoidlandsBlocks.SAFFRON_TERRACOTTA)
    val SAFFRON_WOOL = add("saffron_wool", VoidlandsBlocks.SAFFRON_WOOL)

    val DUST_CLOUD = add("dust_cloud", VoidlandsBlocks.DUST_CLOUD)
    // endregion
}
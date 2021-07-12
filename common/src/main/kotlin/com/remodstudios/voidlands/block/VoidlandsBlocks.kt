package com.remodstudios.voidlands.block

import com.remodstudios.voidlands.Voidlands
import com.remodstudios.voidlands.util.VoidlandsDyeColors.CRAYOLA
import com.remodstudios.voidlands.util.VoidlandsDyeColors.DARK_RED
import dev.architectury.registry.block.BlockProperties
import dev.architectury.registry.client.rendering.RenderTypeRegistry
import io.github.remodstudios.remodcore.registry.BlockRegistryHelper
import net.fabricmc.api.EnvType
import net.fabricmc.api.Environment
import net.minecraft.block.*
import net.minecraft.block.AbstractBlock.ContextPredicate
import net.minecraft.block.AbstractBlock.TypedContextPredicate
import net.minecraft.block.dispenser.BlockPlacementDispenserBehavior
import net.minecraft.block.dispenser.FallibleItemDispenserBehavior
import net.minecraft.block.entity.ShulkerBoxBlockEntity
import net.minecraft.block.enums.BedPart
import net.minecraft.block.piston.PistonBehavior
import net.minecraft.client.render.RenderLayer
import net.minecraft.entity.Entity
import net.minecraft.entity.EntityType
import net.minecraft.entity.passive.HorseBaseEntity
import net.minecraft.item.ItemStack
import net.minecraft.sound.BlockSoundGroup
import net.minecraft.util.DyeColor
import net.minecraft.util.math.BlockPointer
import net.minecraft.util.math.BlockPos
import net.minecraft.util.math.Box
import net.minecraft.util.math.Direction
import net.minecraft.world.BlockView
import net.minecraft.world.World

object VoidlandsBlocks : BlockRegistryHelper(Voidlands.MOD_ID) {
    private fun add(id: String, props: AbstractBlock.Settings): Block
        = add(id, Block(props))

    @JvmField
    val MARBLE_ROCKS_MATERIAL = Material(Material.STONE.color, false, false, true, false, false, false, PistonBehavior.DESTROY)

    @JvmField
    val CANNA = add("canna", CannaPlantBlock(BlockProperties.copy(Blocks.TALL_GRASS)))
    @JvmField
    val CANNA_SPROUT = add("canna_sprout", CannaSproutBlock(BlockProperties.copy(Blocks.GRASS)))

    @JvmField
    val CRAYOLA_BED = add("crayola_bed", createBedBlock(CRAYOLA))
    @JvmField
    val CRAYOLA_CARPET = add("crayola_carpet",
        ModDyedCarpetBlock(CRAYOLA, BlockProperties.of(Material.WOOL, CRAYOLA).strength(0.1F).sounds(BlockSoundGroup.WOOL)))
    @JvmField
    val CRAYOLA_CANDLE = add("crayola_candle",
        CandleBlock(BlockProperties.of(Material.DECORATION, CRAYOLA).nonOpaque().strength(0.1F).sounds(BlockSoundGroup.CANDLE).luminance(CandleBlock.STATE_TO_LUMINANCE)))
    @JvmField
    val CRAYOLA_CANDLE_CAKE = add("crayola_candle_cake",
        ModCandleCakeBlock(CRAYOLA_CANDLE, BlockProperties.copy(Blocks.CANDLE_CAKE)))
    @JvmField
    val CRAYOLA_CONCRETE = add("crayola_concrete",
        BlockProperties.of(Material.STONE, CRAYOLA).requiresTool().strength(1.8F))
    @JvmField
    val CRAYOLA_CONCRETE_POWDER = add("crayola_concrete_powder",
        ConcretePowderBlock(CRAYOLA_CONCRETE, BlockProperties.of(Material.AGGREGATE, CRAYOLA).strength(0.5F).sounds(BlockSoundGroup.SAND)))
    @JvmField
    val CRAYOLA_GLAZED_TERRACOTTA = add("crayola_glazed_terracotta",
        GlazedTerracottaBlock(BlockProperties.of(Material.STONE, CRAYOLA).requiresTool().strength(1.4F)))
    @JvmField
    val CRAYOLA_SHULKER_BOX = add("crayola_shulker_box",
        createShulkerBoxBlock(CRAYOLA))
    @JvmField
    val CRAYOLA_STAINED_GLASS = add("crayola_stained_glass",
        createStainedGlassBlock(CRAYOLA))
    @JvmField
    val CRAYOLA_STAINED_GLASS_PANE = add("crayola_stained_glass_pane",
        createStainedGlassPaneBlock(CRAYOLA))
    @JvmField
    val CRAYOLA_TERRACOTTA = add("crayola_terracotta", BlockProperties.of(Material.STONE, CRAYOLA).requiresTool().strength(1.25F, 4.2F))
    @JvmField
    val CRAYOLA_WOOL = add("crayola_wool", BlockProperties.of(Material.WOOL, CRAYOLA).strength(0.8F).sounds(BlockSoundGroup.WOOL))

    @JvmField
    val DARK_RED_BED = add("dark_red_bed", createBedBlock(DARK_RED))
    @JvmField
    val DARK_RED_CARPET = add("dark_red_carpet",
        ModDyedCarpetBlock(DARK_RED, BlockProperties.of(Material.WOOL, DARK_RED).strength(0.1F).sounds(BlockSoundGroup.WOOL)))
    @JvmField
    val DARK_RED_CANDLE = add("dark_red_candle",
        CandleBlock(BlockProperties.of(Material.DECORATION, DARK_RED).nonOpaque().strength(0.1F).sounds(BlockSoundGroup.CANDLE).luminance(CandleBlock.STATE_TO_LUMINANCE)))
    @JvmField
    val DARK_RED_CANDLE_CAKE = add("dark_red_candle_cake",
        ModCandleCakeBlock(DARK_RED_CANDLE, BlockProperties.copy(Blocks.CANDLE_CAKE)))
    @JvmField
    val DARK_RED_CONCRETE = add("dark_red_concrete",
        BlockProperties.of(Material.STONE, DARK_RED).requiresTool().strength(1.8F))
    @JvmField
    val DARK_RED_CONCRETE_POWDER = add("dark_red_concrete_powder",
        ConcretePowderBlock(DARK_RED_CONCRETE, BlockProperties.of(Material.AGGREGATE, DARK_RED).strength(0.5F).sounds(BlockSoundGroup.SAND)))
    @JvmField
    val DARK_RED_GLAZED_TERRACOTTA = add("dark_red_glazed_terracotta",
        GlazedTerracottaBlock(BlockProperties.of(Material.STONE, DARK_RED).requiresTool().strength(1.4F)))
    @JvmField
    val DARK_RED_SHULKER_BOX = add("dark_red_shulker_box", 
        createShulkerBoxBlock(DARK_RED))
    @JvmField
    val DARK_RED_STAINED_GLASS = add("dark_red_stained_glass",
        createStainedGlassBlock(DARK_RED))
    @JvmField
    val DARK_RED_STAINED_GLASS_PANE = add("dark_red_stained_glass_pane",
        createStainedGlassPaneBlock(DARK_RED))
    @JvmField
    val DARK_RED_TERRACOTTA = add("dark_red_terracotta", BlockProperties.of(Material.STONE, DARK_RED).requiresTool().strength(1.25F, 4.2F))
    @JvmField
    val DARK_RED_WOOL = add("dark_red_wool", BlockProperties.of(Material.WOOL, DARK_RED).strength(0.8F).sounds(BlockSoundGroup.WOOL))

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
        MarbleRocksBlock(BlockProperties.of(MARBLE_ROCKS_MATERIAL).nonOpaque().strength(0.2F)))

    // region Private stuff
    private object ContextPredicates {
        object Never : ContextPredicate, TypedContextPredicate<EntityType<*>> {
            override fun test(state: BlockState?, world: BlockView?, pos: BlockPos?) = false
            override fun test(state: BlockState?, world: BlockView?, pos: BlockPos?, entityType: EntityType<*>?) = false
        }

        object ShulkerBox : ContextPredicate {
            override fun test(state: BlockState?, world: BlockView?, pos: BlockPos?): Boolean {
                val entity = world!!.getBlockEntity(pos)
                return if (entity !is ShulkerBoxBlockEntity)
                    true
                else
                    entity.suffocates()
            }
        }
    }

    private fun createBedBlock(dyeColor: DyeColor) =
        BedBlock(dyeColor, BlockProperties.of(Material.WOOL)
        { state ->
            if (state.get(BedBlock.PART) == BedPart.FOOT)
                dyeColor.mapColor
            else
                MapColor.WHITE_GRAY
        }.sounds(BlockSoundGroup.WOOD).strength(0.2f).nonOpaque())

    private fun createStainedGlassBlock(dyeColor: DyeColor) =
        StainedGlassBlock(dyeColor,
            BlockProperties.of(Material.GLASS, dyeColor).strength(0.3f).sounds(BlockSoundGroup.GLASS).nonOpaque()
                .allowsSpawning(ContextPredicates.Never).solidBlock(ContextPredicates.Never)
                .suffocates(ContextPredicates.Never).blockVision(ContextPredicates.Never))

    private fun createStainedGlassPaneBlock(dyeColor: DyeColor) =
        StainedGlassPaneBlock(dyeColor,
            BlockProperties.of(Material.GLASS, dyeColor).strength(0.3f).sounds(BlockSoundGroup.GLASS).nonOpaque()
                .allowsSpawning(ContextPredicates.Never).solidBlock(ContextPredicates.Never)
                .suffocates(ContextPredicates.Never).blockVision(ContextPredicates.Never))

    private fun createShulkerBoxBlock(dyeColor: DyeColor) =
        ShulkerBoxBlock(dyeColor,
            BlockProperties.of(Material.SHULKER_BOX, dyeColor).strength(2.0f).dynamicBounds().nonOpaque()
                .suffocates(ContextPredicates.ShulkerBox).blockVision(ContextPredicates.ShulkerBox))

    private object DispenserBehaviors {
        object EquipOnHorse : FallibleItemDispenserBehavior() {
            override fun dispenseSilently(ptr: BlockPointer, stack: ItemStack): ItemStack? {
                val blockPos = ptr.pos.offset(ptr.blockState.get(DispenserBlock.FACING) as Direction)
                for (horse in ptr.world.getEntities<HorseBaseEntity>(Box(blockPos)) { it.isAlive && it.hasArmorSlot() }) {
                    if (!horse.isHorseArmor(stack) || horse.hasArmorInSlot() || !horse.isTame)
                        continue
                    horse.getStackReference(401).set(stack.split(1))
                    isSuccess = true
                    return stack
                }
                return super.dispenseSilently(ptr, stack)
            }
        }

        object PlaceBlock : BlockPlacementDispenserBehavior()
    }
    // endregion

    fun registerDispenserBehaviors() {
        DispenserBlock.registerBehavior(CRAYOLA_CARPET, DispenserBehaviors.EquipOnHorse)
        DispenserBlock.registerBehavior(DARK_RED_CARPET, DispenserBehaviors.EquipOnHorse)
        DispenserBlock.registerBehavior(CRAYOLA_SHULKER_BOX, DispenserBehaviors.PlaceBlock)
        DispenserBlock.registerBehavior(DARK_RED_SHULKER_BOX, DispenserBehaviors.PlaceBlock)
    }

    @Environment(EnvType.CLIENT)
    fun registerRenderTypes() {
        RenderTypeRegistry.register(RenderLayer.getCutout(),
            CANNA, CANNA_SPROUT)
        RenderTypeRegistry.register(RenderLayer.getTranslucent(),
            DARK_RED_STAINED_GLASS, DARK_RED_STAINED_GLASS_PANE,
            CRAYOLA_STAINED_GLASS, CRAYOLA_STAINED_GLASS_PANE,
            DUST_CLOUD)
    }
}

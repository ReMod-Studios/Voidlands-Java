package com.remodstudios.voidlands.block

import com.remodstudios.voidlands.Voidlands
import com.remodstudios.voidlands.util.VoidlandsDyeColors
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

object VoidlandsBlocks : BlockRegistryHelper(Voidlands.MOD_ID) {
    private fun add(id: String, props: AbstractBlock.Settings): Block
        = add(id, Block(props))

    @JvmField
    val MARBLE_ROCKS_MATERIAL = Material(Material.STONE.color, false, false, true, false, false, false,
        PistonBehavior.DESTROY)

    @JvmField
    val CANNA = add("canna", CannaPlantBlock(BlockProperties.of(Material.PLANT)
        .noCollision().strength(0.5F).ticksRandomly().sounds(BlockSoundGroup.NETHER_STEM)))
    @JvmField
    val CANNA_SPROUT = add("canna_sprout", CannaSproutBlock(BlockProperties.of(Material.PLANT)
        .noCollision().strength(0.5F).ticksRandomly().sounds(BlockSoundGroup.NETHER_STEM)))

    @JvmField
    val CRAYOLA_BANNER = add("crayola_banner", createBannerBlock(VoidlandsDyeColors.CRAYOLA))
    @JvmField
    val CRAYOLA_WALL_BANNER = add("crayola_wall_banner", createWallBannerBlock(VoidlandsDyeColors.CRAYOLA, CRAYOLA_BANNER))
    @JvmField
    val CRAYOLA_BED = add("crayola_bed", createBedBlock(VoidlandsDyeColors.CRAYOLA))
    @JvmField
    val CRAYOLA_CARPET = add("crayola_carpet",
        ModDyedCarpetBlock(VoidlandsDyeColors.CRAYOLA, BlockProperties.of(Material.WOOL, VoidlandsDyeColors.CRAYOLA)
            .strength(0.1F).sounds(BlockSoundGroup.WOOL)))
    @JvmField
    val CRAYOLA_CANDLE = add("crayola_candle",
        CandleBlock(BlockProperties.of(Material.DECORATION, VoidlandsDyeColors.CRAYOLA)
            .nonOpaque().strength(0.1F).sounds(BlockSoundGroup.CANDLE).luminance(CandleBlock.STATE_TO_LUMINANCE)))
    @JvmField
    val CRAYOLA_CANDLE_CAKE = add("crayola_candle_cake",
        ModCandleCakeBlock(CRAYOLA_CANDLE, BlockProperties.copy(Blocks.CANDLE_CAKE)))
    @JvmField
    val CRAYOLA_CONCRETE = add("crayola_concrete",
        BlockProperties.of(Material.STONE, VoidlandsDyeColors.CRAYOLA).requiresTool().strength(1.8F))
    @JvmField
    val CRAYOLA_CONCRETE_POWDER = add("crayola_concrete_powder",
        ConcretePowderBlock(CRAYOLA_CONCRETE, BlockProperties.of(Material.AGGREGATE, VoidlandsDyeColors.CRAYOLA).strength(0.5F).sounds(BlockSoundGroup.SAND)))
    @JvmField
    val CRAYOLA_GLAZED_TERRACOTTA = add("crayola_glazed_terracotta",
        GlazedTerracottaBlock(BlockProperties.of(Material.STONE, VoidlandsDyeColors.CRAYOLA).requiresTool().strength(1.4F)))
    @JvmField
    val CRAYOLA_SHULKER_BOX = add("crayola_shulker_box",
        createShulkerBoxBlock(VoidlandsDyeColors.CRAYOLA))
    @JvmField
    val CRAYOLA_STAINED_GLASS = add("crayola_stained_glass",
        createStainedGlassBlock(VoidlandsDyeColors.CRAYOLA))
    @JvmField
    val CRAYOLA_STAINED_GLASS_PANE = add("crayola_stained_glass_pane",
        createStainedGlassPaneBlock(VoidlandsDyeColors.CRAYOLA))
    @JvmField
    val CRAYOLA_TERRACOTTA = add("crayola_terracotta", BlockProperties.of(Material.STONE, VoidlandsDyeColors.CRAYOLA)
        .requiresTool().strength(1.25F, 4.2F))
    @JvmField
    val CRAYOLA_WOOL = add("crayola_wool", BlockProperties.of(Material.WOOL, VoidlandsDyeColors.CRAYOLA).
    strength(0.8F).sounds(BlockSoundGroup.WOOL))

    @JvmField
    val DARK_RED_BANNER = add("dark_red_banner", createBannerBlock(VoidlandsDyeColors.DARK_RED))
    @JvmField
    val DARK_RED_WALL_BANNER = add("dark_red_wall_banner", createWallBannerBlock(VoidlandsDyeColors.DARK_RED, DARK_RED_BANNER))
    @JvmField
    val DARK_RED_BED = add("dark_red_bed", createBedBlock(VoidlandsDyeColors.DARK_RED))
    @JvmField
    val DARK_RED_CARPET = add("dark_red_carpet",
        ModDyedCarpetBlock(VoidlandsDyeColors.DARK_RED, BlockProperties.of(Material.WOOL, VoidlandsDyeColors.DARK_RED)
            .strength(0.1F).sounds(BlockSoundGroup.WOOL)))
    @JvmField
    val DARK_RED_CANDLE = add("dark_red_candle",
        CandleBlock(BlockProperties.of(Material.DECORATION, VoidlandsDyeColors.DARK_RED)
            .nonOpaque().strength(0.1F).sounds(BlockSoundGroup.CANDLE).luminance(CandleBlock.STATE_TO_LUMINANCE)))
    @JvmField
    val DARK_RED_CANDLE_CAKE = add("dark_red_candle_cake",
        ModCandleCakeBlock(DARK_RED_CANDLE, BlockProperties.copy(Blocks.CANDLE_CAKE)))
    @JvmField
    val DARK_RED_CONCRETE = add("dark_red_concrete",
        BlockProperties.of(Material.STONE, VoidlandsDyeColors.DARK_RED).requiresTool().strength(1.8F))
    @JvmField
    val DARK_RED_CONCRETE_POWDER = add("dark_red_concrete_powder",
        ConcretePowderBlock(DARK_RED_CONCRETE, BlockProperties.of(Material.AGGREGATE, VoidlandsDyeColors.DARK_RED)
            .strength(0.5F).sounds(BlockSoundGroup.SAND)))
    @JvmField
    val DARK_RED_GLAZED_TERRACOTTA = add("dark_red_glazed_terracotta",
        GlazedTerracottaBlock(BlockProperties.of(Material.STONE, VoidlandsDyeColors.DARK_RED)
            .requiresTool().strength(1.4F)))
    @JvmField
    val DARK_RED_SHULKER_BOX = add("dark_red_shulker_box", 
        createShulkerBoxBlock(VoidlandsDyeColors.DARK_RED))
    @JvmField
    val DARK_RED_STAINED_GLASS = add("dark_red_stained_glass",
        createStainedGlassBlock(VoidlandsDyeColors.DARK_RED))
    @JvmField
    val DARK_RED_STAINED_GLASS_PANE = add("dark_red_stained_glass_pane",
        createStainedGlassPaneBlock(VoidlandsDyeColors.DARK_RED))
    @JvmField
    val DARK_RED_TERRACOTTA = add("dark_red_terracotta", BlockProperties.of(Material.STONE, VoidlandsDyeColors.DARK_RED)
        .requiresTool().strength(1.25F, 4.2F))
    @JvmField
    val DARK_RED_WOOL = add("dark_red_wool", BlockProperties.of(Material.WOOL, VoidlandsDyeColors.DARK_RED)
        .strength(0.8F).sounds(BlockSoundGroup.WOOL))

    @JvmField
    val ASHSTONE = addCopy("ashstone", Blocks.NETHER_BRICKS)
    @JvmField
    val POLISHED_ASHSTONE = addCopy("polished_ashstone", Blocks.NETHER_BRICKS)
    @JvmField
    val ASHSTONE_BRICKS = addCopy("ashstone_bricks", Blocks.NETHER_BRICKS)
    @JvmField
    val ASHSTONE_TILES = addCopy("ashstone_tiles", Blocks.NETHER_BRICKS)
    @JvmField
    val ASHSTONE_PILLAR = add("ashstone_pillar", PillarBlock(BlockProperties.copy(Blocks.NETHER_BRICKS)))
    @JvmField
    val CHISELED_ASHSTONE_BRICKS = addCopy("chiseled_ashstone_bricks", Blocks.NETHER_BRICKS)
    @JvmField
    val CRACKED_ASHSTONE_BRICKS = addCopy("cracked_ashstone_bricks", Blocks.NETHER_BRICKS)

    @JvmField
    val ASHSTONE_SLAB = add("ashstone_slab", SlabBlock(BlockProperties.copy(Blocks.NETHER_BRICK_SLAB)))
    @JvmField
    val POLISHED_ASHSTONE_SLAB = add("polished_ashstone_slab", SlabBlock(BlockProperties.copy(Blocks.NETHER_BRICK_SLAB)))
    @JvmField
    val ASHSTONE_BRICK_SLAB = add("ashstone_brick_slab", SlabBlock(BlockProperties.copy(Blocks.NETHER_BRICK_SLAB)))
    @JvmField
    val ASHSTONE_TILE_SLAB = add("ashstone_tile_slab", SlabBlock(BlockProperties.copy(Blocks.NETHER_BRICK_SLAB)))
    @JvmField
    val CHISELED_ASHSTONE_BRICK_SLAB = add("chiseled_ashstone_brick_slab", SlabBlock(BlockProperties.copy(Blocks.NETHER_BRICK_SLAB)))

    @JvmField
    val ASHSTONE_STAIRS =
        add("ashstone_stairs", ModStairsBlock(ASHSTONE.defaultState, BlockProperties.copy(Blocks.NETHER_BRICK_STAIRS)))
    @JvmField
    val POLISHED_ASHSTONE_STAIRS =
        add("polished_ashstone_stairs", ModStairsBlock(POLISHED_ASHSTONE.defaultState, BlockProperties.copy(Blocks.NETHER_BRICK_STAIRS)))
    @JvmField
    val ASHSTONE_BRICK_STAIRS =
        add("ashstone_brick_stairs", ModStairsBlock(ASHSTONE_BRICKS.defaultState, BlockProperties.copy(Blocks.NETHER_BRICK_STAIRS)))
    @JvmField
    val ASHSTONE_TILE_STAIRS =
        add("ashstone_tile_stairs", ModStairsBlock(ASHSTONE_TILES.defaultState, BlockProperties.copy(Blocks.NETHER_BRICK_STAIRS)))
    @JvmField
    val CHISELED_ASHSTONE_BRICK_STAIRS =
        add("chiseled_ashstone_brick_stairs", ModStairsBlock(CHISELED_ASHSTONE_BRICKS.defaultState, BlockProperties.copy(Blocks.NETHER_BRICK_STAIRS)))

    @JvmField
    val ASHSTONE_WALL = add("ashstone_wall", WallBlock(BlockProperties.copy(Blocks.NETHER_BRICK_WALL)))
    @JvmField
    val POLISHED_ASHSTONE_WALL = add("polished_ashstone_wall", WallBlock(BlockProperties.copy(Blocks.NETHER_BRICK_WALL)))
    @JvmField
    val ASHSTONE_BRICK_WALL = add("ashstone_brick_wall", WallBlock(BlockProperties.copy(Blocks.NETHER_BRICK_WALL)))
    @JvmField
    val ASHSTONE_TILE_WALL = add("ashstone_tile_wall", WallBlock(BlockProperties.copy(Blocks.NETHER_BRICK_WALL)))
    @JvmField
    val CHISELED_ASHSTONE_BRICK_WALL = add("chiseled_ashstone_brick_wall", WallBlock(BlockProperties.copy(Blocks.NETHER_BRICK_WALL)))

    @JvmField
    val SHADEWOOD_LOG = addWoodlike("shadewood_log") { PillarBlock(this.sounds(BlockSoundGroup.NETHER_STEM)) }
    @JvmField
    val SHADEWOOD = addWoodlike("shadewood") { Block(this.sounds(BlockSoundGroup.NETHER_STEM)) }
    @JvmField
    val SHADEWOOD_ROOTS = add("shadewood_roots", ShadewoodRootsBlock(BlockProperties.copy(Blocks.HANGING_ROOTS)))
    @JvmField
    val SHADEWOOD_WALL = addWoodlike("shadewood_wall") { WallBlock(this.sounds(BlockSoundGroup.NETHER_STEM)) }

    @JvmField
    val MARBLE = addCopy("marble", Blocks.DEEPSLATE)
    @JvmField
    val MARBLE_SLAB = add("marble_slab", SlabBlock(BlockProperties.copy(Blocks.DEEPSLATE)))
    @JvmField
    val MARBLE_STAIRS = add("marble_stairs", ModStairsBlock(MARBLE.defaultState, BlockProperties.copy(Blocks.DEEPSLATE)))
    @JvmField
    val MARBLE_WALL = add("marble_wall", WallBlock(BlockProperties.copy(Blocks.DEEPSLATE)))
    @JvmField
    val MARBLE_ROCKS = add("marble_rocks",
        MarbleRocksBlock(BlockProperties.of(MARBLE_ROCKS_MATERIAL).nonOpaque().strength(0.2F)))

    @JvmField
    val IGNEOUS_LOG = addWoodlike("igneous_log") { PillarBlock(this.sounds(BlockSoundGroup.NETHER_STEM)) }
    @JvmField
    val IGNEOUS_WOOD = addWoodlike("igneous_wood") { Block(this.sounds(BlockSoundGroup.NETHER_STEM)) }
    @JvmField
    val IGNEOUS_PLANKS = addWoodlike("igneous_planks") { Block(this.sounds(BlockSoundGroup.NETHER_STEM)) }

    @JvmField
    val DUST_CLOUD = add("dust_cloud",
        DustCloudBlock(BlockProperties.copy(Blocks.WHITE_WOOL).noCollision().nonOpaque().strength(0.2f)))
    @JvmField
    val OSMIUM_BLOCK = addCopy("osmium_block", Blocks.IRON_BLOCK)
    @JvmField
    val VOID_BERRY_ROOTS = add("void_berry_roots", VoidBerryRootsBlock(BlockProperties.of(Material.PLANT)
        .ticksRandomly().strength(0.2F, 3.0F).sounds(BlockSoundGroup.SWEET_BERRY_BUSH).noCollision().nonOpaque()
        .emissiveLighting { state, _, _ -> state[VoidBerryRootsBlock.AGE] >= 2 }))

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

    private fun createBedBlock(color: DyeColor) =
        BedBlock(color, BlockProperties.of(Material.WOOL)
        { state ->
            if (state.get(BedBlock.PART) == BedPart.FOOT)
                color.mapColor
            else
                MapColor.WHITE_GRAY
        }.sounds(BlockSoundGroup.WOOD).strength(0.2f).nonOpaque())

    private fun createStainedGlassBlock(color: DyeColor) =
        StainedGlassBlock(color,
            BlockProperties.of(Material.GLASS, color).strength(0.3f).sounds(BlockSoundGroup.GLASS).nonOpaque()
                .allowsSpawning(ContextPredicates.Never).solidBlock(ContextPredicates.Never)
                .suffocates(ContextPredicates.Never).blockVision(ContextPredicates.Never))

    private fun createStainedGlassPaneBlock(color: DyeColor) =
        StainedGlassPaneBlock(color,
            BlockProperties.of(Material.GLASS, color).strength(0.3f).sounds(BlockSoundGroup.GLASS).nonOpaque()
                .allowsSpawning(ContextPredicates.Never).solidBlock(ContextPredicates.Never)
                .suffocates(ContextPredicates.Never).blockVision(ContextPredicates.Never))

    private fun createShulkerBoxBlock(color: DyeColor) =
        ShulkerBoxBlock(color,
            BlockProperties.of(Material.SHULKER_BOX, color).strength(2.0f).dynamicBounds().nonOpaque()
                .suffocates(ContextPredicates.ShulkerBox).blockVision(ContextPredicates.ShulkerBox))
    
    private fun createBannerBlock(color: DyeColor) =
        BannerBlock(
            color, 
            BlockProperties.of(Material.WOOD).noCollision().strength(1.0f).sounds(BlockSoundGroup.WOOD))

    private fun createWallBannerBlock(color: DyeColor, banner: BannerBlock) =
        WallBannerBlock(
            color,
            BlockProperties.of(Material.WOOD).noCollision().strength(1.0f).sounds(BlockSoundGroup.WOOD).dropsLike(banner))

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
            CANNA, CANNA_SPROUT, SHADEWOOD_ROOTS, VOID_BERRY_ROOTS)
        RenderTypeRegistry.register(RenderLayer.getTranslucent(),
            DARK_RED_STAINED_GLASS, DARK_RED_STAINED_GLASS_PANE,
            CRAYOLA_STAINED_GLASS, CRAYOLA_STAINED_GLASS_PANE,
            DUST_CLOUD)
    }
}

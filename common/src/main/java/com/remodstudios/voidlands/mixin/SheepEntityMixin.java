package com.remodstudios.voidlands.mixin;

import com.remodstudios.voidlands.Voidlands;
import com.remodstudios.voidlands.block.VoidlandsBlocks;
import com.remodstudios.voidlands.util.VoidlandsDyeColors;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.passive.SheepEntity;
import net.minecraft.item.ItemConvertible;
import net.minecraft.util.DyeColor;
import net.minecraft.util.Identifier;
import org.spongepowered.asm.mixin.*;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Map;

@Mixin(SheepEntity.class)
public abstract class SheepEntityMixin extends Entity {
    @Unique private static final Identifier CRAYOLA_SHEEP_LOOT_TABLE = Voidlands.id("entities/sheep/crayola");
    @Unique private static final Identifier DARK_RED_SHEEP_LOOT_TABLE = Voidlands.id("entities/sheep/dark_red");

    @Shadow @Final private static Map<DyeColor, ItemConvertible> DROPS;
    @Shadow @Final private static TrackedData<Byte> COLOR;

    static {
        DROPS.put(VoidlandsDyeColors.CRAYOLA, VoidlandsBlocks.CRAYOLA_WOOL);
        DROPS.put(VoidlandsDyeColors.DARK_RED, VoidlandsBlocks.DARK_RED_WOOL);
    }

    private SheepEntityMixin() {
        super(EntityType.PIG, null);
        throw new AssertionError();
    }

    /**
     * @reason Allowing >16 unique dye colors (128)
     * @author ADudeCalledLeo
     */
    @Overwrite
    public DyeColor getColor() {
        byte b = dataTracker.get(COLOR);
        return DyeColor.byId(b & 0x7F);
    }

    /**
     * @reason Allowing >16 unique dye colors (128)
     * @author ADudeCalledLeo
     */
    @Overwrite
    public void setColor(DyeColor color) {
        byte b = dataTracker.get(COLOR);
        dataTracker.set(COLOR, (byte) ((b & 0x80) | color.getId() % 0x7F));
    }

    /**
     * @reason Allowing >16 unique dye colors (128)
     * @author ADudeCalledLeo
     */
    @Overwrite
    public boolean isSheared() {
        return (dataTracker.get(COLOR) & 0x80) != 0;
    }

    /**
     * @reason Allowing >16 unique dye colors (128)
     * @author ADudeCalledLeo
     */
    @Overwrite
    public void setSheared(boolean sheared) {
        byte b = dataTracker.get(COLOR);
        dataTracker.set(COLOR, (byte) ((b & 0x7F) | (sheared ? 0x80 : 0)));
    }

    @Inject(method = "getLootTableId", at = @At("HEAD"), cancellable = true)
    public void getCustomLootTableIds(CallbackInfoReturnable<Identifier> cir) {
        DyeColor color = getColor();
        if (color == VoidlandsDyeColors.CRAYOLA)
            cir.setReturnValue(CRAYOLA_SHEEP_LOOT_TABLE);
        else if (color == VoidlandsDyeColors.DARK_RED)
            cir.setReturnValue(DARK_RED_SHEEP_LOOT_TABLE);
    }
}

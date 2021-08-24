package com.remodstudios.voidlands.datagen.template;

import com.remodstudios.voidlands.datagen.TemplatedResourceGenerator;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.UncheckedIOException;
import java.nio.file.Files;
import java.nio.file.Path;

import static com.remodstudios.voidlands.datagen.TemplatedResourceGenerator.Templates.*;

public final class SlabTemplate implements TemplatedResourceGenerator.Template {
    public SlabTemplate() throws IOException {
        blockstate = read("slab/blockstate");
        model = read("slab/model");
        modelTop = read("slab/model_top");
        lootTable = read("slab/loot_table");
    }

    private final String blockstate;
    private final String model;
    private final String modelTop;
    private final String lootTable;

    public String blockstate(String model, String fullModel) {
        return blockstate.replaceAll("MODEL_FULL", fullModel).replaceAll("MODEL", model);
    }

    public String model(String texture) {
        return model.replaceAll("TEXTURE", texture);
    }

    public String modelTop(String texture) {
        return modelTop.replaceAll("TEXTURE", texture);
    }

    public String lootTable(String id) {
        return lootTable.replaceAll("ID", id);
    }

    @Override
    public void generate(TemplatedResourceGenerator.Entry entry) {
        String slabId = entry.baseId() + "_slab";
        String fullModel = entry.blockModel();
        String texture = entry.texture();
        Path blockstate = BLOCKSTATES.resolve(slabId + ".json");
        try (BufferedWriter writer = Files.newBufferedWriter(blockstate)) {
            writer.write(blockstate(slabId, fullModel));
        } catch (IOException e) {
            throw new UncheckedIOException("Failed to write slab blockstate \"" + blockstate + "\"", e);
        }
        Path model = BLOCK_MODELS.resolve(slabId + ".json");
        try (BufferedWriter writer = Files.newBufferedWriter(model)) {
            writer.write(model(texture));
        } catch (IOException e) {
            throw new UncheckedIOException("Failed to write slab model \"" + model + "\"", e);
        }
        Path topModel = BLOCK_MODELS.resolve(slabId + "_top.json");
        try (BufferedWriter writer = Files.newBufferedWriter(topModel)) {
            writer.write(modelTop(texture));
        } catch (IOException e) {
            throw new UncheckedIOException("Failed to write top slab model \"" + topModel + "\"", e);
        }
        Path itemModel = ITEM_MODELS.resolve(slabId + ".json");
        try (BufferedWriter writer = Files.newBufferedWriter(itemModel)) {
            writer.write(blockItemModel(slabId));
        } catch (IOException e) {
            throw new UncheckedIOException("Failed to write slab item model \"" + itemModel + "\"", e);
        }
        Path lootTable = LOOT_TABLES.resolve(slabId + ".json");
        try (BufferedWriter writer = Files.newBufferedWriter(lootTable)) {
            writer.write(lootTable(slabId));
        } catch (IOException e) {
            throw new UncheckedIOException("Failed to write slab loot table \"" + lootTable + "\"", e);
        }
    }
}

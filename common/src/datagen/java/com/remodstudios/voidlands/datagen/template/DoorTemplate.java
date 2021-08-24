package com.remodstudios.voidlands.datagen.template;

import com.remodstudios.voidlands.datagen.TemplatedResourceGenerator;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.UncheckedIOException;
import java.nio.file.Files;
import java.nio.file.Path;

import static com.remodstudios.voidlands.datagen.TemplatedResourceGenerator.Templates.*;

public final class DoorTemplate implements TemplatedResourceGenerator.Template {
    public DoorTemplate() throws IOException {
        blockstate = read("door/blockstate");
        modelBottom = read("door/model_bottom");
        modelBottomHinge = read("door/model_bottom_hinge");
        modelTop = read("door/model_top");
        modelTopHinge = read("door/model_top_hinge");
        lootTable = read("door/loot_table");
    }

    private final String blockstate;
    private final String modelBottom;
    private final String modelBottomHinge;
    private final String modelTop;
    private final String modelTopHinge;
    private final String lootTable;

    public String blockstate(String model) {
        return blockstate.replaceAll("MODEL", model);
    }

    public String modelBottom(String texture) {
        return modelBottom.replaceAll("TEXTURE", texture);
    }

    public String modelBottomHinge(String texture) {
        return modelBottomHinge.replaceAll("TEXTURE", texture);
    }

    public String modelTop(String texture) {
        return modelTop.replaceAll("TEXTURE", texture);
    }

    public String modelTopHinge(String texture) {
        return modelTopHinge.replaceAll("TEXTURE", texture);
    }

    public String lootTable(String id) {
        return lootTable.replaceAll("ID", id);
    }

    @Override
    public void generate(TemplatedResourceGenerator.Entry entry) {
        String doorId = entry.baseId() + "_door";
        Path blockstate = BLOCKSTATES.resolve(doorId + ".json");
        try (BufferedWriter writer = Files.newBufferedWriter(blockstate)) {
            writer.write(blockstate(doorId));
        } catch (IOException e) {
            throw new UncheckedIOException("Failed to write door blockstate \"" + blockstate + "\"", e);
        }
        Path bottomModel = BLOCK_MODELS.resolve(doorId + "_bottom.json");
        try (BufferedWriter writer = Files.newBufferedWriter(bottomModel)) {
            writer.write(modelBottom(doorId));
        } catch (IOException e) {
            throw new UncheckedIOException("Failed to write bottom door model \"" + bottomModel + "\"", e);
        }
        Path bottomHingeModel = BLOCK_MODELS.resolve(doorId + "_bottom_hinge.json");
        try (BufferedWriter writer = Files.newBufferedWriter(bottomHingeModel)) {
            writer.write(modelBottomHinge(doorId));
        } catch (IOException e) {
            throw new UncheckedIOException("Failed to write bottom hinge door model \"" + bottomHingeModel + "\"", e);
        }
        Path topModel = BLOCK_MODELS.resolve(doorId + "_top.json");
        try (BufferedWriter writer = Files.newBufferedWriter(topModel)) {
            writer.write(modelTop(doorId));
        } catch (IOException e) {
            throw new UncheckedIOException("Failed to write top door model \"" + topModel + "\"", e);
        }
        Path topHingeModel = BLOCK_MODELS.resolve(doorId + "_top_hinge.json");
        try (BufferedWriter writer = Files.newBufferedWriter(topHingeModel)) {
            writer.write(modelTopHinge(doorId));
        } catch (IOException e) {
            throw new UncheckedIOException("Failed to write top hinge door model \"" + topHingeModel + "\"", e);
        }
        Path itemModel = ITEM_MODELS.resolve(doorId + ".json");
        try (BufferedWriter writer = Files.newBufferedWriter(itemModel)) {
            writer.write(itemModel(doorId));
        } catch (IOException e) {
            throw new UncheckedIOException("Failed to write door item model \"" + itemModel + "\"", e);
        }
        Path lootTable = LOOT_TABLES.resolve(doorId + ".json");
        try (BufferedWriter writer = Files.newBufferedWriter(lootTable)) {
            writer.write(lootTable(doorId));
        } catch (IOException e) {
            throw new UncheckedIOException("Failed to write door loot table \"" + lootTable + "\"", e);
        }
    }
}

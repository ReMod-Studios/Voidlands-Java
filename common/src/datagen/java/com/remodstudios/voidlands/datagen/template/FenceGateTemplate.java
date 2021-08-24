package com.remodstudios.voidlands.datagen.template;

import com.remodstudios.voidlands.datagen.TemplatedResourceGenerator;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.UncheckedIOException;
import java.nio.file.Files;
import java.nio.file.Path;

import static com.remodstudios.voidlands.datagen.TemplatedResourceGenerator.Templates.*;

public final class FenceGateTemplate implements TemplatedResourceGenerator.Template {
    public FenceGateTemplate() throws IOException {
        blockstate = read("fence_gate/blockstate");
        model = read("fence_gate/model");
        openModel = read("fence_gate/model_open");
        wallModel = read("fence_gate/model_wall");
        openWallModel = read("fence_gate/model_wall_open");
    }

    private final String blockstate;
    private final String model;
    private final String openModel;
    private final String wallModel;
    private final String openWallModel;

    public String blockstate(String model) {
        return blockstate.replaceAll("MODEL", model);
    }

    public String model(String texture) {
        return model.replaceAll("TEXTURE", texture);
    }

    public String openModel(String texture) {
        return openModel.replaceAll("TEXTURE", texture);
    }

    public String wallModel(String texture) {
        return wallModel.replaceAll("TEXTURE", texture);
    }

    public String openWallModel(String texture) {
        return openWallModel.replaceAll("TEXTURE", texture);
    }

    @Override
    public void generate(TemplatedResourceGenerator.Entry entry) {
        String fenceGateId = entry.baseId() + "_fence_gate";
        String texture = entry.texture();
        Path blockstate = BLOCKSTATES.resolve(fenceGateId + ".json");
        try (BufferedWriter writer = Files.newBufferedWriter(blockstate)) {
            writer.write(blockstate(fenceGateId));
        } catch (IOException e) {
            throw new UncheckedIOException("Failed to write fence gate blockstate \"" + blockstate + "\"", e);
        }
        Path model = BLOCK_MODELS.resolve(fenceGateId + ".json");
        try (BufferedWriter writer = Files.newBufferedWriter(model)) {
            writer.write(model(texture));
        } catch (IOException e) {
            throw new UncheckedIOException("Failed to write fence gate model \"" + model + "\"", e);
        }
        Path openModel = BLOCK_MODELS.resolve(fenceGateId + "_open.json");
        try (BufferedWriter writer = Files.newBufferedWriter(openModel)) {
            writer.write(openModel(texture));
        } catch (IOException e) {
            throw new UncheckedIOException("Failed to write open fence gate model \"" + openModel + "\"", e);
        }
        Path wallModel = BLOCK_MODELS.resolve(fenceGateId + "_wall.json");
        try (BufferedWriter writer = Files.newBufferedWriter(wallModel)) {
            writer.write(wallModel(texture));
        } catch (IOException e) {
            throw new UncheckedIOException("Failed to write wall fence gate model \"" + wallModel + "\"", e);
        }
        Path openWallModel = BLOCK_MODELS.resolve(fenceGateId + "_wall_open.json");
        try (BufferedWriter writer = Files.newBufferedWriter(openWallModel)) {
            writer.write(openWallModel(texture));
        } catch (IOException e) {
            throw new UncheckedIOException("Failed to write open wall fence gate model \"" + openWallModel + "\"", e);
        }
        Path itemModel = ITEM_MODELS.resolve(fenceGateId + ".json");
        try (BufferedWriter writer = Files.newBufferedWriter(itemModel)) {
            writer.write(blockItemModel(fenceGateId));
        } catch (IOException e) {
            throw new UncheckedIOException("Failed to write fence gate item model \"" + itemModel + "\"", e);
        }
    }
}

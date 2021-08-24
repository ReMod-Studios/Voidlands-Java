package com.remodstudios.voidlands.datagen.template;

import com.remodstudios.voidlands.datagen.TemplatedResourceGenerator;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.UncheckedIOException;
import java.nio.file.Files;
import java.nio.file.Path;

import static com.remodstudios.voidlands.datagen.TemplatedResourceGenerator.Templates.*;

public final class StairsTemplate implements TemplatedResourceGenerator.Template {
    public StairsTemplate() throws IOException {
        blockstate = read("stairs/blockstate");
        model = read("stairs/model");
        modelInner = read("stairs/model_inner");
        modelOuter = read("stairs/model_outer");
    }

    private final String blockstate;
    private final String model;
    private final String modelInner;
    private final String modelOuter;

    public String blockstate(String model) {
        return blockstate.replaceAll("MODEL", model);
    }

    public String model(String texture) {
        return model.replaceAll("TEXTURE", texture);
    }

    public String modelInner(String texture) {
        return modelInner.replaceAll("TEXTURE", texture);
    }

    public String modelOuter(String texture) {
        return modelOuter.replaceAll("TEXTURE", texture);
    }

    @Override
    public void generate(TemplatedResourceGenerator.Entry entry) {
        String stairsId = entry.baseId() + "_stairs";
        String texture = entry.texture();
        Path blockstate = BLOCKSTATES.resolve(stairsId + ".json");
        try (BufferedWriter writer = Files.newBufferedWriter(blockstate)) {
            writer.write(blockstate(stairsId));
        } catch (IOException e) {
            throw new UncheckedIOException("Failed to write stairs blockstate \"" + blockstate + "\"", e);
        }
        Path model = BLOCK_MODELS.resolve(stairsId + ".json");
        try (BufferedWriter writer = Files.newBufferedWriter(model)) {
            writer.write(model(texture));
        } catch (IOException e) {
            throw new UncheckedIOException("Failed to write stairs model \"" + model + "\"", e);
        }
        Path innerModel = BLOCK_MODELS.resolve(stairsId + "_inner.json");
        try (BufferedWriter writer = Files.newBufferedWriter(innerModel)) {
            writer.write(modelInner(texture));
        } catch (IOException e) {
            throw new UncheckedIOException("Failed to write stairs inner model \"" + innerModel + "\"", e);
        }
        Path outerModel = BLOCK_MODELS.resolve(stairsId + "_outer.json");
        try (BufferedWriter writer = Files.newBufferedWriter(outerModel)) {
            writer.write(modelOuter(texture));
        } catch (IOException e) {
            throw new UncheckedIOException("Failed to write stairs outer model \"" + outerModel + "\"", e);
        }
        Path itemModel = ITEM_MODELS.resolve(stairsId + ".json");
        try (BufferedWriter writer = Files.newBufferedWriter(itemModel)) {
            writer.write(blockItemModel(stairsId));
        } catch (IOException e) {
            throw new UncheckedIOException("Failed to write stairs item model \"" + itemModel + "\"", e);
        }
    }
}

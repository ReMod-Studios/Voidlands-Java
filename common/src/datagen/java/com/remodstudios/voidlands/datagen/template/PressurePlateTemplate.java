package com.remodstudios.voidlands.datagen.template;

import com.remodstudios.voidlands.datagen.TemplatedResourceGenerator;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.UncheckedIOException;
import java.nio.file.Files;
import java.nio.file.Path;

import static com.remodstudios.voidlands.datagen.TemplatedResourceGenerator.Templates.*;

public final class PressurePlateTemplate implements TemplatedResourceGenerator.Template {
    public PressurePlateTemplate() throws IOException {
        blockstate = read("pressure_plate/blockstate");
        model = read("pressure_plate/model");
        modelDown = read("pressure_plate/model_down");
    }

    private final String blockstate;
    private final String model;
    private final String modelDown;

    public String blockstate(String model) {
        return blockstate.replaceAll("MODEL", model);
    }

    public String model(String texture) {
        return model.replaceAll("TEXTURE", texture);
    }

    public String modelDown(String texture) {
        return modelDown.replaceAll("TEXTURE", texture);
    }

    @Override
    public void generate(TemplatedResourceGenerator.Entry entry) {
        String pressurePlateId = entry.baseId() + "_pressure_plate";
        String texture = entry.texture();
        Path blockstate = BLOCKSTATES.resolve(pressurePlateId + ".json");
        try (BufferedWriter writer = Files.newBufferedWriter(blockstate)) {
            writer.write(blockstate(pressurePlateId));
        } catch (IOException e) {
            throw new UncheckedIOException("Failed to write pressure plate blockstate \"" + blockstate + "\"", e);
        }
        Path model = BLOCK_MODELS.resolve(pressurePlateId + ".json");
        try (BufferedWriter writer = Files.newBufferedWriter(model)) {
            writer.write(model(texture));
        } catch (IOException e) {
            throw new UncheckedIOException("Failed to write pressure plate model \"" + model + "\"", e);
        }
        Path topModel = BLOCK_MODELS.resolve(pressurePlateId + "_down.json");
        try (BufferedWriter writer = Files.newBufferedWriter(topModel)) {
            writer.write(modelDown(texture));
        } catch (IOException e) {
            throw new UncheckedIOException("Failed to write pressed pressure plate model \"" + topModel + "\"", e);
        }
        Path itemModel = ITEM_MODELS.resolve(pressurePlateId + ".json");
        try (BufferedWriter writer = Files.newBufferedWriter(itemModel)) {
            writer.write(blockItemModel(pressurePlateId));
        } catch (IOException e) {
            throw new UncheckedIOException("Failed to write pressure plate item model \"" + itemModel + "\"", e);
        }
    }
}

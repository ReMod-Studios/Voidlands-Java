package com.remodstudios.voidlands.datagen.template;

import com.remodstudios.voidlands.datagen.TemplatedResourceGenerator;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.UncheckedIOException;
import java.nio.file.Files;
import java.nio.file.Path;

import static com.remodstudios.voidlands.datagen.TemplatedResourceGenerator.Templates.*;

public final class ButtonTemplate implements TemplatedResourceGenerator.Template {
    public ButtonTemplate() throws IOException {
        blockstate = read("button/blockstate");
        model = read("button/model");
        modelPressed = read("button/model_pressed");
        modelInventory = read("button/model_inventory");
    }
    
    private final String blockstate;
    private final String model;
    private final String modelPressed;
    private final String modelInventory;

    public String blockstate(String model) {
        return blockstate.replaceAll("MODEL", model);
    }

    public String model(String texture) {
        return model.replaceAll("TEXTURE", texture);
    }

    public String modelPressed(String texture) {
        return modelPressed.replaceAll("TEXTURE", texture);
    }

    public String modelInventory(String texture) {
        return modelInventory.replaceAll("TEXTURE", texture);
    }
    
    @Override
    public void generate(TemplatedResourceGenerator.Entry entry) {
        String buttonId = entry.baseId() + "_button";
        String texture = entry.texture();
        Path blockstate = BLOCKSTATES.resolve(buttonId + ".json");
        try (BufferedWriter writer = Files.newBufferedWriter(blockstate)) {
            writer.write(blockstate(buttonId));
        } catch (IOException e) {
            throw new UncheckedIOException("Failed to write button blockstate \"" + blockstate + "\"", e);
        }
        Path model = BLOCK_MODELS.resolve(buttonId + ".json");
        try (BufferedWriter writer = Files.newBufferedWriter(model)) {
            writer.write(model(texture));
        } catch (IOException e) {
            throw new UncheckedIOException("Failed to write button model \"" + model + "\"", e);
        }
        Path pressedModel = BLOCK_MODELS.resolve(buttonId + "_pressed.json");
        try (BufferedWriter writer = Files.newBufferedWriter(pressedModel)) {
            writer.write(modelPressed(texture));
        } catch (IOException e) {
            throw new UncheckedIOException("Failed to write pressed button model \"" + pressedModel + "\"", e);
        }
        String inventoryModelName = buttonId + "_inventory";
        Path modelInventory = BLOCK_MODELS.resolve(inventoryModelName + ".json");
        try (BufferedWriter writer = Files.newBufferedWriter(modelInventory)) {
            writer.write(modelInventory(texture));
        } catch (IOException e) {
            throw new UncheckedIOException("Failed to write inventory button model \"" + pressedModel + "\"", e);
        }
        Path itemModel = ITEM_MODELS.resolve(buttonId + ".json");
        try (BufferedWriter writer = Files.newBufferedWriter(itemModel)) {
            writer.write(blockItemModel(inventoryModelName));
        } catch (IOException e) {
            throw new UncheckedIOException("Failed to write button item model \"" + itemModel + "\"", e);
        }
    }
}

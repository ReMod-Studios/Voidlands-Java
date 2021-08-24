package com.remodstudios.voidlands.datagen.template;

import com.remodstudios.voidlands.datagen.TemplatedResourceGenerator;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.UncheckedIOException;
import java.nio.file.Files;
import java.nio.file.Path;

import static com.remodstudios.voidlands.datagen.TemplatedResourceGenerator.Templates.*;

public final class FenceTemplate implements TemplatedResourceGenerator.Template {
    public FenceTemplate() throws IOException {
        blockstate = read("fence/blockstate");
        modelInventory = read("fence/model_inventory");
        modelPost = read("fence/model_post");
        modelSide = read("fence/model_side");
        itemModel = read("fence/item_model");
    }

    private final String blockstate;
    private final String modelInventory;
    private final String modelPost;
    private final String modelSide;
    private final String itemModel;

    public String blockstate(String model) {
        return blockstate.replaceAll("MODEL", model);
    }

    public String modelInventory(String texture) {
        return modelInventory.replaceAll("TEXTURE", texture);
    }

    public String modelPost(String texture) {
        return modelPost.replaceAll("TEXTURE", texture);
    }

    public String modelSide(String texture) {
        return modelSide.replaceAll("TEXTURE", texture);
    }

    public String itemModel(String model) {
        return itemModel.replaceAll("MODEL", model);
    }

    @Override
    public void generate(TemplatedResourceGenerator.Entry entry) {
        String fenceId = entry.baseId() + "_fence";
        String texture = entry.texture();
        Path blockstate = BLOCKSTATES.resolve(fenceId + ".json");
        try (BufferedWriter writer = Files.newBufferedWriter(blockstate)) {
            writer.write(blockstate(fenceId));
        } catch (IOException e) {
            throw new UncheckedIOException("Failed to write fence blockstate \"" + blockstate + "\"", e);
        }
        Path inventoryModel = BLOCK_MODELS.resolve(fenceId + "_inventory.json");
        try (BufferedWriter writer = Files.newBufferedWriter(inventoryModel)) {
            writer.write(modelInventory(texture));
        } catch (IOException e) {
            throw new UncheckedIOException("Failed to write fence inventory model \"" + inventoryModel + "\"", e);
        }
        Path postModel = BLOCK_MODELS.resolve(fenceId + "_post.json");
        try (BufferedWriter writer = Files.newBufferedWriter(postModel)) {
            writer.write(modelPost(texture));
        } catch (IOException e) {
            throw new UncheckedIOException("Failed to write fence post model \"" + postModel + "\"", e);
        }
        Path outerModel = BLOCK_MODELS.resolve(fenceId + "_side.json");
        try (BufferedWriter writer = Files.newBufferedWriter(outerModel)) {
            writer.write(modelSide(texture));
        } catch (IOException e) {
            throw new UncheckedIOException("Failed to write fence side model \"" + outerModel + "\"", e);
        }
        Path itemModel = ITEM_MODELS.resolve(fenceId + ".json");
        try (BufferedWriter writer = Files.newBufferedWriter(itemModel)) {
            writer.write(itemModel(fenceId));
        } catch (IOException e) {
            throw new UncheckedIOException("Failed to write fence item model \"" + itemModel + "\"", e);
        }
    }
}

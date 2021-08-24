package com.remodstudios.voidlands.datagen.template;

import com.remodstudios.voidlands.datagen.TemplatedResourceGenerator;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.UncheckedIOException;
import java.nio.file.Files;
import java.nio.file.Path;

import static com.remodstudios.voidlands.datagen.TemplatedResourceGenerator.Templates.*;

public final class WallTemplate implements TemplatedResourceGenerator.Template {
    public WallTemplate() throws IOException {
        blockstate = read("wall/blockstate");
        modelInventory = read("wall/model_inventory");
        modelPost = read("wall/model_post");
        modelSide = read("wall/model_side");
        modelSideTall = read("wall/model_side_tall");
        itemModel = read("wall/item_model");
    }

    private final String blockstate;
    private final String modelInventory;
    private final String modelPost;
    private final String modelSide;
    private final String modelSideTall;
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

    public String modelSideTall(String texture) {
        return modelSideTall.replaceAll("TEXTURE", texture);
    }

    public String itemModel(String model) {
        return itemModel.replaceAll("MODEL", model);
    }

    @Override
    public void generate(TemplatedResourceGenerator.Entry entry) {
        String wallId = entry.baseId() + "_wall";
        String texture = entry.texture();
        Path blockstate = BLOCKSTATES.resolve(wallId + ".json");
        try (BufferedWriter writer = Files.newBufferedWriter(blockstate)) {
            writer.write(blockstate(wallId));
        } catch (IOException e) {
            throw new UncheckedIOException("Failed to write wall blockstate \"" + blockstate + "\"", e);
        }
        Path inventoryModel = BLOCK_MODELS.resolve(wallId + "_inventory.json");
        try (BufferedWriter writer = Files.newBufferedWriter(inventoryModel)) {
            writer.write(modelInventory(texture));
        } catch (IOException e) {
            throw new UncheckedIOException("Failed to write wall inventory model \"" + inventoryModel + "\"", e);
        }
        Path postModel = BLOCK_MODELS.resolve(wallId + "_post.json");
        try (BufferedWriter writer = Files.newBufferedWriter(postModel)) {
            writer.write(modelPost(texture));
        } catch (IOException e) {
            throw new UncheckedIOException("Failed to write wall post model \"" + postModel + "\"", e);
        }
        Path outerModel = BLOCK_MODELS.resolve(wallId + "_side.json");
        try (BufferedWriter writer = Files.newBufferedWriter(outerModel)) {
            writer.write(modelSide(texture));
        } catch (IOException e) {
            throw new UncheckedIOException("Failed to write wall side model \"" + outerModel + "\"", e);
        }
        Path tallSideModel = BLOCK_MODELS.resolve(wallId + "_side_tall.json");
        try (BufferedWriter writer = Files.newBufferedWriter(tallSideModel)) {
            writer.write(modelSideTall(texture));
        } catch (IOException e) {
            throw new UncheckedIOException("Failed to write wall tall side model \"" + outerModel + "\"", e);
        }
        Path itemModel = ITEM_MODELS.resolve(wallId + ".json");
        try (BufferedWriter writer = Files.newBufferedWriter(itemModel)) {
            writer.write(itemModel(wallId));
        } catch (IOException e) {
            throw new UncheckedIOException("Failed to write wall item model \"" + itemModel + "\"", e);
        }
    }
}

package com.remodstudios.voidlands.datagen.template;

import com.remodstudios.voidlands.datagen.TemplatedResourceGenerator;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.UncheckedIOException;
import java.nio.file.Files;
import java.nio.file.Path;

import static com.remodstudios.voidlands.datagen.TemplatedResourceGenerator.Templates.*;

public final class TrapdoorTemplate implements TemplatedResourceGenerator.Template {
    public TrapdoorTemplate() throws IOException {
        blockstate = read("trapdoor/blockstate");
        bottomModel = read("trapdoor/model_bottom");
        topModel = read("trapdoor/model_top");
        openModel = read("trapdoor/model_open");
    }

    private final String blockstate;
    private final String bottomModel;
    private final String topModel;
    private final String openModel;

    public String blockstate(String bottomModel) {
        return blockstate.replaceAll("MODEL", bottomModel);
    }

    public String bottomModel(String texture) {
        return bottomModel.replaceAll("TEXTURE", texture);
    }

    public String topModel(String texture) {
        return topModel.replaceAll("TEXTURE", texture);
    }

    public String openModel(String texture) {
        return openModel.replaceAll("TEXTURE", texture);
    }

    @Override
    public void generate(TemplatedResourceGenerator.Entry entry) {
        String trapdoorId = entry.baseId() + "_trapdoor";
        Path blockstate = BLOCKSTATES.resolve(trapdoorId + ".json");
        try (BufferedWriter writer = Files.newBufferedWriter(blockstate)) {
            writer.write(blockstate(trapdoorId));
        } catch (IOException e) {
            throw new UncheckedIOException("Failed to write trapdoor blockstate \"" + blockstate + "\"", e);
        }
        String bottomModelName = trapdoorId + "_bottom";
        Path bottomModel = BLOCK_MODELS.resolve(bottomModelName + ".json");
        try (BufferedWriter writer = Files.newBufferedWriter(bottomModel)) {
            writer.write(bottomModel(trapdoorId));
        } catch (IOException e) {
            throw new UncheckedIOException("Failed to write bottom trapdoor model \"" + bottomModel + "\"", e);
        }
        Path topModel = BLOCK_MODELS.resolve(trapdoorId + "_top.json");
        try (BufferedWriter writer = Files.newBufferedWriter(topModel)) {
            writer.write(topModel(trapdoorId));
        } catch (IOException e) {
            throw new UncheckedIOException("Failed to write top trapdoor inner model \"" + topModel + "\"", e);
        }
        Path openModel = BLOCK_MODELS.resolve(trapdoorId + "_open.json");
        try (BufferedWriter writer = Files.newBufferedWriter(openModel)) {
            writer.write(openModel(trapdoorId));
        } catch (IOException e) {
            throw new UncheckedIOException("Failed to write open trapdoor outer model \"" + openModel + "\"", e);
        }
        Path itemModel = ITEM_MODELS.resolve(trapdoorId + ".json");
        try (BufferedWriter writer = Files.newBufferedWriter(itemModel)) {
            writer.write(blockItemModel(bottomModelName));
        } catch (IOException e) {
            throw new UncheckedIOException("Failed to write trapdoor item model \"" + itemModel + "\"", e);
        }
    }
}

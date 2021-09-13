package com.remodstudios.voidlands.datagen;

import com.remodstudios.voidlands.datagen.template.*;

import java.io.*;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public final class TemplatedResourceGenerator {
    private static final String ASSETS_DIR = System.getProperty("user.dir") + "\\common\\src\\main\\resources\\assets\\voidlands";
    private static final String DATA_DIR = System.getProperty("user.dir") + "\\common\\src\\main\\resources\\data\\voidlands";
    private static final String MOD_ID = "voidlands";
    
    @FunctionalInterface
    public interface Template {
        void generate(Entry entry);
    }

    public static final class Entry {
        public static class Builder {
            private final String baseId;
            private final String blockModel;
            private final String texture;
            private final List<Template> templates;
            
            private Builder(String baseId, String blockModel, String texture) {
                this.baseId = baseId;
                this.blockModel = blockModel;
                this.texture = texture;
                templates = new ArrayList<>();
            }
            
            public Builder addTemplate(Template template) {
                templates.add(template);
                return this;
            }
            
            public Builder addTemplates(Collection<Template> templates) {
                this.templates.addAll(templates);
                return this;
            }

            public Builder addTemplates(Template... templates) {
                Collections.addAll(this.templates, templates);
                return this;
            }

            public Builder addStoneTemplates() {
                return addTemplates(Templates.SLAB, Templates.STAIRS, Templates.WALL);
            }

            public Builder addWoodenTemplates() {
                return addTemplates(Templates.SLAB, Templates.STAIRS, Templates.BUTTON, Templates.DOOR,
                        Templates.FENCE, Templates.FENCE_GATE, Templates.PRESSURE_PLATE, Templates.TRAPDOOR);
            }
            
            public Entry build() {
                return new Entry(baseId, blockModel, texture, new ArrayList<>(templates));
            }
        }

        public static Builder builder(String baseId, String blockModel, String texture) {
            return new Builder(baseId, blockModel, texture);
        }

        public static Builder builder(String baseId, String blockModel) {
            return builder(baseId, blockModel, blockModel);
        }

        public static Builder builder(String baseId) {
            return builder(baseId, baseId);
        }
        
        private final String baseId;
        private final String blockModel;
        private final String texture;
        private final List<Template> templates;

        private Entry(String baseId, String blockModel, String texture, List<Template> templates) {
            this.baseId = baseId;
            this.blockModel = blockModel;
            this.texture = texture;
            this.templates = Collections.unmodifiableList(templates);
        }

        public String baseId() {
            return baseId;
        }

        public String blockModel() {
            return blockModel;
        }

        public String texture() {
            return texture;
        }
        
        public List<Template> templates() {
            return templates;
        }
    }

    private static final Entry[] ENTRIES = new Entry[] {
            Entry.builder("ashstone").addStoneTemplates().build(),
            Entry.builder("polished_ashstone").addStoneTemplates().build(),
            Entry.builder("ashstone_brick", "ashstone_bricks").addStoneTemplates().build(),
            Entry.builder("ashstone_tile", "ashstone_tiles").addStoneTemplates().build(),
            Entry.builder("chiseled_ashstone_brick", "chiseled_ashstone_bricks").addStoneTemplates().build(),
            Entry.builder("marble").addStoneTemplates().build(),
            Entry.builder("shadewood", "shadewood_planks").addWoodenTemplates().build(),
            Entry.builder("igneous", "igneous_planks").addWoodenTemplates().build()
    };

    public static final class Templates {
        private Templates() { }
        
        public static final Template SLAB;
        public static final Template STAIRS;
        public static final Template WALL;
        public static final Template BUTTON;
        public static final Template DOOR;
        public static final Template FENCE;
        public static final Template FENCE_GATE;
        public static final Template PRESSURE_PLATE;
        public static final Template TRAPDOOR;

        public static final Path ASSETS = Paths.get(ASSETS_DIR);
        public static final Path BLOCKSTATES = ASSETS.resolve("blockstates");
        public static final Path BLOCK_MODELS = ASSETS.resolve("models/block");
        public static final Path ITEM_MODELS = ASSETS.resolve("models/item");

        public static final Path DATA = Paths.get(DATA_DIR);
        public static final Path LOOT_TABLES = DATA.resolve("loot_tables/blocks");
        
        private static final String itemModel, blockItemModel;

        private static final CharsetDecoder UTF8_DECODER = StandardCharsets.UTF_8.newDecoder();
        public static String read(String file) throws IOException {
            final ArrayList<String> lines = new ArrayList<>();
            final String fileName = "templates/" + file + ".json";
            try (InputStream is = TemplatedResourceGenerator.class.getClassLoader().getResourceAsStream(fileName)) {
                if (is == null)
                    throw new FileNotFoundException(fileName);
                try (InputStreamReader isr = new InputStreamReader(is, UTF8_DECODER);
                     BufferedReader reader = new BufferedReader(isr)) {
                    for (;;) {
                        String line = reader.readLine();
                        if (line == null)
                            break;
                        lines.add(line);
                    }
                }
            }
            return String.join("\n", lines).replaceAll("MODID", MOD_ID);
        }

        static {
            try {
                itemModel = read("item_model");
                blockItemModel = read("block_item_model");
                
                SLAB = new SlabTemplate();
                STAIRS = new StairsTemplate();
                WALL = new WallTemplate();
                BUTTON = new ButtonTemplate();
                DOOR = new DoorTemplate();
                FENCE = new FenceTemplate();
                FENCE_GATE = new FenceGateTemplate();
                PRESSURE_PLATE = new PressurePlateTemplate();
                TRAPDOOR = new TrapdoorTemplate();
            } catch (IOException e) {
                throw new UncheckedIOException("Failed to load templates", e);
            }
        }
        
        static void run() {
            for (Entry entry : ENTRIES) {
                for (Template template : entry.templates()) {
                    template.generate(entry);
                }
            }
        }

        public static String itemModel(String texture) {
            return itemModel.replaceAll("TEXTURE", texture);
        }

        public static String blockItemModel(String model) {
            return blockItemModel.replaceAll("MODEL", model);
        }
    }

    public static void main(String[] args) {
        Templates.run();
    }

}
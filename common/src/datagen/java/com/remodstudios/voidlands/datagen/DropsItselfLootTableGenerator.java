package com.remodstudios.voidlands.datagen;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public final class DropsItselfLootTableGenerator {
    private static final String DIR = "C:\\MCMods\\ReMod\\Voidlands-Java\\common\\src\\main\\resources\\data\\voidlands\\loot_tables\\blocks";

    private static final String MOD_ID = "voidlands";
    private static final String[] BLOCKS = new String[] {
            "polished_ashstone",
            "ashstone_bricks",
            "ashstone_tiles",
            "ashstone_pillar",
            "cracked_ashstone_bricks",
            "chiseled_ashstone_bricks",
            "ashstone_stairs",
            "polished_ashstone_stairs",
            "ashstone_brick_stairs",
            "ashstone_tile_stairs",
            "chiseled_ashstone_brick_stairs",
            "ashstone_wall",
            "polished_ashstone_wall",
            "ashstone_brick_wall",
            "ashstone_tile_wall",
            "chiseled_ashstone_brick_wall",
            "dark_red_carpet",
            "dark_red_concrete",
            "dark_red_concrete_powder",
            "dark_red_glazed_terracotta",
            "dark_red_terracotta",
            "dark_red_wool",
            "shadewood_log",
            "shadewood",
            "stripped_shadewood_log",
            "stripped_shadewood",
            "shadewood_sign",
            "shadewood_wall",
            "igneous_log",
            "igneous_wood",
            "stripped_igneous_log",
            "stripped_igneous_wood",
            "igneous_sign",
            "osmium_block",
            "crayola_carpet",
            "crayola_concrete",
            "crayola_concrete_powder",
            "crayola_glazed_terracotta",
            "crayola_terracotta",
            "crayola_wool",
            "dust_cloud",
            "marble",
            "marble_stairs",
            "marble_wall",
            "marble_rocks",
            "shadewood_button",
            "shadewood_fence",
            "shadewood_fence_gate",
            "shadewood_pressure_plate",
            "shadewood_stairs",
            "shadewood_trapdoor",
            "igneous_button",
            "igneous_fence",
            "igneous_fence_gate",
            "igneous_pressure_plate",
            "igneous_stairs",
            "igneous_trapdoor",
    };

    public static void main(String[] args) {
        Path path = Paths.get(DIR);

        for (String block : BLOCKS) {
            Path lootTable = path.resolve(block + ".json");

            try (BufferedWriter w = Files.newBufferedWriter(lootTable)) {
                w.write("{\n");
                w.write("  \"type\": \"minecraft:block\",\n");
                w.write("  \"pools\": [\n");
                w.write("    {\n");
                w.write("      \"rolls\": 1.0,\n");
                w.write("      \"bonus_rolls\": 0.0,\n");
                w.write("      \"entries\": [\n");
                w.write("        {\n");
                w.write("          \"type\": \"minecraft:item\",\n");
                w.write("          \"name\": \"" + MOD_ID + ":" + block + "\"\n");
                w.write("        }\n");
                w.write("      ],\n");
                w.write("      \"conditions\": [\n");
                w.write("        {\n");
                w.write("            \"condition\": \"minecraft:survives_explosion\"\n");
                w.write("        }\n");
                w.write("      ]\n");
                w.write("    }\n");
                w.write("  ]\n");
                w.write("}\n");
            } catch (IOException e) {
                throw new RuntimeException("fuced up while generating loot table for block " + block, e);
            }
        }
    }
}
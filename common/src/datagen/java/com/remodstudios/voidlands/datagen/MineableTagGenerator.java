package com.remodstudios.voidlands.datagen;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collection;
import java.util.Map;

public final class MineableTagGenerator {
    private static final String DIR = "C:\\MCMods\\ReMod\\Voidlands-Java\\common\\src\\main\\resources\\data\\minecraft\\tags\\blocks\\mineable";

    private static final String MOD_ID = "voidlands";
    private static final String[] BLOCKS = new String[] {
            "ashstone:pickaxe",
            "polished_ashstone:pickaxe",
            "ashstone_bricks:pickaxe",
            "ashstone_tiles:pickaxe",
            "ashstone_pillar:pickaxe",
            "chiseled_ashstone_bricks:pickaxe",
            "cracked_ashstone_bricks:pickaxe",
            "ashstone_slab:pickaxe",
            "polished_ashstone_slab:pickaxe",
            "ashstone_brick_slab:pickaxe",
            "ashstone_tile_slab:pickaxe",
            "chiseled_ashstone_brick_slab:pickaxe",
            "ashstone_stairs:pickaxe",
            "polished_ashstone_stairs:pickaxe",
            "ashstone_brick_stairs:pickaxe",
            "ashstone_tile_stairs:pickaxe",
            "chiseled_ashstone_brick_stairs:pickaxe",
            "ashstone_wall:pickaxe",
            "polished_ashstone_wall:pickaxe",
            "ashstone_brick_wall:pickaxe",
            "ashstone_tile_wall:pickaxe",
            "chiseled_ashstone_brick_wall:pickaxe",
            "dark_red_concrete:pickaxe",
            "dark_red_concrete_powder:shovel",
            "dark_red_glazed_terracotta:pickaxe",
            "dark_red_terracotta:pickaxe",
            "shadewood_log:axe",
            "shadewood:axe",
            "stripped_shadewood_log:axe",
            "stripped_shadewood:axe",
            "shadewood_sign:axe",
            "shadewood_roots:axe",
            "shadewood_wall:axe",
            "igneous_log:axe",
            "igneous_wood:axe",
            "stripped_igneous_log:axe",
            "stripped_igneous_wood:axe",
            "igneous_sign:axe",
            "osmium_block:pickaxe",
            "crayola_concrete:pickaxe",
            "crayola_concrete_powder:shovel",
            "crayola_glazed_terracotta:pickaxe",
            "crayola_terracotta:pickaxe",
            "marble:pickaxe",
            "marble_slab:pickaxe",
            "marble_stairs:pickaxe",
            "marble_wall:pickaxe",
            "marble_rocks:pickaxe",
            "shadewood_button:axe",
            "shadewood_door:axe",
            "shadewood_fence:axe",
            "shadewood_fence_gate:axe",
            "shadewood_pressure_plate:axe",
            "shadewood_stairs:axe",
            "shadewood_trapdoor:axe",
            "igneous_button:axe",
            "shadewood_door:axe",
            "igneous_fence:axe",
            "igneous_fence_gate:axe",
            "igneous_pressure_plate:axe",
            "igneous_stairs:axe",
            "igneous_trapdoor:axe",
    };

    public static void main(String[] args) {
        Multimap<String, String> map = ArrayListMultimap.create();
        for (String block : BLOCKS) {
            String[] split = block.split(":");
            map.put(split[1], split[0]);
        }

        Path path = Paths.get(DIR);

        for (Map.Entry<String, Collection<String>> entry : map.asMap().entrySet()) {
            Path tag = path.resolve(entry.getKey() + ".json");

            try (BufferedWriter w = Files.newBufferedWriter(tag)) {
                w.write("{\n");
                w.write("    \"replace\": \"false\",\n");
                w.write("    \"values\": [\n");
                StringBuilder sb = new StringBuilder();
                for (String value : entry.getValue()) {
                    sb.append("        \"" + MOD_ID + ":").append(value).append("\",\n");
                }
                if (sb.length() > 2) {
                    sb.setLength(sb.length() - 2);
                    sb.append('\n');
                }
                w.write(sb.toString());
                w.write("    ]\n");
                w.write("}\n");
            } catch (IOException e) {
                throw new RuntimeException("fuced up while generating mineable tag for " + entry.getKey(), e);
            }
        }
    }
}
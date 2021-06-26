# Architectury Skeleton
The absolute bare-bones (badum psh) skeleton for ReMod Studios' Java Edition mods, based on Architectury.
### Steps to make a new mod out of this template
1. Clone with the Git tool of choice (command-line, IDEA built-in, etc.)
2. Replace *all* occurences of `architectury-skeleton`, `ArchitecturySkeleton` and `archskeleton` in the project. Detailed list as follows:
    * in the last line of `settings.gradle` (`rootProject.name = "architectury-skeleton"`)
    * the `archives_base_name` property in `gradle.properties`
    * the package names for all three modules (`com.remodstudios.archskeleton.(fabric|forge)?`)
    * the main class in `common` (`ArchitecturySkeleton`), along with its `MOD_ID` field (`archskeleton`)
    * the client class in `common` (`ArchitecturySkeletonClient`)
    * the modloader specific entrypoints (`ArchitecturySkeleton(Fabric|Forge)(Client)?`)
    * entrypoints in `fabric.mod.json`
        ```json5
        {
          // ...
          "entrypoints": {
            "main": [{
              "adapter": "kotlin",
              "value": "com.remodstudios.archskeleton.fabric.ArchitecturySkeletonFabric"
            }],
            "client": [{
              "adapter": "kotlin",
              "value": "com.remodstudios.archskeleton.fabric.ArchitecturySkeletonFabricClient"
            }]
          },
          // ...
        }
        ```
      * in both `<root>/forge/src/main/resources/META-INF/mods.toml` and `<root>/fabric/src/main/resources/fabric.mod.json`
        ```toml
        [[mods]]
        modId = "archskeleton"
        ```
        ```json5
        {
          "schemaVersion": 1,
          "id": "archskeleton"
          // ...
        }
        ```
3. Update mod description and dependencies in both `<root>/forge/src/main/resources/META-INF/mods.toml` and `<root>/fabric/src/main/resources/fabric.mod.json`.

## In case if the mod is broken, but you can't quite figure it out
Around 90% of the issues I asked in Architectury's Discord server are related to, if not directly caused by, incorrectly generated run configs.
So before asking, please refresh your run configs by:
   * IDEA: deleting all run configs and re-sync Gradle.
   * Other IDEs: TODO
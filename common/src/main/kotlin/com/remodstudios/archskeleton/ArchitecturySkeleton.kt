package com.remodstudios.archskeleton

import net.minecraft.util.Identifier

object ArchitecturySkeleton {
    const val MOD_ID = "archskeleton"

    fun init() {
        printHelloWorld()
        ArchSkeletonItems.register()
    }

    fun id(path: String): Identifier {
        return Identifier(MOD_ID, path)
    }
}
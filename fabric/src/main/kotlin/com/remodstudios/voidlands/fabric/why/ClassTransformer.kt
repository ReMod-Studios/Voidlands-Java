package com.remodstudios.voidlands.fabric.why

fun interface ClassTransformer {
    fun transform(name: String, transformedName: String, bytes: ByteArray): ByteArray
}
@file:JvmName("PlatformInterface")
package com.remodstudios.voidlands

import dev.architectury.injectables.annotations.ExpectPlatform
import net.minecraft.util.DyeColor

@ExpectPlatform
fun printHelloWorld(): Unit = throw AssertionError()

@ExpectPlatform
fun getCrayolaDyeColor(): DyeColor = throw AssertionError()

@ExpectPlatform
fun getDarkRedDyeColor(): DyeColor = throw AssertionError()
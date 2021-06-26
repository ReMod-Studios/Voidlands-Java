@file:JvmName("PlatformInterface")
package com.remodstudios.voidlands

import dev.architectury.injectables.annotations.ExpectPlatform

@ExpectPlatform
fun printHelloWorld(): Unit = throw AssertionError()
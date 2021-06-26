@file:JvmName("PlatformInterface")
package com.remodstudios.archskeleton

import dev.architectury.injectables.annotations.ExpectPlatform

@ExpectPlatform
fun printHelloWorld(): Unit = throw AssertionError()
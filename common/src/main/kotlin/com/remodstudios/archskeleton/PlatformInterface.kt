@file:JvmName("PlatformInterface")
package com.remodstudios.archskeleton

import me.shedaniel.architectury.annotations.ExpectPlatform

@ExpectPlatform
fun printHelloWorld(): Unit = throw AssertionError()
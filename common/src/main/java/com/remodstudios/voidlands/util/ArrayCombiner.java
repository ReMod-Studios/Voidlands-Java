package com.remodstudios.voidlands.util;

import java.util.function.IntFunction;

public final class ArrayCombiner {
    private ArrayCombiner() { }

    public static <T> T[] combine(T[] a1, T[] a2, IntFunction<T[]> arrayConstructor) {
        T[] a3 = arrayConstructor.apply(a1.length + a2.length);
        System.arraycopy(a1, 0, a3, 0, a1.length);
        System.arraycopy(a2, 0, a3, a1.length, a2.length);
        return a3;
    }
}

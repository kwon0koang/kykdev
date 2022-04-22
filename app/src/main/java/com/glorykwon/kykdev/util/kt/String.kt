package com.glorykwon.kykdev.util.kt

fun String.toKykInt() = toIntOrNull() ?: 0
fun String.toKykLong() = toLongOrNull() ?: 0L
fun String.toKykFloat() = toFloatOrNull()?: 0.0f
fun String.toKykDouble() = toDoubleOrNull() ?: 0.0
fun String.toKykBigDecimal() = toBigDecimalOrNull() ?: 0.toBigDecimal()
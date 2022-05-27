package com.glorykwon.kykdev.util.kt

fun String.toSafeInt() = toIntOrNull() ?: 0
fun String.toSafeLong() = toLongOrNull() ?: 0L
fun String.toSafeFloat() = toFloatOrNull() ?: 0.0f
fun String.toSafeDouble() = toDoubleOrNull() ?: 0.0
fun String.toSafeBigDecimal() = toBigDecimalOrNull() ?: 0.toBigDecimal()

fun String.getYNtoBool() = if(this == "Y") true else false
package com.glorykwon.kykdev.util.kt

import android.os.Bundle

inline fun <reified T : Any?> Bundle.getSafeSerializable(key: String): T? {
    val value = this.getSerializable(key)
    return if (value is T) {
        value
    } else {
        null
    }
}

inline fun <reified T : Any?> Bundle.getSafeSerializable(key: String, default: T): T {
    val value = this.getSerializable(key)
    return if (value is T) {
        value
    } else {
        default
    }
}
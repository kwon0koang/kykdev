package com.glorykwon.kykdev.util.kt

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

/**
 * repeatOnLifecycle : OnStart()/onStop() 에서 collect/cancel 할 필요없이 Lifecycle에 맞게 알아서 collect/cancel을 반복
 */
fun LifecycleOwner.launchRepeatOnStarted(block: suspend CoroutineScope.() -> Unit) {
    this.lifecycleScope.launch {
        lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED, block)
    }
}
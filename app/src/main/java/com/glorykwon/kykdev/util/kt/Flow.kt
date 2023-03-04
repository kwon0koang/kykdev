package com.glorykwon.kykdev.util.kt

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext
import kotlin.coroutines.EmptyCoroutineContext


/**
 * repeatOnLifecycle : OnStart()/onStop() 에서 collect/cancel 할 필요없이 Lifecycle에 맞게 알아서 collect/cancel을 반복
 */
fun <T> Flow<T>.collectLatestWithLifeCycle(
    lifecycleOwner: LifecycleOwner,
    dispatcher: CoroutineContext = EmptyCoroutineContext,
    block: suspend (T) -> Unit
) {
    lifecycleOwner.lifecycleScope.launch(dispatcher) {
        lifecycleOwner.lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
            collectLatest(block)
        }
    }
}
package com.glorykwon.kykdev

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class FlowTest {

    @Before
    fun setUp() {
    }

    @Test
    fun `flow test`() = runBlocking {
        val numFlow = flow {
            for(i in 1..5) {
                emit(i)
                delay(800)
            }
        }

        numFlow
            .onEach {
                println("1 / onEach / $it / ${Thread.currentThread().name}")
                if(it == 4) throw Exception("my exception")
            }
            .onEach {
                println("2 / onEach / $it / ${Thread.currentThread().name}")
            }
            .onCompletion { e ->
                if(e != null)
                    println("4 / onCompletion / exception / $e / ${Thread.currentThread().name}")
                else
                    println("4 / onCompletion / $e / ${Thread.currentThread().name}")
            }
            .catch { e ->
                e.printStackTrace()
            }
            .collect {
                println("3 / collect / $it / ${Thread.currentThread().name}")
            }
    }
}

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
        val testFlow = flow {
            listOf("A", "B", "C", "D", "E").forEach {
                emit(it)
                delay(500)
            }
        }

        testFlow
//            .take(2)        // 값을 두개만 받겠음
            .onEach {
                println("1 / onEach / $it / ${Thread.currentThread().name}")
                if(it == "D") throw Exception("test exception")
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
                println("4 / catch / exception / $e / ${Thread.currentThread().name}")
            }
            .collect {
                println("3 / collect / $it / ${Thread.currentThread().name}")
            }
    }

    @Test
    fun `flow zip and combine test`(): Unit = runBlocking {
        val nums = (1..3).asFlow().onEach { delay(100L) }
        val strs = flowOf("일", "이", "삼").onEach { delay(200L) }

        println("zip =======================")
        nums.zip(strs) { a, b -> "${a}은(는) $b" }
            .collect { println(it) }

        println("combine =======================")
        nums.combine(strs) { a, b -> "${a}은(는) $b" }
            .collect { println(it) }
    }

}

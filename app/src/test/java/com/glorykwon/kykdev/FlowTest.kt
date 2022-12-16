package com.glorykwon.kykdev

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import kotlin.system.measureTimeMillis

class FlowTest {

    @Before
    fun setUp() {
    }

    @Test
    fun `test flow`() = runBlocking {
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
    fun `test flow zip and combine`(): Unit = runBlocking {
        val nums = (1..3).asFlow().onEach { delay(90L) }
        val strs = flowOf("A", "B", "C").onEach { delay(110L) }

        println("zip =======================")
        nums.zip(strs) { a, b -> "${a} - $b" }
            .collect { println(it) }

        println("combine =======================")
        nums.combine(strs) { a, b -> "${a} - $b" }
            .collect { println(it) }
    }

    @Test
    fun `tset flatMap`(): Unit = runBlocking {
        val nums = (1..3).asFlow().onEach { delay(90L) }
        val strs = flowOf("A", "B", "C").onEach { delay(110L) }

        println("flatMapConcat =======================")
        nums.flatMapConcat { num ->
            strs.map { str -> "$num - $str" }
        }.onEach { println(it) }.collect()

        println("flatMapMerge =======================")
        nums.flatMapMerge { num ->
            strs.map { str -> "$num - $str" }
        }.onEach { println(it) }.collect()

        println("flatMapLatest =======================")
        nums.flatMapLatest { num ->
            strs.map { str -> "$num - $str" }
        }.onEach { println(it) }.collect()
    }

}

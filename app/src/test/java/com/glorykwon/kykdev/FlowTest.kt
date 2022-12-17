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

//        1 / onEach / A / Test worker @coroutine#1
//        2 / onEach / A / Test worker @coroutine#1
//        3 / collect / A / Test worker @coroutine#1
//        1 / onEach / B / Test worker @coroutine#1
//        2 / onEach / B / Test worker @coroutine#1
//        3 / collect / B / Test worker @coroutine#1
//        1 / onEach / C / Test worker @coroutine#1
//        2 / onEach / C / Test worker @coroutine#1
//        3 / collect / C / Test worker @coroutine#1
//        1 / onEach / D / Test worker @coroutine#1
//        4 / onCompletion / exception / java.lang.Exception: test exception / Test worker @coroutine#1
//        4 / catch / exception / java.lang.Exception: test exception / Test worker @coroutine#1
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

//        zip =======================
//        1 - A
//        2 - B
//        3 - C
//        combine =======================
//        1 - A
//        2 - A
//        2 - B
//        3 - B
//        3 - C
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

//        flatMapConcat =======================
//        1 - A
//        1 - B
//        1 - C
//        2 - A
//        2 - B
//        2 - C
//        3 - A
//        3 - B
//        3 - C
//        flatMapMerge =======================
//        1 - A
//        2 - A
//        1 - B
//        3 - A
//        2 - B
//        1 - C
//        3 - B
//        2 - C
//        3 - C
//        flatMapLatest =======================
//        3 - A
//        3 - B
//        3 - C
    }

}

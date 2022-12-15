package com.glorykwon.kykdev

import kotlinx.coroutines.*
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test


/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class CoroutineTest {

    @Before
    fun setUp() {
    }

    @Test
    fun `test Dispatchers Main immediate`() = runBlocking {
        println("${coroutineContext}")
        launch {
            println("1 / ${coroutineContext}")
        }
        println("2")
        println("========================================")
        launch(Dispatchers.Main) {
            println("3 / ${coroutineContext}")
        }
        println("4")
        println("========================================")
        launch(Dispatchers.Main.immediate) {
            println("5 / ${coroutineContext}")
        }
        println("6")
        println("========================================")
        launch(Dispatchers.Main.immediate) {
            launch {
                println("7 / ${coroutineContext}")
            }
            println("8")
        }
        println("9")
        launch(Dispatchers.IO) {
            launch(Dispatchers.Main.immediate) {
                println("10 / ${coroutineContext}")
            }
            println("11")
        }
        println("12")
        delay(300)
    }

}

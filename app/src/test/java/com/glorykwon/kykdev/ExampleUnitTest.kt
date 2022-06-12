package com.glorykwon.kykdev

import com.glorykwon.kykdev.common.api.RetrofitTestApiService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import kotlin.system.measureTimeMillis


/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {

    @Before
    fun setUp() {
    }

    @Test
    fun addition_isCorrect() {
        println("kyk test")
        assertEquals(4, 2 + 2)
    }

    @Test
    fun retrofitTestApiServiceTest(): Unit = runBlocking {
        measureTimeMillis {
            listOf(async(Dispatchers.IO) {
                RetrofitTestApiService.getInstance().searchByUserId(1)
            }, async(Dispatchers.IO) {
                RetrofitTestApiService.getInstance().searchByUserId(2)
            }, async(Dispatchers.IO) {
                RetrofitTestApiService.getInstance().searchByUserId(3)
            }).awaitAll()
        }.also {
            println("total time : $it")
        }
    }

    public inline fun <T, R> T.let2(block: (T) -> R): R {
//        contract {
//            callsInPlace(block, InvocationKind.EXACTLY_ONCE)
//        }
        return block(this)
    }

    public inline fun <T> T.also2(block: (T) -> Unit): T {
//        contract {
//            callsInPlace(block, InvocationKind.EXACTLY_ONCE)
//        }
        block(this)
        return this
    }
    public inline fun <T, R> T.run2(block: T.() -> R): R {
//        contract {
//            callsInPlace(block, InvocationKind.EXACTLY_ONCE)
//        }
        return block()
    }
    public inline fun <T, R> with2(receiver: T, block: T.() -> R): R {
//        contract {
//            callsInPlace(block, InvocationKind.EXACTLY_ONCE)
//        }
        return receiver.block()
    }


}

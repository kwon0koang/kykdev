package com.glorykwon.kykdev

import org.junit.Test

import org.junit.Assert.*
import timber.log.Timber
import java.io.File
import kotlin.contracts.InvocationKind
import kotlin.contracts.contract

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun addition_isCorrect() {
        System.out.println("kyk test")
        assertEquals(4, 2 + 2)
    }

    @Test
    fun test() {

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

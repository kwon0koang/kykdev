package com.glorykwon.kykdev

import com.glorykwon.kykdev.common.api.RetrofitTestApiService
import io.reactivex.rxjava3.kotlin.subscribeBy
import io.reactivex.rxjava3.schedulers.Schedulers
import io.reactivex.rxjava3.subjects.BehaviorSubject
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import java.util.concurrent.TimeUnit
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

    @Test
    fun `rxjava test`() = runBlocking {
        val behaviorSubject = BehaviorSubject.create<Int>()

        behaviorSubject
            .doOnSubscribe {
                println("0 / doOnSubscribe / ${Thread.currentThread().name}")
            }
            .doOnNext {
                println("1 / doOnNext / $it / ${Thread.currentThread().name}")
            }
            .subscribeOn(Schedulers.io())
            .doOnNext {
                println("2 / doOnNext / $it / ${Thread.currentThread().name}")
//                if(it == 4) throw Exception("my exception")
            }
            .debounce(1000, TimeUnit.MILLISECONDS)
            .observeOn(Schedulers.computation())
            .doOnComplete {
                println("4 / doOnComplete / ${Thread.currentThread().name}")
            }
            .doOnError {
                println("4 / doOnError / $it / ${Thread.currentThread().name}")
            }
            .subscribeBy {
                println("3 / subscribeBy / $it / ${Thread.currentThread().name}")
            }

        for(i in 1..5) {
            behaviorSubject.onNext(i)
            delay(800)
        }

        delay(2000)
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

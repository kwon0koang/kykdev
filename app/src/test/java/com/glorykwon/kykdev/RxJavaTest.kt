package com.glorykwon.kykdev

import io.reactivex.rxjava3.kotlin.subscribeBy
import io.reactivex.rxjava3.schedulers.Schedulers
import io.reactivex.rxjava3.subjects.BehaviorSubject
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import java.util.concurrent.TimeUnit

class RxJavaTest {

    @Before
    fun setUp() {
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

}

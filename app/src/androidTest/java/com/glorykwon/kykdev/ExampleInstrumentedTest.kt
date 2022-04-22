package com.glorykwon.kykdev

import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.ext.junit.runners.AndroidJUnit4
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*
import org.junit.Before
import timber.log.Timber

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class ExampleInstrumentedTest {

    @Before
    fun setUp() {
        Timber.plant(Timber.DebugTree())
    }

    @Test
    fun useAppContext() {
        // Context of the app under test.
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        assertEquals("com.glorykwon.kykdev", appContext.packageName)
    }

    @Test
    fun test() = runBlocking {
        listOf(async {
            for(i in 1..10){
                Timber.d("kyk / a / $i")
                delay(100)
            }
        }, async {
            for(i in 1..10){
                Timber.d("kyk / b / $i")
                delay(100)
            }
        }, async {
            for(i in 1..10){
                Timber.d("kyk / c / $i")
                delay(100)
            }
        }).awaitAll()

        for(i in 1..10){
            Timber.d("kyk / d / $i")
            delay(100)
        }

        Timber.d("kyk / 555555555555555555555555555")
    }

    @Test
    fun asdf(){
        Timber.d("asdfasdfasdfasdfasdf")
    }

}
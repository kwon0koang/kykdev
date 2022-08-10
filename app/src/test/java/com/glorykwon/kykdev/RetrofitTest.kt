package com.glorykwon.kykdev

import com.glorykwon.kykdev.common.api.RetrofitTestApiService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import kotlin.system.measureTimeMillis

class RetrofitTest {

    @Before
    fun setUp() {
    }

    @Test
    fun `retrofit test`(): Unit = runBlocking {
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

}

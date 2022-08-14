package com.glorykwon.kykdev

import kotlinx.coroutines.*
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.channels.ReceiveChannel
import kotlinx.coroutines.channels.consumeEach
import kotlinx.coroutines.channels.produce
import org.junit.Before
import org.junit.Test

class ChannelTest {

    @Before
    fun setUp() {
    }

    /**
     * 1씩 증가
     */
    fun CoroutineScope.produceNumbers() = produce<Int> {
        var x = 1
        while (true) {
            send(x++)
        }
    }

    /**
     * 홀수 필터링
     */
    fun CoroutineScope.filterOdd(numbers: ReceiveChannel<Int>): ReceiveChannel<String> = produce {
        for (i in numbers) {
            if (i % 2 == 1) {
                send("${i}!")
            }
        }
    }

    @Test
    fun `channel test`(): Unit = runBlocking {
        val numbers = produceNumbers()
        val oddNumbers = filterOdd(numbers)

        repeat(10) {
            println(oddNumbers.receive())
        }
        println("완료")
        coroutineContext.cancelChildren()
    }

}

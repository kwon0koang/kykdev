package com.glorykwon.kykdev

import kotlinx.coroutines.*
import kotlinx.coroutines.channels.*
import org.junit.Before
import org.junit.Test

class ChannelTest {

    @Before
    fun setUp() {
    }

    /**
     * 1씩 증가
     */
    fun CoroutineScope.produceNumbers(interval: Long = 0) = produce<Int> {
        var x = 1
        while (true) {
            send(x++)
            delay(interval)
        }
    }

    /**
     * from부터 2씩 증가
     */
//    suspend fun produceNumbers(channel: SendChannel<Int>, from: Int, interval: Long) {
//        var x = from
//        while (true) {
//            channel.send(x)
//            x += 2
//            delay(interval)
//        }
//    }
    fun CoroutineScope.produceNumbers(channel: SendChannel<Int>, from: Int, interval: Long) = produce<Int> {
        var x = from
        while (true) {
            channel.send(x)
            x += 2
            delay(interval)
        }
    }

    /**
     * 홀수 필터링
     */
    fun CoroutineScope.produceFilterOdd(numbers: ReceiveChannel<Int>): ReceiveChannel<String> = produce {
        for (i in numbers) {
            if (i % 2 == 1) {
                send("${i}!")
            }
        }
    }

    fun CoroutineScope.receiveNumbers(id: Int, numbers: ReceiveChannel<Int>) = launch {
        numbers.consumeEach {
            println("${id}가 ${it}을 받았습니다.")
        }
    }

    @Test
    fun `channel test`(): Unit = runBlocking {
        val numbers = produceNumbers()

        repeat(10) {
            println(numbers.receive())
        }
        println("완료")
        coroutineContext.cancelChildren()
    }

    @Test
    fun `channel test (홀수 필터링)`(): Unit = runBlocking {
        val numbers = produceNumbers()
        val oddNumbers = produceFilterOdd(numbers)

        repeat(10) {
            println(oddNumbers.receive())
        }
        println("완료")
        coroutineContext.cancelChildren()
    }

    /**
     * fan-out : 여러 코루틴이 동시에 채널을 구독
     */
    @Test
    fun `fan-out test`(): Unit = runBlocking {
        val producer = produceNumbers(interval = 100)
        repeat (5) { id ->
            receiveNumbers(id, producer)
        }
        delay(1000L)
        producer.cancel()
    }

    /**
     * fan-in : fan-out과 반대로 생산자가 많은 것
     */
    @Test
    fun `fan-in test`(): Unit = runBlocking {
        val channel = Channel<Int>()
//        launch {
//            produceNumbers(channel, 1, 100L)
//        }
//        launch {
//            produceNumbers(channel, 2, 150L)
//        }
        produceNumbers(channel, 1, 100L)
        produceNumbers(channel, 2, 150L)
        receiveNumbers(id = 9999, channel)

        delay(1000L)
        coroutineContext.cancelChildren()
    }

}

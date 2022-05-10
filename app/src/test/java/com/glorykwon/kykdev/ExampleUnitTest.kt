package com.glorykwon.kykdev

import com.glorykwon.kykdev.api.RetrofitTestApiService
import com.glorykwon.kykdev.dto.TestDto
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.`when`
import org.mockito.Mockito.mock
import kotlin.system.measureTimeMillis


/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {

//    @Mock
//    private lateinit var mTestDto: TestDto

    @Before
    fun setUp() {
//        MockitoAnnotations.openMocks(this)
//        val lifecycleOwner = Mockito.mock(LifecycleOwner::class.java)
//        val lifecycle = LifecycleRegistry(lifecycleOwner)
    }

    @Test
    fun addition_isCorrect() {
        println("kyk test")
        assertEquals(4, 2 + 2)
    }

    @Test
    fun retrofitTestApiServiceTest() = runBlocking {
        measureTimeMillis {
            listOf(async {
                RetrofitTestApiService.getInstance().searchByUserId(1)
            }, async {
                RetrofitTestApiService.getInstance().searchByUserId(2)
            }, async {
                RetrofitTestApiService.getInstance().searchByUserId(3)
            }).awaitAll()
        }.let {
            println("total time : $it")
        }
    }

    @Test
    fun mockTest() {
        val testDto = mock(TestDto::class.java)

        `when`(testDto.value01).thenReturn("value 01")
        `when`(testDto.value02).thenReturn("value 02")
        `when`(testDto.value03).thenReturn("value 03")

        assertEquals("value 01", testDto.value01)
        assertEquals("value 02", testDto.value02)
        assertEquals("value 03", testDto.value03)

        //Error
//        assertEquals("value 03", testDto.value01)
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

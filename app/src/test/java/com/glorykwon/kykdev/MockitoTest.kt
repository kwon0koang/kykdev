package com.glorykwon.kykdev

import com.glorykwon.kykdev.common.dto.TestDto
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito


/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class MockitoTest {

    @Before
    fun setUp() {
    }

    @Test
    fun `mock test`(){
        val testDto = Mockito.mock(TestDto::class.java)
        assertEquals(true, testDto != null)

        Mockito.`when`(testDto.value01).thenReturn("it is value 01")
        Mockito.`when`(testDto.value02).thenReturn("it is value 02")
        Mockito.`when`(testDto.value03).thenReturn("it is value 03")

        assertEquals("it is value 01", testDto.value01)
        assertEquals("it is value 02", testDto.value02)
        assertEquals("it is value 03", testDto.value03)

        //error
        assertEquals("it is value 04", testDto.value03)
    }

    @Test
    fun `mock test 02`(){
        val testDto = Mockito.mock(TestDto::class.java)
        assertEquals(true, testDto != null)

        Mockito.`when`(testDto.value01).thenReturn("it is value 01")
        Mockito.`when`(testDto.value02).thenReturn("it is value 02")
        Mockito.`when`(testDto.value03).thenThrow(RuntimeException("it is exception"))

        assertEquals("it is value 01", testDto.value01)
        assertEquals("it is value 02", testDto.value02)
        assertEquals("it is value 03", testDto.value03)
    }

}

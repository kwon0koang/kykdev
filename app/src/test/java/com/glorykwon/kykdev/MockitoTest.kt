package com.glorykwon.kykdev

import com.glorykwon.kykdev.common.dto.TestDto
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.*

class MockitoTest {

    @Before
    fun setUp() {
    }

    @Test
    fun `mock test`(){
        val testDto = mock(TestDto::class.java)
        assertEquals(true, testDto != null)

        `when`(testDto.value01).thenReturn("it is value 01")
        `when`(testDto.value02).thenReturn("it is value 02")
        `when`(testDto.value03).thenReturn("it is value 03")

        assertEquals("it is value 01", testDto.value01)
        assertEquals("it is value 02", testDto.value02)
        assertEquals("it is value 03", testDto.value03)

        //error
//        assertEquals("it is value 04", testDto.value03)
    }

    @Test
    fun `mock test 02`(){
        val testDto = mock(TestDto::class.java)
        assertEquals(true, testDto != null)

        `when`(testDto.value01).thenReturn("it is value 01")
        `when`(testDto.value02).thenReturn("it is value 02")
        `when`(testDto.value03).thenThrow(RuntimeException("it is exception"))

        assertEquals("it is value 01", testDto.value01)
        assertEquals("it is value 02", testDto.value02)

        //error
//        assertEquals("it is value 03", testDto.value03)
    }

}

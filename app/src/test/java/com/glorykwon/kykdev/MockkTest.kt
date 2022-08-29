package com.glorykwon.kykdev

import com.glorykwon.kykdev.common.dto.TestDto
import com.google.common.truth.Truth.assertThat
import io.mockk.every
import io.mockk.mockk
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class MockkTest {

    @Before
    fun setUp() {
    }

    @Test
    fun `mock test`(){
        val testDto = mockk<TestDto>()
        assertEquals(true, testDto != null)

        every { testDto.value01 } returns "it is value 01"
        every { testDto.value02 } returns "it is value 02"
        every { testDto.value03 } returns "it is value 03"

        assertThat(testDto.value01).isEqualTo("it is value 01")
        assertThat(testDto.value02).isEqualTo("it is value 02")
        assertThat(testDto.value03).isEqualTo("it is value 03")
    }

}

package com.glorykwon.kykdev

import com.glorykwon.kykdev.common.api.RetrofitTestApiService
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.runBlocking
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Before
import org.junit.Test

class MockWebServerTest {

    private val MOCK_SERVER_PORT = 7777

    private var mMockServer = MockWebServer()

    @Before
    fun setup() {
        mMockServer.start(MOCK_SERVER_PORT)

        val mockUrl = mMockServer.url("/v1/").toString()
        println("mockUrl = $mockUrl")       // mockUrl = http://localhost:7777/v1/
        RetrofitTestApiService.setTestBaseUrl(mockUrl)
    }

    @After
    fun tearDown() {
        mMockServer.shutdown()
    }

    @Test
    fun `test retrofit by using MockWebServer`(): Unit = runBlocking {
        val response = MockResponse()
            .addHeader("Content-Type", "application/json; charset=utf-8")
            .addHeader("Cache-Control", "no-cache")
            .addHeader("Cookie", "cookie1=some-value; cookie2=other-value")
            .setResponseCode(200)
//            .setBody("result 1")
//            .setBody("result 2")
//            .setBody("result 3")
//            .setBody("{ result: \"success\" }")
            .setBody("[\n" +
                    "  {\n" +
                    "    \"userId\": 1,\n" +
                    "    \"id\": 1,\n" +
                    "    \"title\": \"MockWebServer test 1\",\n" +
                    "    \"completed\": false\n" +
                    "  }, \n" +
                    "  {\n" +
                    "    \"userId\": 1,\n" +
                    "    \"id\": 2,\n" +
                    "    \"title\": \"MockWebServer test 2\",\n" +
                    "    \"completed\": false\n" +
                    "  },\n" +
                    "  {\n" +
                    "    \"userId\": 1,\n" +
                    "    \"id\": 3,\n" +
                    "    \"title\": \"MockWebServer test 3\",\n" +
                    "    \"completed\": false\n" +
                    "  }\n" +
                    "]")
        mMockServer.enqueue(response)

        val testDtos = RetrofitTestApiService.getInstance().searchByUserId(1)

        println(testDtos)

        assertThat(testDtos).isNotEmpty()
        assertThat(testDtos.size).isEqualTo(3)
        assertThat(testDtos.get(0).title).isEqualTo("MockWebServer test 1")
    }

}

package com.glorykwon.kykdev.ui

import android.content.Intent
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingSource
import androidx.recyclerview.widget.RecyclerView
import androidx.test.core.app.ActivityScenario
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.doesNotExist
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import com.glorykwon.kykdev.R
import com.glorykwon.kykdev.common.api.RetrofitTestApiService
import com.glorykwon.kykdev.common.api.RetrofitTestDto
import com.glorykwon.kykdev.common.dto.TestDto
import com.glorykwon.kykdev.repository.TestRepository
import com.glorykwon.kykdev.util.kt.toSafeInt
import com.google.common.truth.Truth.assertThat
import io.mockk.coEvery
import io.mockk.every
import io.mockk.mockk
import io.mockk.mockkObject
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import org.hamcrest.core.StringContains.containsString
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
@LargeTest
class MainActivityTest {

    private var mActivityScenario: ActivityScenario<MainActivity>? = null

    @Before
    fun setup() {
        val intent = Intent(ApplicationProvider.getApplicationContext(), MainActivity::class.java).apply {
            putExtra("testName123", "testValue123")
        }
        mActivityScenario = ActivityScenario.launch(intent)
    }

    @After
    fun close() {
        mActivityScenario?.close()
    }

    @Test
    fun testIntent() {
        mActivityScenario?.onActivity { activity ->
            val testValue = activity.intent.getStringExtra("testName123")
            assertThat(testValue).isNotEqualTo("testValue11111111111")
            assertThat(testValue).isEqualTo("testValue123")
        }
    }

    @Test
    fun testRxJava(): Unit = runBlocking {
        onView(withId(R.id.btn_rxjava_test))
            .perform(click())

        onView(withId(R.id.cb_on_network_subject01))
            .perform(click())
        onView(withId(R.id.cb_on_network_subject02))
            .perform(click())

        onView(withId(R.id.txt_combine_latest_test))
            .check(matches(withText(containsString("false"))))

        onView(withId(R.id.cb_on_network_subject03))
            .perform(click())

        onView(withId(R.id.txt_combine_latest_test))
            .check(matches(withText(containsString("true"))))
    }

    @Test
    fun testBackButton() {
        // rxjava test 버튼 누르고 버튼 없어졌는지 체크
        onView(withId(R.id.btn_rxjava_test))
            .perform(click())
            .check(doesNotExist())

        // 아래 텍스트 보여지는지 체크 > 백버튼 실행 > 해당 텍스트 없어졌는지 체크
        onView(withId(R.id.txt_combine_latest_test))
            .check(matches(withText(containsString("is all check ?"))))
            .perform(ViewActions.pressBack())
            .check(doesNotExist())

        // rxjava test 버튼 보이는지 체크
        onView(withId(R.id.btn_rxjava_test))
            .check(matches(isDisplayed()))
    }

    @Test
    fun testMockBasic() {
        val testDto = mockk<TestDto>()
        assertThat(testDto).isNotNull()

        every { testDto.value01 } returns "it is value 01"
        every { testDto.value02 } returns "it is value 02"
        every { testDto.value03 } returns "it is value 03"

        assertThat(testDto.value01).isEqualTo("it is value 01")
        assertThat(testDto.value02).isEqualTo("it is value 02")
        assertThat(testDto.value03).isEqualTo("it is value 03")
    }

    @Test
    fun testMockRetrofit() = runBlocking {
        mockkObject(RetrofitTestApiService)
        coEvery { RetrofitTestApiService.getInstance().searchByUserId(any()) } returns mutableListOf<RetrofitTestDto>().apply {
            add(RetrofitTestDto("aa", "", "", false))
            add(RetrofitTestDto("bb", "", "", false))
            add(RetrofitTestDto("cc", "", "", false))
            add(RetrofitTestDto("dd", "", "", false))
            add(RetrofitTestDto("ee", "", "", false))
        }
        onView(withId(R.id.btn_retrofit_test))
            .perform(click())

        delay(3000)
    }

    @Test
    fun testRecyclerView(): Unit = runBlocking {
        // data mocking
        mockkObject(TestRepository)
        every { TestRepository.getPagingData() } returns Pager(
            config = PagingConfig(pageSize = 100, enablePlaceholders = false)
            , pagingSourceFactory = { object : PagingSource<String, RetrofitTestDto>() {
                override suspend fun load(params: LoadParams<String>): LoadResult<String, RetrofitTestDto> {

                    val currentKey = params.key ?: ""
                    val nextKey = (currentKey.toSafeInt()+1).toString()

                    val dataList = mutableListOf<RetrofitTestDto>().apply {
                        add(RetrofitTestDto("aaaaaaaaaaaaaaaa", "aaaaaaaaaaaaaaaa", "aaaaaaaaaaaaaaaa", false))
                        add(RetrofitTestDto("bbbbbbbbbbbbbbbb", "bbbbbbbbbbbbbbbb", "bbbbbbbbbbbbbbbb", false))
                        add(RetrofitTestDto("cccccccccccccccc", "cccccccccccccccc", "cccccccccccccccc", false))
                        add(RetrofitTestDto("dddddddddddddddd", "dddddddddddddddd", "dddddddddddddddd", false))
                        add(RetrofitTestDto("eeeeeeeeeeeeeeee", "eeeeeeeeeeeeeeee", "eeeeeeeeeeeeeeee", false))
                    }

                    return LoadResult.Page(
                        data = dataList,
                        prevKey = null,
                        nextKey = if (nextKey.isNullOrEmpty()) null else nextKey)
                }
            } }
        ).flow

        onView(withId(R.id.btn_flow_test))
            .perform(click())

        delay(1000)

        // 100 번 아이템까지 스크롤 순회
        (1..100).forEach { position ->
            onView(withId(R.id.rv_flow_test))
                .perform(
                    RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(position))
        }

        // bbb 문자 포함하는 0번째 아이템 클릭
        onView(withId(R.id.rv_flow_test))
            .perform(
                RecyclerViewActions.actionOnItem<RecyclerView.ViewHolder>(
                    hasDescendant(withText(containsString("bbb"))), click()
                ).atPosition(0))

        delay(1000)

    }

}
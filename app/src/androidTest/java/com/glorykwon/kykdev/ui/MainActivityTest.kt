package com.glorykwon.kykdev.ui

import android.content.Intent
import androidx.lifecycle.*
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
import com.glorykwon.kykdev.common.Event
import com.glorykwon.kykdev.common.NetworkResult
import com.glorykwon.kykdev.common.dto.TestDto
import com.google.common.truth.Truth.assertThat
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import org.hamcrest.core.StringContains.containsString
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import timber.log.Timber

@RunWith(AndroidJUnit4::class)
@LargeTest
class MainActivityTest {

    private var mActivityScenario: ActivityScenario<MainActivity>? = null

    private var mockViewModel: MainViewModel? = null

    private var mockRetrofitTest = MutableLiveData<Event<NetworkResult>>()

    @Before
    fun setup() {
        val intent = Intent(ApplicationProvider.getApplicationContext(), MainActivity::class.java).apply {
            putExtra("testName123", "testValue123")
        }
        mActivityScenario = ActivityScenario.launch(intent)

        // mock viewmodel
        mockViewModel = mockk(relaxed = true) {
            every { retrofitTest } returns mockRetrofitTest
            every { retrofitTest() } returns testCallRetrofit()
        }

        mActivityScenario?.onActivity {
//            mockRetrofitTest!!.observeForever {
//                Timber.d("mRetrofitTest observed")
//            }
            mockViewModel?.retrofitTest?.observeForever {
                Timber.d("mRetrofitTest observed")
            }
        }

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
    fun testRecyclerView(): Unit = runBlocking {
        onView(withId(R.id.btn_flow_test))
            .perform(click())

        delay(1000)

        // 100 번 아이템까지 스크롤 순회
        (1..100).forEach { position ->
            onView(withId(R.id.rv_flow_test))
                .perform(
                    RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(position))
        }

        // totam 문자 포함하는 0번째 아이템 클릭
        onView(withId(R.id.rv_flow_test))
            .perform(
                RecyclerViewActions.actionOnItem<RecyclerView.ViewHolder>(
                    hasDescendant(withText(containsString("totam"))), click()
                ).atPosition(0))

        delay(1000)

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
        onView(withId(R.id.btn_retrofit_test))
            .perform(click())

        delay(3000)
    }

    fun testCallRetrofit() {
        Timber.d("testCallRetrofit()")
        mockRetrofitTest.postValue(Event(NetworkResult.Success()))
    }

}
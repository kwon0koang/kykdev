package com.glorykwon.kykdev.ui

import android.content.Intent
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
import com.google.common.truth.Truth
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

    var mActivityScenario: ActivityScenario<MainActivity>? = null
//    @Rule
//    var activityScenarioRule: ActivityScenarioRule<MainActivity> =
//        ActivityScenarioRule(MainActivity::class.java)

    @Before
    fun setup() {
        val intent = Intent(ApplicationProvider.getApplicationContext(), MainActivity::class.java).apply {
            putExtra("testName123", "testValue123")
        }
        mActivityScenario = ActivityScenario.launch<MainActivity>(intent)
    }

    @After
    fun close() {
        mActivityScenario?.close()
    }

    @Test
    fun testIntent() {
        mActivityScenario?.onActivity { activity ->
            val testValue = activity.intent.getStringExtra("testName123")
            Truth.assertThat(testValue).isNotEqualTo("testValue11111111111")
            Truth.assertThat(testValue).isEqualTo("testValue123")
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

}
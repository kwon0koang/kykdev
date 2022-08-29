package com.glorykwon.kykdev.ui

import androidx.test.core.app.ActivityScenario
import androidx.test.core.app.launchActivity
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import com.glorykwon.kykdev.R
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

    @Before
    fun setup() {
        mActivityScenario = launchActivity()
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
        onView(withId(R.id.btn_rxjava_test))
            .perform(click())

        onView(isRoot())
            .perform(ViewActions.pressBack())

        onView(withId(R.id.btn_rxjava_test))
            .check(matches(isDisplayed()))
    }

    @After
    fun close() {
        mActivityScenario?.close()
    }

}
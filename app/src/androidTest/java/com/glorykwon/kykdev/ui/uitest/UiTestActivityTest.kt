package com.glorykwon.kykdev.ui.uitest

import androidx.test.core.app.ActivityScenario
import androidx.test.core.app.launchActivity
import androidx.test.espresso.Espresso.*
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.*
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.glorykwon.kykdev.R
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

/**
 * 단일 앱의 UI 테스트
 * https://developer.android.com/training/testing/ui-testing/espresso-testing?hl=ko#additional-resources-codelabs
 * https://developer.android.com/codelabs/advanced-android-kotlin-training-testing-test-doubles?hl=ko#9
 *
 * 프래그먼트 테스트
 * https://developer.android.com/guide/fragments/test?hl=ko
 */
@RunWith(AndroidJUnit4::class)
class UiTestActivityTest {

    private val TEST_TEXT = "ui test !!!!!!!!!!!"

    var mActivityScenario: ActivityScenario<UiTestActivity>? = null

    @Before
    fun setup() {
        mActivityScenario = launchActivity()
    }

    @Test
    fun uiTest(): Unit = runBlocking {
//        val activityScenario = ActivityScenario.launch(UiTestActivity::class.java)

        onView(withId(R.id.et_ui_test))
            .perform(typeText(TEST_TEXT))

        onView(withId(R.id.btn_edittext_to_textview))
            .perform(click())

        delay(100)

        onView(withId(R.id.txt_ui_test))
            .check(matches(withText(TEST_TEXT)))
    }

    @After
    fun close() {
        mActivityScenario?.close()
    }

}
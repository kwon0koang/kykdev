package com.glorykwon.kykdev

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.fragment.app.FragmentFactory
import androidx.lifecycle.Lifecycle
import androidx.navigation.fragment.NavHostFragment
import androidx.test.core.app.ActivityScenario
import androidx.test.core.app.launchActivity
import androidx.test.espresso.Espresso.*
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.*
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.activityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.glorykwon.kykdev.ui.MainActivity
import com.glorykwon.kykdev.ui.MainFragment
import com.glorykwon.kykdev.ui.uitest.UiTestActivity
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.Robolectric
import org.robolectric.RuntimeEnvironment
import org.robolectric.Shadows
import org.robolectric.annotation.Config
import org.robolectric.shadows.ShadowLog

/**
 * Robolectric은 버전 4.0부터 Android의 공식 테스트 라이브러리와 완벽하게 호환됩니다.
 * 따라서 이러한 새로운 API를 사용해 보고 피드백을 제공하는 것이 좋습니다.
 * 어느 시점에서 Robolectric 등가물은 더 이상 사용되지 않으며 제거됩니다.
 * AndroidX Test API를 사용하면 Robolectric 테스트를 작성하든 계측 테스트를 작성하든 상관없이 동일한 Android 개념에 대해 학습할 API 세트 하나만 있으면 개발자의 인지 부하가 ​​줄어듭니다.
 * 또한 테스트를 보다 쉽게 ​​이식할 수 있고 향후 계획과 호환될 수 있습니다.
 */
@Config(instrumentedPackages = ["androidx.loader.content"])     // required to access final members on androidx.loader.content.ModernAsyncTask
@RunWith(AndroidJUnit4::class)
class RobolectricTest {

    private val TEST_TEXT = "ui test !!!!!!!!!!!"

    @Before
    fun setUp() {
        // print log
        ShadowLog.stream = System.out
    }

    @Test
    fun `액티비티 라이프사이클 테스트 (robolectric)`() {
        // given
        val controller = Robolectric.buildActivity(MainActivity::class.java).setup()

        // when
        controller.pause().stop().destroy()

        // then
        val activity = controller.get()
        assertThat(activity.lifecycle.currentState).isEqualTo(Lifecycle.State.DESTROYED)
    }

    @Test
    fun `액티비티 라이프사이클 테스트 (androidX)`() {
        // given
        val scenario = launchActivity<MainActivity>()

        // when
        scenario.moveToState(Lifecycle.State.CREATED)

        // then
        // ActivityScenario.onActivity : activity에 안전하게 액세스
        scenario.onActivity { activity ->
            assertThat(activity.lifecycle.currentState).isEqualTo(Lifecycle.State.CREATED)
        }
    }

    @Test
    fun `화면 이동 잘 되는지 테스트 (robolectric)`() {
        // given
        val controller = Robolectric.buildActivity(MainActivity::class.java).setup()
        val activity = controller.get()
        val mainFragment = (activity.supportFragmentManager.fragments.first() as NavHostFragment).childFragmentManager.fragments.first()

        // when
        val btnUiTest = mainFragment.view!!.findViewById<Button>(R.id.btn_ui_test)
        btnUiTest.performClick()

        // then
        val expectedIntent = Intent(activity, UiTestActivity::class.java)
        val actual = Shadows.shadowOf(RuntimeEnvironment.getApplication()).nextStartedActivity
        println("expectedIntent.component : ${expectedIntent.component}")
        println("actual.component : ${actual.component}")
        assertThat(expectedIntent.component).isEqualTo(actual.component)
    }

    @Test
    fun `에스프레소 테스트`() {
        onView(withText("Hello world!")).check(matches(isDisplayed()))
    }

//    @Test
//    fun `버튼을 누르면 EditText 값이 TextView에 잘 셋팅되는지 확인`() {
//        val scenario = launchActivity<UiTestActivity>()
//
//        // when
////        scenario.recreate()
//        onView(withId(R.id.et_ui_test)).perform(typeText(TEST_TEXT))
//        onView(withId(R.id.btn_edittext_to_textview)).perform(click())
//
//        // then
//        onView(withId(R.id.txt_ui_test)).check(matches(withText(TEST_TEXT)))
//    }

//    @Test
//    fun `프래그먼트 테스트 (Non-graphical fragment)`() {
//        val fragmentFactory = FragmentFactory()
//        val fragmentScenario = launchFragment<MainFragment>()
//
//        fragmentScenario.onFragment { fragment ->
//            onView(withId(R.id.btn_ui_test))
//                .perform(typeText(TEST_TEXT))
//                .check(matches(withText("1234")))
//        }
//    }

}

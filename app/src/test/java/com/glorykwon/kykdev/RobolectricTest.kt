package com.glorykwon.kykdev

import android.content.Intent
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.lifecycle.Lifecycle
import androidx.navigation.fragment.NavHostFragment
import androidx.test.core.app.ActivityScenario
import com.glorykwon.kykdev.ui.MainActivity
import com.glorykwon.kykdev.ui.uitest.UiTestActivity
import com.google.common.truth.Truth.assertThat
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.Robolectric
import org.robolectric.RobolectricTestRunner
import org.robolectric.RuntimeEnvironment
import org.robolectric.Shadows
import org.robolectric.shadows.ShadowLog

@RunWith(RobolectricTestRunner::class)
class RobolectricTest {

    private val TEST_TEXT = "ui test !!!!!!!!!!!"

    @Before
    fun setUp() {
        // print log
        ShadowLog.stream = System.out
    }

    @Test
    fun `robolectric intent test`() {
        // given
        val activity = Robolectric.buildActivity(MainActivity::class.java).create().resume().get()
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
    fun `robolectric ui test`() {
        // given
        val activity = Robolectric.buildActivity(UiTestActivity::class.java).create().resume().get()

        // when
        val etUiTest = activity.findViewById<EditText>(R.id.et_ui_test)
        etUiTest.setText(TEST_TEXT)

        val btnCopy = activity.findViewById<Button>(R.id.btn_edittext_to_textview)
        btnCopy.performClick()

        // then
        val txtUiTest = activity.findViewById<TextView>(R.id.txt_ui_test)
        println(txtUiTest.text.toString())
        assertThat(txtUiTest.text.toString()).isEqualTo(TEST_TEXT)
    }

    @Test
    fun `robolectric ui test 02`() {
        // given
        val activityScenario = ActivityScenario.launch(UiTestActivity::class.java)

        // when
        activityScenario.moveToState(Lifecycle.State.CREATED)

        // then
        activityScenario.onActivity { activity ->
            val txtUiTest = activity.findViewById<TextView>(R.id.txt_ui_test)
            txtUiTest.setText(TEST_TEXT)
            assertThat(txtUiTest.text.toString()).isEqualTo(TEST_TEXT)
        }
    }

}

package com.glorykwon.kykdev.ui.uitest

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.glorykwon.kykdev.common.NetworkResult
import com.google.common.truth.Truth
import org.junit.Rule
import org.junit.Test

class UiTestViewModelTest {

    // JUnit Rule
    // 테스트 전에 세팅을 할 때 설정하는 규칙과 같은 것
    // InstantTaskExecutorRule은 백그라운드에서 작업하는 Architecture Component들을 동일 스레드에서 작업할 수 있게 만드는 규칙
    // 동기적으로, 반복적으로 테스트를 수행할 수 있게
    @get:Rule
    var mInstantTaskExecutorRule = InstantTaskExecutorRule()

    @Test
    fun `test viewmodel livedata`() {
        val viewModel = UiTestViewModel()
        val observer = Observer<Any> {}
        try {
            viewModel.testEvent.observeForever(observer)

            viewModel.testEvent(true)
            Truth.assertThat(viewModel.testEvent.value).isEqualTo(NetworkResult.Success(true))
            viewModel.testEvent(false)
            Truth.assertThat(viewModel.testEvent.value).isEqualTo(NetworkResult.Success(false))
            viewModel.testEvent(123)
            Truth.assertThat(viewModel.testEvent.value).isEqualTo(NetworkResult.Success(123))
            viewModel.testEvent("abc")
            Truth.assertThat(viewModel.testEvent.value).isEqualTo(NetworkResult.Success("abc"))

        } finally {
            viewModel.testEvent.removeObserver(observer)
        }
    }


}
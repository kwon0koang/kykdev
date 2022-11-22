package com.glorykwon.kykdev

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.glorykwon.kykdev.common.Event
import com.glorykwon.kykdev.common.NetworkResult
import com.glorykwon.kykdev.common.api.RetrofitTestApiService
import com.glorykwon.kykdev.common.api.RetrofitTestDto
import com.glorykwon.kykdev.ui.MainViewModel
import com.glorykwon.kykdev.ui.uitest.UiTestViewModel
import com.google.common.truth.Truth
import io.mockk.coEvery
import io.mockk.mockkObject
import org.junit.Rule
import org.junit.Test

class ViewModelTest {

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

    @Test
    fun `test viewmodel (mock retrofit)`() {
        mockkObject(RetrofitTestApiService)
        coEvery { RetrofitTestApiService.getInstance().searchByUserId(any()) } returns mutableListOf<RetrofitTestDto>().apply {
            add(RetrofitTestDto("aa", "", "", false))
            add(RetrofitTestDto("bb", "", "", false))
            add(RetrofitTestDto("cc", "", "", false))
            add(RetrofitTestDto("dd", "", "", false))
            add(RetrofitTestDto("ee", "", "", false))
        }

        val viewModel = MainViewModel()
        val observer = Observer<Event<NetworkResult>> {
            println("result : ${it.peekContent()}")
        }
        try {
            viewModel.retrofitTest.observeForever(observer)

            viewModel.retrofitTest()

            val results = (viewModel.retrofitTest.value?.getContentIfNotHandled() as? NetworkResult.Success)?.data as List<RetrofitTestDto>

            Truth.assertThat(results.size).isEqualTo(15)
            Truth.assertThat(results.filter { it.userId == "aa" }.size).isEqualTo(3)
            Truth.assertThat(results.filter { it.userId == "bb" }.size).isEqualTo(3)
            Truth.assertThat(results.filter { it.userId == "cc" }.size).isEqualTo(3)
            Truth.assertThat(results.filter { it.userId == "dd" }.size).isEqualTo(3)
            Truth.assertThat(results.filter { it.userId == "ee" }.size).isEqualTo(3)
        } finally {
            viewModel.retrofitTest.removeObserver(observer)
        }
    }


}
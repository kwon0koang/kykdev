package com.glorykwon.kykdev.ui.roomtest

import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

//@RunWith(RobolectricTestRunner::class)
class RoomTestViewModelTest {

    // todo kyk

    // JUnit Rule
    // 테스트 전에 세팅을 할 때 설정하는 규칙과 같은 것
    // InstantTaskExecutorRule은 백그라운드에서 작업하는 Architecture Component들을 동일 스레드에서 작업할 수 있게 만드는 규칙
    // 동기적으로, 반복적으로 테스트를 수행할 수 있게
//    @get:Rule
//    var mInstantTaskExecutorRule = InstantTaskExecutorRule()

    @Before
    fun setUp() {
//        val mockedApplication = mock(MainApplication::class.java)
//        val context = ApplicationProvider.getApplicationContext<Context>()
//        `when`(mockedApplication.getApplicationContext()).thenReturn(context)
    }

    @Test
    fun `test fetchAllItems`() = runBlocking {
//        val viewModel = RoomTestViewModel()
//        val observer = Observer<Any> {}
//        try {
//            viewModel.fetchAllItems.observeForever(observer)
//            viewModel.fetchAllItems()
//            println("fetchAllItems : ${viewModel.fetchAllItems.value}")
//        } finally {
//            viewModel.fetchAllItems.removeObserver(observer)
//        }
//
//        delay(1000)
    }

}
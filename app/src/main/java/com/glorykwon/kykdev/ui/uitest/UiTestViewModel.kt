package com.glorykwon.kykdev.ui.uitest

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import androidx.lifecycle.switchMap
import com.glorykwon.kykdev.common.NetworkResult

class UiTestViewModel : ViewModel() {

    private val _testEvent = MutableLiveData<Any>()
    fun testEvent(any: Any){ _testEvent.value = any }
    val testEvent = _testEvent.switchMap {
        liveData {
            emit(NetworkResult.Loading())
            try {
                emit(NetworkResult.Success(it))
            } catch (e: Exception) {
                e.printStackTrace()
                emit(NetworkResult.Error(e))
            }
        }
    }

}
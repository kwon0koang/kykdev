package com.glorykwon.kykdev.ui.flowtest

import androidx.lifecycle.*
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch

class FlowTestViewModel : ViewModel() {

    private val _countLiveData = MutableLiveData<Any>()
    fun startCountLiveData(){ _countLiveData.value = Any() }
    val countLiveData = _countLiveData.switchMap {
        liveData {
            (1..10).forEach {
                emit(it)
                delay(1000)
            }
        }
    }

    private val _countFlow = MutableSharedFlow<Any>()
    val countFlow = _countFlow.asSharedFlow()
    fun startCountFlow() = viewModelScope.launch {
        with(_countFlow) {
            (1..10).forEach {
                emit(it)
                delay(1000)
            }
        }
    }
//    val todoFlow = _todoFlow.flatMapLatest {
//        flow {
//            emit(NetworkResult.Loading())
//            try {
//                var result = false
//                emit(NetworkResult.Success(result))
//            } catch (e: Exception) {
//                emit(NetworkResult.Error(e))
//            }
//        }
//    }

}
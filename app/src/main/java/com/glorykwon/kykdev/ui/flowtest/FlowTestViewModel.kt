package com.glorykwon.kykdev.ui.flowtest

import androidx.lifecycle.*
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
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

    private val _status1 = MutableStateFlow("")
    val status1 = _status1.asStateFlow()
    fun updateStatus1(value: String) = viewModelScope.launch {
        _status1.emit(value)
    }

    private val _status2 = MutableStateFlow("")
    val status2 = _status2.asStateFlow()
    fun updateStatus2(value: String) = viewModelScope.launch {
        _status2.emit(value)
    }


}
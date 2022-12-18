package com.glorykwon.kykdev.ui.flowtest

import androidx.lifecycle.*
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import timber.log.Timber

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

    private val _status1 = MutableStateFlow("default status 1")
    val status1 = _status1.asStateFlow()
    fun updateStatus1(status: String) = viewModelScope.launch {
        delay(1000)
        val result = "emit status1 : $status"
        Timber.d(result)
        _status1.emit(result)
    }

    private val _status2 = MutableStateFlow("default status 2")
    val status2 = _status2.asStateFlow()
    fun updateStatus2(status: String) = viewModelScope.launch {
        delay(1000)
        val result = "emit status2 : $status"
        Timber.d(result)
        _status2.emit(result)
    }

    private val _networkProcessValue = MutableStateFlow("default value")
    val networkProcessValue = _networkProcessValue.asStateFlow()
    fun callNetworkProcess() = viewModelScope.launch {
        _isLoading.emit(true)
        delay(1000)
        Timber.d("emit networkProcessValue : ${mockNetworkProcessValue}")
        _networkProcessValue.emit("networkProcessValue : ${mockNetworkProcessValue++}")
        _isLoading.emit(false)
    }
    var mockNetworkProcessValue = 1

    private val _isLoading = MutableStateFlow(false)
    val isLoading = _isLoading.asStateFlow()

}
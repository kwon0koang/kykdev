package com.glorykwon.kykdev.ui.flowtest

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.liveData
import androidx.lifecycle.switchMap
import androidx.lifecycle.viewModelScope
import com.glorykwon.kykdev.ui.BaseViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import timber.log.Timber
import kotlin.random.Random

class FlowTestViewModel : BaseViewModel() {

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

    /**
     * ==============================================================================================================================================================================================================================================
     */
    //    private val _eventFlow = MutableSharedFlow<NetworkResult>()
//    val eventFlow = _eventFlow.asSharedFlow()

    private val _networkProcessValue01 = MutableStateFlow("value 01")
    val networkProcessValue01 = _networkProcessValue01.asStateFlow()
    private val _networkProcessValue02 = MutableStateFlow("value 02")
    val networkProcessValue02 = _networkProcessValue02.asStateFlow()
    private val _networkProcessValue03 = MutableStateFlow("value 03")
    val networkProcessValue03 = _networkProcessValue03.asStateFlow()

    fun callNetworkProcess() = viewModelScope.launch {
        loading()
        try {
            _networkProcessValue01.emit("value 01 : ${Random.nextInt(1, 1001)}")
            delay(1000)
            _networkProcessValue02.emit("value 02 : ${Random.nextInt(1, 1001)}")
            delay(1000)
            _networkProcessValue03.emit("value 03 : ${Random.nextInt(1, 1001)}")
        } catch (e: Exception) {
            _errorFlow.emit(e.message)
        }
        finished()
    }

    fun funcDelay3000() = viewModelScope.launch {
        loading()
        delay(3000)
        finished()
    }

    fun funcDelay1000() = viewModelScope.launch {
        loading()
        delay(1000)
        finished()
    }

}
package com.glorykwon.kykdev.ui.flowtest

import androidx.lifecycle.*
import com.glorykwon.kykdev.common.NetworkResult
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import timber.log.Timber
import java.lang.Exception
import kotlin.random.Random

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

    /**
     * ==============================================================================================================================================================================================================================================
     */

    private val _isLoading = MutableStateFlow(false)
    val isLoading = _isLoading.asStateFlow()

    private val _errorFlow = MutableSharedFlow<String?>()
    val errorFlow = _errorFlow.asSharedFlow()

    //    private val _eventFlow = MutableSharedFlow<NetworkResult>()
//    val eventFlow = _eventFlow.asSharedFlow()

    private val _networkProcessValue01 = MutableStateFlow("value 01")
    val networkProcessValue01 = _networkProcessValue01.asStateFlow()
    private val _networkProcessValue02 = MutableStateFlow("value 02")
    val networkProcessValue02 = _networkProcessValue02.asStateFlow()
    private val _networkProcessValue03 = MutableStateFlow("value 03")
    val networkProcessValue03 = _networkProcessValue03.asStateFlow()

    fun callNetworkProcess() = viewModelScope.launch {
        _isLoading.emit(true)
        try {
            _networkProcessValue01.emit("value 01 : ${Random.nextInt(1, 1001)}")
            delay(1000)
            _networkProcessValue02.emit("value 02 : ${Random.nextInt(1, 1001)}")
            delay(1000)
            _networkProcessValue03.emit("value 03 : ${Random.nextInt(1, 1001)}")
        } catch (e: Exception) {
            _errorFlow.emit(e.message)
        }
        _isLoading.emit(false)
    }


}
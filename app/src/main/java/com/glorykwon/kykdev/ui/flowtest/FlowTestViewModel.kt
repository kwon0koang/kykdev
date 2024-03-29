package com.glorykwon.kykdev.ui.flowtest

import androidx.lifecycle.*
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import timber.log.Timber
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

    /**
     * ==============================================================================================================================================================================================================================================
     */
    private val _loadingFlow = MutableSharedFlow<Boolean>()
    val loadingFlow = _loadingFlow.asSharedFlow()

    private val _errorFlow = MutableSharedFlow<Exception>()
    val errorFlow = _errorFlow.asSharedFlow()

    private val _networkProcessValue01 = MutableStateFlow("value 01")
    val networkProcessValue01 = _networkProcessValue01.asStateFlow()
    private val _networkProcessValue02 = MutableStateFlow("value 02")
    val networkProcessValue02 = _networkProcessValue02.asStateFlow()
    private val _networkProcessValue03 = MutableStateFlow("value 03")
    val networkProcessValue03 = _networkProcessValue03.asStateFlow()

    private var _callApi: Job? = null
    fun callApi(isLoading: Boolean = true) {
        _callApi?.cancel()
        _callApi = myLaunch(isLoading) {
            _networkProcessValue01.emit("value 01 : ${Random.nextInt(1, 1001)}")
            Timber.d("emit value 01")
            delay(1000)
            _networkProcessValue02.emit("value 02 : ${Random.nextInt(1, 1001)}")
            Timber.d("emit value 02")
            delay(1000)
            _networkProcessValue03.emit("value 03 : ${Random.nextInt(1, 1001)}")
            Timber.d("emit value 03")
        }
    }

    fun myLaunch(isLoading: Boolean = true, block: suspend () -> Unit) = viewModelScope.launch {
        try {
            if (isLoading) {
                _loadingFlow.emit(true)
            }
            block()
        } catch (e: Exception) {
            _errorFlow.emit(e)
        } finally {
            if (isLoading) {
                _loadingFlow.emit(false)
            }
        }
    }


}
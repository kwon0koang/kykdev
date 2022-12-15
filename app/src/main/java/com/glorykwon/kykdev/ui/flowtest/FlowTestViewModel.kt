package com.glorykwon.kykdev.ui.flowtest

import androidx.lifecycle.*
import com.glorykwon.kykdev.common.NetworkResult
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch

class FlowTestViewModel : ViewModel() {

    /**
     * todo
     */
    private val _todoLiveData = MutableLiveData<Any>()
    fun todoLiveData(){ _todoLiveData.value = Any() }
    val todoLiveData = _todoLiveData.switchMap {
        liveData {
            emit(NetworkResult.Loading())
            try {
                var result = false
                emit(NetworkResult.Success(result))
            } catch (e: Exception) {
                e.printStackTrace()
                emit(NetworkResult.Error(e))
            }
        }
    }

    private val _todoFlow = MutableSharedFlow<NetworkResult>()
    val todoFlow = _todoFlow.asSharedFlow()
    fun todoFlow() = viewModelScope.launch {
        with(_todoFlow) {
            emit(NetworkResult.Loading())
            try {
                var result = false
                emit(NetworkResult.Success(result))
            } catch (e: Exception) {
                e.printStackTrace()
                emit(NetworkResult.Error(e))
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
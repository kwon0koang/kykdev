package com.glorykwon.kykdev.template

import androidx.lifecycle.*
import com.glorykwon.kykdev.common.NetworkResult
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.launch

class TemplateViewModel : ViewModel() {

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

    private val _todoFlow = MutableSharedFlow<Any>()
    fun todoFlow() = viewModelScope.launch {
        _todoFlow.emit(Any())
    }
    val todoFlow = _todoFlow.flatMapLatest {
        flowOf(it)
    }

}
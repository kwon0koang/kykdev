package com.glorykwon.kykdev.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

open class BaseViewModel : ViewModel() {

    private val _loadingCnt = MutableStateFlow(0)
    val isShowProgress = _loadingCnt.flatMapLatest { loadingCnt ->
        flow {
            emit(loadingCnt > 0)
        }
    }

    protected val _errorFlow = MutableSharedFlow<String?>()
    val errorFlow = _errorFlow.asSharedFlow()

    fun loading() = viewModelScope.launch {
        _loadingCnt.emit(++_loadingCnt.value)
    }
    fun finished() = viewModelScope.launch {
        _loadingCnt.emit(--_loadingCnt.value)
    }

}
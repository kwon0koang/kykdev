package com.glorykwon.kykdev.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Job
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

    /**
     * API 로딩 중
     */
    protected fun loading() = viewModelScope.launch {
        _loadingCnt.emit(++_loadingCnt.value)
    }

    /**
     * API 끝
     */
    protected fun finished() = viewModelScope.launch {
        if (_loadingCnt.value <= 0) return@launch
        _loadingCnt.emit(--_loadingCnt.value)
    }

    protected fun Job?.myLaunch(block: suspend () -> Unit) = this.myLaunchWithProgress(isShowProgress = false, block)
    protected fun Job?.myLaunchWithProgress(isShowProgress: Boolean = true, block: suspend () -> Unit): Job {
        this?.cancel()      // 기존 Job cancel
        finished()          // 기존 Job cancel
        return viewModelScope.launch {
            if (isShowProgress) {
                loading()
            }

            try {
                block()
            } catch (e: Exception) {
                finished()
                _errorFlow.emit(e.message)
            }

            if (isShowProgress) {
                finished()
            }
        }
    }
}
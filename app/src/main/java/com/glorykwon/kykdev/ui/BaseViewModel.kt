package com.glorykwon.kykdev.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

open class BaseViewModel : ViewModel() {

    private val _isLoading = MutableStateFlow(0)
    val isLoading = _isLoading.asStateFlow()

    protected val _errorFlow = MutableSharedFlow<String?>()
    val errorFlow = _errorFlow.asSharedFlow()

    fun loading() = viewModelScope.launch {
        _isLoading.emit(++_isLoading.value)
    }
    fun finished() = viewModelScope.launch {
        _isLoading.emit(--_isLoading.value)
    }

}
package com.glorykwon.kykdev.template

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import androidx.lifecycle.switchMap
import com.glorykwon.kykdev.common.NetworkResult
import kotlinx.coroutines.Dispatchers

class TemplateViewModel : ViewModel() {

    /**
     * todo
     */
    private val _todo = MutableLiveData<Any>()
    fun todo(){ _todo.value = Any() }
    val todo = _todo.switchMap {
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

}
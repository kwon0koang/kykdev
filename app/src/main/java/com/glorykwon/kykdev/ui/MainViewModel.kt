package com.glorykwon.kykdev.ui.main

import android.Manifest
import androidx.lifecycle.*
import com.glorykwon.kykdev.MainApplication
import com.glorykwon.kykdev.common.Event
import com.glorykwon.kykdev.common.NetworkResult
import com.glorykwon.kykdev.retrofittest.RetrofitTestApiService
import com.tbruyelle.rxpermissions3.RxPermissions

class MainViewModel : ViewModel() {

    /**
     * Retrofit test
     */
    private val _retrofitTest = MutableLiveData<Event<Any>>()
    fun retrofitTest(){ _retrofitTest.value = Event(Any()) }
    val retrofitTest = _retrofitTest.switchMap {
        liveData {
            emit(Event(NetworkResult.Loading()))
            try {

                val dtoList = RetrofitTestApiService.getInstance().searchById(1)
                emit(Event(NetworkResult.Success(dtoList)))

            } catch (e: Exception) {
                e.printStackTrace()
                emit(Event(NetworkResult.Error(e)))
            }
        }
    }

    /**
     * RxPermission test
     */
    private val _rxPermissionTest = MutableLiveData<Event<Any>>()
    fun rxPermissionTest(){ _rxPermissionTest.value = Event(Any()) }
    val rxPermissionTest = _rxPermissionTest.switchMap {
        liveData {
            emit(Event(NetworkResult.Loading()))
            try {

                var result = false
                MainApplication.getActivityContext()?.let { context ->
                    RxPermissions(context).request(Manifest.permission.CAMERA)
                }?.subscribe { granted: Boolean ->
                    result = granted
                }
                emit(Event(NetworkResult.Success(result)))

            } catch (e: Exception) {
                e.printStackTrace()
                emit(Event(NetworkResult.Error(e)))
            }
        }
    }

}
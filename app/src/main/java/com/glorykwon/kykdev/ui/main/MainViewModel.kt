package com.glorykwon.kykdev.ui.main

import android.Manifest
import androidx.lifecycle.*
import com.glorykwon.kykdev.MainApplication
import com.glorykwon.kykdev.common.Event
import com.glorykwon.kykdev.common.NetworkResult
import com.glorykwon.kykdev.retrofittest.DeepSearchApiService
import com.tbruyelle.rxpermissions3.RxPermissions
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.subjects.BehaviorSubject
import kotlinx.coroutines.delay
import timber.log.Timber
import java.util.concurrent.TimeUnit

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

                val result = DeepSearchApiService.getInstance().searchNewsByKeywordToJson("삼성전자", 800)
                emit(Event(NetworkResult.Success(result)))

            } catch (e: Exception) {
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
                emit(Event(NetworkResult.Error(e)))
            }
        }
    }

    /**
     * Push test
     */
    private val _pushTest = MutableLiveData<Event<Any>>()
    fun pushTest(){ _pushTest.value = Event(Any()) }
    val pushTest = _pushTest.switchMap {
        liveData {
            emit(Event(NetworkResult.Loading()))
            try {

                val result = DeepSearchApiService.getInstance().searchNewsByKeywordToJson("삼성전자", 800)
                emit(Event(NetworkResult.Success(result)))

            } catch (e: Exception) {
                emit(Event(NetworkResult.Error(e)))
            }
        }
    }

}
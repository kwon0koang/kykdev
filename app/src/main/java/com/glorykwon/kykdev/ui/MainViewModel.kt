package com.glorykwon.kykdev.ui

import android.Manifest
import androidx.lifecycle.*
import com.glorykwon.kykdev.MainApplication
import com.glorykwon.kykdev.util.kt.Event
import com.glorykwon.kykdev.common.NetworkResult
import com.glorykwon.kykdev.api.RetrofitTestApiService
import com.glorykwon.kykdev.api.RetrofitTestDto
import com.tbruyelle.rxpermissions3.RxPermissions
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.coroutineScope
import timber.log.Timber
import kotlin.system.measureTimeMillis

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

                val dataList = mutableListOf<RetrofitTestDto>()
                measureTimeMillis {
                    coroutineScope {
                        listOf(async {
                            dataList.addAll(RetrofitTestApiService.getInstance().searchByUserId(1))
                        }, async {
                            dataList.addAll(RetrofitTestApiService.getInstance().searchByUserId(2))
                        }, async {
                            dataList.addAll(RetrofitTestApiService.getInstance().searchByUserId(3))
                        }).awaitAll()
                    }
                }.run { Timber.d("TestDataPagingSource / load / time:${this} / dataSize:${dataList.size}") }

                emit(Event(NetworkResult.Success(dataList)))

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
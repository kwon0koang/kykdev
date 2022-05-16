package com.glorykwon.kykdev.ui

import android.Manifest
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import androidx.lifecycle.switchMap
import com.glorykwon.kykdev.MainApplication
import com.glorykwon.kykdev.api.RetrofitTestApiService
import com.glorykwon.kykdev.api.RetrofitTestDto
import com.glorykwon.kykdev.common.Event
import com.glorykwon.kykdev.common.NetworkResult
import com.tbruyelle.rxpermissions3.RxPermissions
import io.reactivex.rxjava3.kotlin.subscribeBy
import kotlinx.coroutines.Dispatchers
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
                        listOf(async(Dispatchers.IO) {
                            dataList.addAll(RetrofitTestApiService.getInstance().searchByUserId(1))
                        }, async(Dispatchers.IO) {
                            dataList.addAll(RetrofitTestApiService.getInstance().searchByUserId(2))
                        }, async(Dispatchers.IO) {
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
                    RxPermissions(context).request(Manifest.permission.CAMERA)?.subscribeBy { granted: Boolean ->
                        result = granted
                    }
                }

                emit(Event(NetworkResult.Success(result)))

            } catch (e: Exception) {
                e.printStackTrace()
                emit(Event(NetworkResult.Error(e)))
            }
        }
    }

}
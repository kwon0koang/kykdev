package com.glorykwon.kykdev.ui.realmtest

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import androidx.lifecycle.switchMap
import com.glorykwon.kykdev.common.NetworkResult
import com.glorykwon.kykdev.common.database.realm.RealmDbHelper
import com.glorykwon.kykdev.common.database.realm.dao.TodoRealmObject

class RealmTestViewModel() : ViewModel() {

    /**
     * 전체 조회
     */
    private val _getAllItems = MutableLiveData<Any>()
    fun getAllItems(){ _getAllItems.value = Any() }
    val getAllItems = _getAllItems.switchMap {
        liveData {
            emit(NetworkResult.Loading())
            try {

                val allItems = RealmDbHelper.getAllItems()

                emit(NetworkResult.Success(allItems))

            } catch (e: Exception) {
                e.printStackTrace()
                emit(NetworkResult.Error(e))
            }
        }
    }

    /**
     * 데이터 추가
     */
    private val _insertItem = MutableLiveData<TodoRealmObject>()
    fun insertItem(obj: TodoRealmObject){ _insertItem.value = obj }
    val insertItem = _insertItem.switchMap {
        liveData {
            emit(NetworkResult.Loading())
            try {

                RealmDbHelper.insertItem(it)

                emit(NetworkResult.Success())

            } catch (e: Exception) {
                e.printStackTrace()
                emit(NetworkResult.Error(e))
            }
        }
    }

    /**
     * 데이터 삭제
     */
    private val _deleteItemId = MutableLiveData<String>()
    fun deleteItem(id: String){ _deleteItemId.value = id }
    val deleteItem = _deleteItemId.switchMap { id ->
        liveData {
            emit(NetworkResult.Loading())
            try {

                RealmDbHelper.deleteItem(id)

                emit(NetworkResult.Success())

            } catch (e: Exception) {
                e.printStackTrace()
                emit(NetworkResult.Error(e))
            }
        }
    }

}
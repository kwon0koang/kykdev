package com.glorykwon.kykdev.ui.roomtest

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import androidx.lifecycle.switchMap
import com.glorykwon.kykdev.common.NetworkResult
import com.glorykwon.kykdev.common.database.room.KykRoomDatabase
import com.glorykwon.kykdev.common.database.room.entity.TodoRoomEntity

class RoomTestViewModel() : ViewModel() {

    /**
     * 전체 조회
     */
    private val _fetchAllItems = MutableLiveData<Any>()
    fun fetchAllItems(){ _fetchAllItems.value = Any() }
    val fetchAllItems = _fetchAllItems.switchMap {
        liveData {
            emit(NetworkResult.Loading())
            try {

                val todoRoomDao = KykRoomDatabase.getInstance().todoRoomDao()
                val allItems = todoRoomDao.getAllItems()

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
    private val _insertItem = MutableLiveData<TodoRoomEntity>()
    fun insertItem(obj: TodoRoomEntity){ _insertItem.value = obj }
    val insertItem = _insertItem.switchMap {
        liveData {
            emit(NetworkResult.Loading())
            try {

                val todoRoomDao = KykRoomDatabase.getInstance().todoRoomDao()
                todoRoomDao.insertItem(it)

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

                val todoRoomDao = KykRoomDatabase.getInstance().todoRoomDao()
                todoRoomDao.deleteItem(id)

                emit(NetworkResult.Success())

            } catch (e: Exception) {
                e.printStackTrace()
                emit(NetworkResult.Error(e))
            }
        }
    }

}
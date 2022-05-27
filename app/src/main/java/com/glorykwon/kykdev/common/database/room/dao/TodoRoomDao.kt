package com.glorykwon.kykdev.common.database.room.dao

import androidx.room.*
import com.glorykwon.kykdev.common.database.room.entity.TodoRoomEntity

/**
 * 테이블의 데이터와 상호작용하는 데에 사용하는 메서드를 제공
 */
@Dao
interface TodoRoomDao {

    /**
     * 모든 데이터 반환
     */
    @Query("SELECT * FROM todo_room_test ORDER BY id DESC LIMIT 20")
    suspend fun getAllItems(): List<TodoRoomEntity>

    /**
     * 특정 데이터 반환
     */
    @Query("SELECT * FROM todo_room_test WHERE id = :id")
    suspend fun getItem(id: String): TodoRoomEntity

    /**
     * 데이터 추가 or 갱신
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertItem(entity: TodoRoomEntity)

    /**
     * 모든 데이터 삭제
     */
    @Query( "DELETE FROM todo_room_test")
    suspend fun deleteAllItems()

    /**
     * 특정 데이터 삭제
     */
    @Query("DELETE FROM todo_room_test WHERE id = :id")
    suspend fun deleteItem(id: String)

}
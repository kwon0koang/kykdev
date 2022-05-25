package com.glorykwon.kykdev.database.room.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "todo_room_test")
data class TodoRoomEntity (
    @PrimaryKey val id: String,
    @ColumnInfo(name = "content") val content: String,
)
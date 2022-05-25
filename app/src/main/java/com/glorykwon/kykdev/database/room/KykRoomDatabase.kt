package com.glorykwon.kykdev.database.room

import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.glorykwon.kykdev.MainApplication
import com.glorykwon.kykdev.database.room.dao.TodoRoomDao
import com.glorykwon.kykdev.database.room.entity.TodoRoomEntity

@Database(entities = [TodoRoomEntity::class]
    , version = 1
    , exportSchema = false
)
abstract class KykRoomDatabase : RoomDatabase() {

    abstract fun todoRoomDao(): TodoRoomDao

    companion object {
        @Volatile
        private var _INSTANCE: KykRoomDatabase? = null
        fun getInstance(): KykRoomDatabase = _INSTANCE ?: synchronized(this) {
            Room.databaseBuilder(
                MainApplication.getApplicationContext()!!,
                KykRoomDatabase::class.java,
                "kyk_room_database"
            ).fallbackToDestructiveMigration()
                .build()
                .apply { _INSTANCE = this }
        }
    }

}
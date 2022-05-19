package com.glorykwon.kykdev.database.realm

import android.content.Context
import com.glorykwon.kykdev.database.realm.dao.TodoRealmObject
import io.realm.Realm
import io.realm.RealmConfiguration
import io.realm.RealmResults
import io.realm.Sort
import io.realm.kotlin.where

object RealmDbHelper {

    private val TODO_REALM_NAME = "todo.realm"

    private lateinit var mTodoRealm: Realm

    fun init(context: Context) {
        Realm.init(context)

        val config : RealmConfiguration = RealmConfiguration.Builder()
            .allowWritesOnUiThread(true)
            .name(TODO_REALM_NAME)
            .deleteRealmIfMigrationNeeded()
            .build()
        Realm.setDefaultConfiguration(config)

        mTodoRealm = Realm.getDefaultInstance()
    }

    /**
     * 데이터 추가 or 갱신
     */
    fun insert(user: TodoRealmObject) {
        mTodoRealm.executeTransactionAsync {
            it.insertOrUpdate(user)
        }
    }

    /**
     * 모든 데이터 반환
     */
    fun getAllTodo(): RealmResults<TodoRealmObject> {
        return mTodoRealm.where<TodoRealmObject>()
            .findAll()
            .sort("id", Sort.ASCENDING)
    }

    /**
     * 특정 데이터 반환
     */
    fun getTodo(id: String): TodoRealmObject? = mTodoRealm.where<TodoRealmObject>()
        .equalTo("id", id)
        .findFirst()

    /**
     * 모든 데이터 삭제
     */
    fun deleteAllTodo() {
        mTodoRealm.executeTransaction {
            it.where<TodoRealmObject>().findAll().deleteAllFromRealm()
        }
    }

    /**
     * 특정 데이터 삭제
     */
    fun deleteTodo(id: String) {
        mTodoRealm.executeTransaction {
            it.where<TodoRealmObject>().equalTo("id", id).findAll().deleteAllFromRealm()
        }
    }

}
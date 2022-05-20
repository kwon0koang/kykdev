package com.glorykwon.kykdev.database.realm

import android.content.Context
import com.glorykwon.kykdev.database.realm.dao.TodoRealmObject
import io.realm.Realm
import io.realm.RealmConfiguration
import io.realm.RealmResults
import io.realm.Sort
import io.realm.kotlin.executeTransactionAwait
import io.realm.kotlin.where
import kotlinx.coroutines.Dispatchers

object RealmDbHelper {

    private val TODO_REALM_NAME = "todo.realm"
    private lateinit var mTodoRealmConfig: RealmConfiguration

    fun init(context: Context) {
        Realm.init(context)

        mTodoRealmConfig = RealmConfiguration.Builder()
            .allowWritesOnUiThread(true)
            .name(TODO_REALM_NAME)
            .deleteRealmIfMigrationNeeded()
            .build()
        Realm.setDefaultConfiguration(mTodoRealmConfig)

    }

    /**
     * 모든 데이터 반환
     */
    suspend fun getAllItems(): List<TodoRealmObject>? {
        var result: List<TodoRealmObject>? = null

        val realm = Realm.getInstance(mTodoRealmConfig)
        realm.executeTransactionAwait(Dispatchers.IO) { realm ->
            val realmResults = realm.where<TodoRealmObject>()
                .findAll()
                .sort("id", Sort.ASCENDING)
            result = realm.copyFromRealm(realmResults)
        }
        return result
    }

    /**
     * 특정 데이터 반환
     */
    suspend fun getItem(id: String): TodoRealmObject? {
        var result: TodoRealmObject? = null

        val realm = Realm.getInstance(mTodoRealmConfig)
        realm.executeTransactionAwait(Dispatchers.IO) { realm ->
            result = realm.where<TodoRealmObject>()
                .equalTo("id", id)
                .findFirst()
        }

        return result
    }


    /**
     * 데이터 추가 or 갱신
     */
    suspend fun insertItem(obj: TodoRealmObject) {
        val realm = Realm.getInstance(mTodoRealmConfig)
        realm.executeTransactionAwait(Dispatchers.IO) { realm ->
            realm.insertOrUpdate(obj)
        }

    }

    /**
     * 모든 데이터 삭제
     */
    suspend fun deleteAllItems() {
        val realm = Realm.getInstance(mTodoRealmConfig)
        realm.executeTransactionAwait(Dispatchers.IO) { realm ->
            realm.where<TodoRealmObject>().findAll().deleteAllFromRealm()
        }
    }

    /**
     * 특정 데이터 삭제
     */
    suspend fun deleteItem(id: String) {
        val realm = Realm.getInstance(mTodoRealmConfig)
        realm.executeTransactionAwait(Dispatchers.IO) { realm ->
            realm.where<TodoRealmObject>().equalTo("id", id).findAll().deleteAllFromRealm()
        }
    }

}
package com.glorykwon.kykdev.database.realm.dao

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

open class TodoRealmObject (
    @PrimaryKey
    var id: String? = null,
    var content: String? = null,
): RealmObject()
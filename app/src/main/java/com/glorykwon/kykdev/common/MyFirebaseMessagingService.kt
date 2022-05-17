package com.glorykwon.kykdev.common

import androidx.annotation.NonNull
import com.glorykwon.kykdev.util.kt.safeLet
import com.glorykwon.kykdev.util.kt.showNoti
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import timber.log.Timber


class MyFirebaseMessagingService: FirebaseMessagingService() {

    /**
     * 메세지 수신되면 호출
     */
    override fun onMessageReceived(@NonNull remoteMessage: RemoteMessage) {
        super.onMessageReceived(remoteMessage)
        Timber.d("onMessageReceived")

        val title = remoteMessage.notification!!.title
        val body = remoteMessage.notification!!.body

        safeLet(title, body) { title, body ->
            showNoti(title, body)
        }

    }

    /**
     * 새로운 토큰 생성 될 때 호출
     */
    override fun onNewToken(token: String) {
        super.onNewToken(token)
        Timber.d("onNewToken")
    }

}

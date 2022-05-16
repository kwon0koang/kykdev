package com.glorykwon.kykdev.common

import androidx.annotation.NonNull
import com.glorykwon.kykdev.util.kt.safeLet
import com.glorykwon.kykdev.util.kt.showTestNoti
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage


class MyFirebaseMessagingService: FirebaseMessagingService() {

    /**
     * token 서버로 전송
     */
    override fun onNewToken(@NonNull token: String) {
        super.onNewToken(token)
    }

    /**
     * 수신 메시지 처리
     */
    override fun onMessageReceived(@NonNull remoteMessage: RemoteMessage) {
        super.onMessageReceived(remoteMessage)

        val title = remoteMessage.notification!!.title
        val body = remoteMessage.notification!!.body

        safeLet(title, body) { title, body ->
            showTestNoti(title, body)
        }

    }

}

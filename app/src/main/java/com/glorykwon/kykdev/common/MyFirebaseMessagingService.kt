package com.glorykwon.kykdev.common

import android.R
import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.os.Build
import androidx.annotation.NonNull
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.google.firebase.messaging.FirebaseMessaging
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage


class MyFirebaseMessagingService: FirebaseMessagingService() {

    val CHANNEL_ID = "kykdev_CHANNEL_ID"
    val CHANNEL_NAME = "kykdev_CHANNEL_NAME"

    /**
     * token을 서버로 전송
     */
    override fun onNewToken(@NonNull token: String) {
        super.onNewToken(token)
    }

    /**
     * 수신한 메시지 처리
     */
    override fun onMessageReceived(@NonNull remoteMessage: RemoteMessage) {
        super.onMessageReceived(remoteMessage)

        val notificationManager = NotificationManagerCompat.from(
            applicationContext
        )

        var builder: NotificationCompat.Builder? = null
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            if (notificationManager.getNotificationChannel(CHANNEL_ID) == null) {
                val channel = NotificationChannel(
                    CHANNEL_ID,
                    CHANNEL_NAME,
                    NotificationManager.IMPORTANCE_DEFAULT
                )
                notificationManager.createNotificationChannel(channel)
            }
            builder = NotificationCompat.Builder(applicationContext, CHANNEL_ID)
        } else {
            builder = NotificationCompat.Builder(applicationContext)
        }

        val title = remoteMessage.notification!!.title
        val body = remoteMessage.notification!!.body

        builder!!.setContentTitle(title)
            .setContentText(body)
            .setSmallIcon(R.drawable.checkbox_on_background)

        val notification: Notification = builder!!.build()
        notificationManager.notify(1, notification)

    }

}

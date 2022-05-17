package com.glorykwon.kykdev.util.kt

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Intent
import android.media.RingtoneManager
import android.os.Build
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.glorykwon.kykdev.MainApplication
import com.glorykwon.kykdev.ui.MainActivity

val NOTI_CHANNEL_ID = "kykdev_NOTI_CHANNEL_ID"          //알림받을 채널 ID
val NOTI_CHANNEL_NAME = "kykdev_NOTI_CHANNEL_NAME"      //채널 이름
fun showNoti(title: String, content: String) {
    try {
        MainApplication.getActivityContext()?.let { context ->
            val notificationManager = NotificationManagerCompat.from(
                context
            )

            var builder: NotificationCompat.Builder? = null
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                if (notificationManager.getNotificationChannel(NOTI_CHANNEL_ID) == null) {
                    val channel = NotificationChannel(
                        NOTI_CHANNEL_ID,
                        NOTI_CHANNEL_NAME,
                        NotificationManager.IMPORTANCE_HIGH     //알림 우선순위
                    )
                    notificationManager.createNotificationChannel(channel)
                }
                builder = NotificationCompat.Builder(context, NOTI_CHANNEL_ID)
            } else {
                builder = NotificationCompat.Builder(context)
            }

            val notiSoundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION) // 소리

            val intent = Intent(context, MainActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            val pendingIntent = PendingIntent.getActivity(context, 0 , intent, PendingIntent.FLAG_IMMUTABLE)

            builder!!.setContentTitle(title)
                .setContentText(content)
                .setAutoCancel(true)
                .setSound(notiSoundUri)
                .setContentIntent(pendingIntent)
                .setSmallIcon(android.R.drawable.checkbox_on_background)

            val notification: Notification = builder!!.build()
            notificationManager.notify(9001, notification)
        }
    } catch (e: Exception) {
        e.printStackTrace()
    }
}
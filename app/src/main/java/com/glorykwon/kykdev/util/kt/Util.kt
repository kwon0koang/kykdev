package com.glorykwon.kykdev.util.kt

import android.app.*
import android.content.Context
import android.content.Intent
import android.media.RingtoneManager
import android.os.Build
import android.util.DisplayMetrics
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.glorykwon.kykdev.MainApplication
import com.glorykwon.kykdev.ui.MainActivity
import timber.log.Timber


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

/**
 * 현재 함수명 반환
 */
fun getCurrentMethodName(): String? {
    return try {
        Throwable().stackTrace[1].methodName
    } catch(e: Exception) {
        null
    }
}

/**
 * Pixels To DP
 */
fun convertPixelsToDp(context: Context, px: Float): Float = px / (context.resources.displayMetrics.densityDpi.toFloat() / DisplayMetrics.DENSITY_DEFAULT)

/**
 * Dp To Pixels
 */
fun convertDpToPixels(context: Context, dp: Float): Float  = dp * (context.resources.displayMetrics.densityDpi.toFloat() / DisplayMetrics.DENSITY_DEFAULT)

/**
 * Display Width
 */
fun getDisplayWidth(context: Context): Int = context.resources.displayMetrics.widthPixels

/**
 * Display Height
 */
fun getDisplayHeight(context: Context): Int = context.resources.displayMetrics.heightPixels

/**
 * 현재 액티비티명 반환
 *      Ex) com.glorykwon.kykdev.ui.MainActivity
 */
fun getTopActivityName(context: Context): String = try {
    val manager = context.getSystemService(Context.ACTIVITY_SERVICE) as ActivityManager
    var className = ""
    className = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
        manager.appTasks[0].taskInfo.topActivity!!.className
    } else {
        val info = manager.getRunningTasks(1)
        val componentName = info[0].topActivity
        componentName!!.className
    }

    // 리턴 반환 데이터 삽입 실시
    className
} catch (e: Exception) {
    Timber.e(e)
    ""
}

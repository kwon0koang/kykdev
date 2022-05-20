package com.glorykwon.kykdev.common

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.media.RingtoneManager
import android.os.Build
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.work.CoroutineWorker
import androidx.work.ForegroundInfo
import androidx.work.WorkerParameters
import com.glorykwon.kykdev.MainApplication
import com.glorykwon.kykdev.ui.MainActivity
import com.glorykwon.kykdev.util.kt.NOTI_CHANNEL_ID
import com.glorykwon.kykdev.util.kt.NOTI_CHANNEL_NAME
import com.glorykwon.kykdev.util.kt.showNoti
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext
import timber.log.Timber

class TestCoroutineWorker(val context: Context, params: WorkerParameters) : CoroutineWorker(context, params) {

    override suspend fun getForegroundInfo(): ForegroundInfo {
        return super.getForegroundInfo()
    }

    override suspend fun doWork(): Result = withContext(Dispatchers.IO) {
        return@withContext try {

            Timber.d("코루틴 워커 테스트")

            val deferred = async {
                for (i in 0..10) {
                    delay(1000)
                    Timber.d("i:$i")
                }
                "finish"
            }
            val value = deferred.await()
            Timber.d(value)

            showNoti("코루틴 워커 테스트", "완료")

            Result.success()
        } catch (throwable: Throwable) {
            throwable.printStackTrace()
            Result.failure()
        }
    }

}

package com.glorykwon.kykdev.common

import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.glorykwon.kykdev.util.kt.showTestNoti
import kotlinx.coroutines.*
import timber.log.Timber

class TestCoroutineWorker(val context: Context, params: WorkerParameters) : CoroutineWorker(context, params) {

    override suspend fun doWork(): Result = withContext(Dispatchers.IO) {

//        Result.success(): 작업이 성공적으로 완료되었습니다.
//        Result.failure(): 작업에 실패했습니다.
//        Result.retry(): 작업에 실패했으며 재시도 정책에 따라 다른 시점에 시도되어야 합니다.

        return@withContext try {

            Timber.d("코루틴 워커 테스트")

            val deferred = async {
                for (i in 0..20) {
                    delay(1000)
                    Timber.d("i:$i")
                }
                "finish"
            }
            val value = deferred.await()
            Timber.d(value)

            showTestNoti("코루틴 워커 테스트", "완료")

            Result.success()
        } catch (throwable: Throwable) {
            throwable.printStackTrace()
            Result.failure()
        }
    }

}

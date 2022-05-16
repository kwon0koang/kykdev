package com.glorykwon.kykdev.common

import android.content.Context
import androidx.work.Worker
import androidx.work.WorkerParameters
import timber.log.Timber

class TestWorker(context: Context, params: WorkerParameters) : Worker(context, params) {

    override fun doWork(): Result {

//        Result.success(): 작업이 성공적으로 완료되었습니다.
//        Result.failure(): 작업에 실패했습니다.
//        Result.retry(): 작업에 실패했으며 재시도 정책에 따라 다른 시점에 시도되어야 합니다.

        return try {
            Timber.d("워커 테스트")
            Result.success()
        } catch (throwable: Throwable) {
            throwable.printStackTrace()
            Result.failure()
        }
    }
}

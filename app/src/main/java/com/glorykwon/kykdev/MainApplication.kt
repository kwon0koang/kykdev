package com.glorykwon.kykdev

import android.app.Application
import android.content.Context
import androidx.work.Configuration
import com.glorykwon.kykdev.common.database.realm.RealmDbManager
import com.glorykwon.kykdev.common.remoteconfig.RemoteConfigManager
import com.glorykwon.kykdev.ui.BaseActivity
import io.reactivex.rxjava3.exceptions.OnErrorNotImplementedException
import io.reactivex.rxjava3.exceptions.UndeliverableException
import io.reactivex.rxjava3.plugins.RxJavaPlugins
import rxdogtag2.RxDogTag
import timber.log.Timber


class MainApplication: Application(), Configuration.Provider {

    companion object {
        private var mApplicationContext: Context? = null
        fun getApplicationContext() = mApplicationContext

        private var mActivityContext: BaseActivity? = null
        fun getActivityContext() = mActivityContext
        fun setActivityContext(context: BaseActivity) { mActivityContext = context }
    }

    override fun onCreate() {
        super.onCreate()

        //init timber
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }

        mApplicationContext = applicationContext

        //init realm db
        RealmDbManager.init(this)

        //init RxJava error handler
        initRxJavaErrorHandler()

        RemoteConfigManager.init()

    }

    /**
     * RxJava error handler 초기화
     */
    private fun initRxJavaErrorHandler() {
        RxDogTag.install()      //RxJava 오류 시, 어디서 발생했는지 알기 위함
        RxJavaPlugins.setErrorHandler { error ->
            Timber.e("MainApplication / initRxJavaErrorHandler")
            error.printStackTrace()
            if(error is UndeliverableException              //RxJava dispose 이후 이벤트 들어올 때
            || error is OnErrorNotImplementedException      //onError 누락
            ){
                //에러 처리
//                Handler(Looper.getMainLooper()).post{
//                    mActivityContext?.let{ context ->
//                        Toast.makeText(context, error.message, Toast.LENGTH_SHORT).show()
//                    }
//                }
            }
            else{
                //nothing
            }
        }
    }

    /**
     * WorkManager 주문형 초기화 위한 Configuration.Provider 구현
     */
    override fun getWorkManagerConfiguration(): Configuration {
        return Configuration.Builder()
            .setMinimumLoggingLevel(android.util.Log.INFO)
            .build()
    }

}
package com.glorykwon.kykdev.common.analytics

import android.os.Bundle
import com.amplitude.api.Amplitude
import com.amplitude.api.AmplitudeClient
import com.amplitude.api.Identify
import com.glorykwon.kykdev.MainApplication
import com.glorykwon.kykdev.common.remoteconfig.RemoteConfigData
import com.glorykwon.kykdev.common.remoteconfig.RemoteConfigManager
import com.glorykwon.kykdev.util.kt.getCurrentMethodName
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.installations.FirebaseInstallations
import timber.log.Timber

/**
 * analytics manager
 */
object AnalyticsManager {

    private val AMPLITUDE_API_KEY = "9fe351220e1ffe034a9a0f12da574a77"

    private var firebaseAnalytics: FirebaseAnalytics? = null
    private var amplitude: AmplitudeClient? = null

    fun init() {
        MainApplication.getApplicationContext()?.let { applicationContext ->
            firebaseAnalytics = FirebaseAnalytics.getInstance(applicationContext)
//            firebaseAnalytics = Firebase.analytics
            amplitude = Amplitude.getInstance().initialize(applicationContext, AMPLITUDE_API_KEY)
        }
    }

    /**
     * set user id (로그인 후)
     */
    fun setUserId(userId: String) {
        Timber.d("${getCurrentMethodName()} / userId : $userId")
        firebaseAnalytics?.setUserId(userId)
        amplitude?.userId = userId
    }

    /**
     * set user property
     */
    fun setUserProperty(name: String, value: String) {
        Timber.d("${getCurrentMethodName()} / name : $name / value : $value")
        firebaseAnalytics?.setUserProperty(name, value)
        amplitude?.identify(Identify().set(name, value))
    }

    /**
     * 대조군, 실험군 구분 위한 feature flag 셋팅
     */
    fun initFeatureFlags() {
        RemoteConfigData.values().forEach { remoteConfigConst ->
            setUserProperty("feature_flag__${remoteConfigConst.key}"
                , RemoteConfigManager.getValue(remoteConfigConst).toString())
        }
    }

    /**
     * send event
     */
    fun logEvent(analyticsData: AnalyticsData) {
        Timber.d("analyticsData : $analyticsData")
        firebaseAnalytics?.logEvent(analyticsData.eventName, Bundle())
        amplitude?.logEvent(analyticsData.eventName)
    }

    fun getFirebaseToken() {
        FirebaseInstallations.getInstance().getToken(false)
            .addOnCompleteListener { task ->
                if(task.isSuccessful) {
                    Timber.d("firebase token : ${task.result.token}")
                }
            }
    }

}
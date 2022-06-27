package com.glorykwon.kykdev.common.analytics

import android.os.Bundle
import com.amplitude.api.Amplitude
import com.amplitude.api.AmplitudeClient
import com.glorykwon.kykdev.MainApplication
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.analytics.ktx.analytics
import com.google.firebase.ktx.Firebase

/**
 * analytics manager
 */
object AnalyticsManager {

    private val AMPLITUDE_API_KEY = "9fe351220e1ffe034a9a0f12da574a77"

    private var firebaseAnalytics: FirebaseAnalytics? = null
    private var amplitude: AmplitudeClient? = null

    fun init() {
        MainApplication.getApplicationContext()?.let { applicationContext ->
//            firebaseAnalytics = FirebaseAnalytics.getInstance(applicationContext)
            firebaseAnalytics = Firebase.analytics
            amplitude = Amplitude.getInstance().initialize(applicationContext, AMPLITUDE_API_KEY)
        }
    }

    /**
     * 로그인 후 user id 셋팅
     */
    fun setUserId(userId: String) {
        firebaseAnalytics?.setUserId(userId)
        amplitude?.userId = userId
    }

    /**
     * send event
     */
    fun logEvent(analyticsData: AnalyticsData) {
        firebaseAnalytics?.logEvent(analyticsData.eventName, Bundle())
        amplitude?.logEvent(analyticsData.eventName)
    }

}
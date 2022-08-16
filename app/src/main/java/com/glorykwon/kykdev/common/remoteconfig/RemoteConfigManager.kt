package com.glorykwon.kykdev.common.remoteconfig

import com.glorykwon.kykdev.BuildConfig
import com.google.firebase.ktx.Firebase
import com.google.firebase.remoteconfig.ktx.remoteConfig
import com.google.firebase.remoteconfig.ktx.remoteConfigSettings
import timber.log.Timber

/**
 * remote config manager
 */
object RemoteConfigManager {

    private val TAG = this::class.simpleName
    private const val FETCH_INTERVAL = 3600L

    fun init() {
        // 가져오기
        // 앱에서 단기간에 가져오기를 너무 많이 수행하면 가져오기 호출이 제한되고 SDK는 FirebaseRemoteConfigFetchThrottledException을 반환합니다.
        // SDK 버전 17.0.0 이전에는 60분 동안 가져오기 요청 수가 5회로 제한되었지만 최신 버전에서는 좀 더 많이 허용됩니다.
        Firebase.remoteConfig.setConfigSettingsAsync(
            remoteConfigSettings {
                minimumFetchIntervalInSeconds = if(BuildConfig.DEBUG) 1 else FETCH_INTERVAL
            }
        )

        // 인앱 매개변수 기본값 설정
        Firebase.remoteConfig.setDefaultsAsync(mutableMapOf<String, Any>().apply {
            RemoteConfigData.values().forEach{ remoteConfigConst ->
                put(remoteConfigConst.key, remoteConfigConst.defaultValue)
            }
            Timber.tag(TAG).d("인앱 매개변수 기본값 설정 : ${this.toList().joinToString()}")
        })

        // 값 가져오기 및 활성화
        Firebase.remoteConfig.fetchAndActivate()
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    val updated = task.result
                    Timber.tag(TAG).d("Config params updated: $updated")
                    Timber.tag(TAG).d("Fetch and activate succeeded")
                } else {
                    Timber.tag(TAG).d("Fetch failed")
                }
            }
    }

    fun getValue(config: RemoteConfigData) = when(config.defaultValue) {
        is Boolean -> Firebase.remoteConfig.getBoolean(config.key)
        is String -> Firebase.remoteConfig.getString(config.key)
        is Long -> Firebase.remoteConfig.getLong(config.key)
        is Double -> Firebase.remoteConfig.getDouble(config.key)
        else -> throw IllegalArgumentException("unused type")
    }

}
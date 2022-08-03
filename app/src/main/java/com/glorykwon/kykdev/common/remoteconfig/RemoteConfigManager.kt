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
        //가져오기
        //앱에서 단기간에 가져오기를 너무 많이 수행하면 가져오기 호출이 제한되고 SDK는 FirebaseRemoteConfigFetchThrottledException을 반환합니다.
        //SDK 버전 17.0.0 이전에는 60분 동안 가져오기 요청 수가 5회로 제한되었지만 최신 버전에서는 좀 더 많이 허용됩니다.
        Firebase.remoteConfig.setConfigSettingsAsync(
            remoteConfigSettings {
                minimumFetchIntervalInSeconds = if(BuildConfig.DEBUG) 1 else FETCH_INTERVAL
            }
        )

        //인앱 매개변수 기본값 설정
        Firebase.remoteConfig.setDefaultsAsync(mapOf<String, Any>())

        //값 가져오기 및 활성화
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

//    fun getValue(remoteConfigData: RemoteConfigData<Boolean>) = Firebase.remoteConfig.getBoolean(remoteConfigData.key)
//    fun getValue(remoteConfigData: RemoteConfigData<String>) = Firebase.remoteConfig.getString(remoteConfigData.key)
//    fun getValue(remoteConfigData: RemoteConfigData<Long>) = Firebase.remoteConfig.getLong(remoteConfigData.key)
//    fun getValue(remoteConfigData: RemoteConfigData<Double>) = Firebase.remoteConfig.getDouble(remoteConfigData.key)
    inline fun <reified T> getValue(config: RemoteConfigData<T>): T {
        return when(T::class){
            Boolean::class  -> Firebase.remoteConfig.getBoolean(config.key)
            String::class   -> Firebase.remoteConfig.getString(config.key)
            Long::class     -> Firebase.remoteConfig.getLong(config.key)
            Double::class   -> Firebase.remoteConfig.getDouble(config.key)
            else            -> Firebase.remoteConfig.getBoolean(config.key)
        } as T
    }

    /**
     * todo kyk 모든 remote config 가져오기
     * remote config 하나씩 추가될 때마다 여기 추가해야 함. 좋은 방법 없을까 ?
     */
    fun getAllRemoteConfigs(): List<RemoteConfigData<out Any>> {
        return listOf(
            RemoteConfigData.HelloRemoteConfigBoolean(),
            RemoteConfigData.HelloRemoteConfigString()
        )
    }
}
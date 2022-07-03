package com.glorykwon.kykdev.common.remoteconfig

import com.google.firebase.ktx.BuildConfig
import com.google.firebase.ktx.Firebase
import com.google.firebase.remoteconfig.ktx.remoteConfig
import com.google.firebase.remoteconfig.ktx.remoteConfigSettings

/**
 * remote config manager
 */
object RemoteConfigManager {

    private const val FETCH_INTERVAL = 3600L

    fun init() {
        Firebase.remoteConfig.setConfigSettingsAsync(
            remoteConfigSettings {
                minimumFetchIntervalInSeconds = if(BuildConfig.DEBUG) 1 else FETCH_INTERVAL
            }
        )

        Firebase.remoteConfig.setDefaultsAsync(mapOf<String, Any>())

        Firebase.remoteConfig.fetchAndActivate()
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
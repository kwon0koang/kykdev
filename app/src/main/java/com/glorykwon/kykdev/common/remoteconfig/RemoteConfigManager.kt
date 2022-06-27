package com.glorykwon.kykdev.common.remoteconfig

import com.google.firebase.ktx.BuildConfig
import com.google.firebase.ktx.Firebase
import com.google.firebase.remoteconfig.ktx.remoteConfig
import com.google.firebase.remoteconfig.ktx.remoteConfigSettings

/**
 * remote config manager
 */
object RemoteConfigManager {

    const val FETCH_INTERVAL = 3600L

    fun init() {
        Firebase.remoteConfig.setConfigSettingsAsync(
            remoteConfigSettings {
                minimumFetchIntervalInSeconds = if(BuildConfig.DEBUG) 1 else FETCH_INTERVAL
            }
        )

        Firebase.remoteConfig.setDefaultsAsync(mapOf<String, Any>())

        Firebase.remoteConfig.fetchAndActivate()
    }

    fun getValue(remoteConfigData: RemoteConfigData<Boolean>) = Firebase.remoteConfig.getBoolean(remoteConfigData.key)
    fun getValue(remoteConfigData: RemoteConfigData<String>) = Firebase.remoteConfig.getString(remoteConfigData.key)
    fun getValue(remoteConfigData: RemoteConfigData<Long>) = Firebase.remoteConfig.getLong(remoteConfigData.key)
    fun getValue(remoteConfigData: RemoteConfigData<Double>) = Firebase.remoteConfig.getDouble(remoteConfigData.key)

}
package com.glorykwon.kykdev.common.remoteconfig

/**
 * remote config data
 */
sealed class RemoteConfigData<T> {
    abstract val key: String
    abstract val defaultValue: T

    data class HelloRemoteConfigBoolean(
        override val key: String = "hello_remote_config_boolean",
        override val defaultValue: Boolean = false
    ): RemoteConfigData<Boolean>()

    data class HelloRemoteConfigString(
        override val key: String = "hello_remote_config_string",
        override val defaultValue: String = "remote config string test"
    ): RemoteConfigData<String>()

}

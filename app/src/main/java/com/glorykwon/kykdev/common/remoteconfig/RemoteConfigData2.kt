package com.glorykwon.kykdev.common.remoteconfig

import org.json.JSONObject

/**
 * remote config data
 */
//sealed class RemoteConfigData2<T> {
//    abstract val key: String
//    abstract val defaultValue: T
//
//    data class HelloRemoteConfigBoolean(
//        override val key: String = "hello_remote_config_boolean",
//        override val defaultValue: Boolean = false
//    ): RemoteConfigData2<Boolean>()
//
//    data class HelloRemoteConfigString(
//        override val key: String = "hello_remote_config_string",
//        override val defaultValue: String = "remote config string test"
//    ): RemoteConfigData2<String>()
//
//    /**
//     * 강제 업데이트
//     */
//    data class RemoteConfigForceUpdate(
//        override val key: String = "force_update",
//        override val defaultValue: String = JSONObject().apply {
//            put("real", JSONObject().apply {
//                put("forceUpdate", false)
//                put("forceUpdateVersion", "0.0.0")
//                put("noticeMsg", "")
//                put("marketUrl", "")
//            })
//            put("dev", JSONObject().apply {
//                put("forceUpdate", false)
//                put("forceUpdateVersion", "0.0.0")
//                put("noticeMsg", "")
//                put("marketUrl", "")
//            })
//        }.toString()
//    ): RemoteConfigData2<String>() {
//        private fun getJsonStr() = RemoteConfigManager.getValue(this)
//        private fun getServer() = "dev"
//        fun getForceUpdate() = JSONObject(getJsonStr()).optJSONObject(getServer())?.optBoolean("forceUpdate")
//        fun getForceUpdateVersion() = JSONObject(getJsonStr()).optJSONObject(getServer())?.optString("forceUpdateVersion")
//        fun getNoticeMsg() = JSONObject(getJsonStr()).optJSONObject(getServer())?.optString("noticeMsg")
//        fun getMarketUrl() = JSONObject(getJsonStr()).optJSONObject(getServer())?.optString("marketUrl")
//    }
//}

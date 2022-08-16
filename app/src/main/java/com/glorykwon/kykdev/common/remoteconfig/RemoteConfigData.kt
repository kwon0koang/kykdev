package com.glorykwon.kykdev.common.remoteconfig

import org.json.JSONObject


/**
 * remote config const
 */
enum class RemoteConfigData(val key: String, val defaultValue: Any) {

    // 테스트
    HELLO_BOOLEAN(
        key = "hello_boolean",
        defaultValue = false),
    HELLO_STRING(
        key = "hello_string",
        defaultValue = "test string 123"),
    HELLO_LONG(
        key = "hello_long",
        defaultValue = 0L),
    HELLO_DOUBLE(
        key = "hello_double",
        defaultValue = 0.0),

    // 강제 업데이트
    FORCE_UPDATE(
        key = "force_update",
        defaultValue = JSONObject().apply {
            put("real", JSONObject().apply {
                put("isForceUpdate", false)
                put("forceUpdateVersion", "0.0.0")
                put("noticeMsg", "")
                put("marketUrl", "market://todo")
            })
            put("dev", JSONObject().apply {
                put("isForceUpdate", false)
                put("forceUpdateVersion", "0.0.0")
                put("noticeMsg", "")
                put("marketUrl", "market://todo")
            })
        }.toString());

    companion object {
        private fun getJsonStr(config: RemoteConfigData) = RemoteConfigManager.getValue(config) as String
        private fun getServer() = "dev"//if (isReal()) "real" else "dev"

        // 강제 업데이트
        private fun getForceUpdate() = JSONObject(getJsonStr(FORCE_UPDATE)).optJSONObject(getServer())
        fun isForceUpdate() = getForceUpdate()?.optBoolean("isForceUpdate")
        fun getForceUpdateVersion() = getForceUpdate()?.optString("forceUpdateVersion")
        fun getNoticeMsg() = getForceUpdate()?.optString("noticeMsg")
        fun getMarketUrl() = getForceUpdate()?.optString("marketUrl")
    }

}

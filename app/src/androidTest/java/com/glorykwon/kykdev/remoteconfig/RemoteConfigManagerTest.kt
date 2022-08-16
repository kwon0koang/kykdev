package com.glorykwon.kykdev.common.remoteconfig

import androidx.test.ext.junit.runners.AndroidJUnit4
import com.google.common.truth.Truth.*
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class RemoteConfigManagerTest {

    @Before
    fun setUp() {
        RemoteConfigManager.init()
    }

    @Test
    fun testRemoteConfig() {

        val booleanValue = RemoteConfigManager.getValue(RemoteConfigData.HELLO_BOOLEAN)
        val stringValue = RemoteConfigManager.getValue(RemoteConfigData.HELLO_STRING)
        val longValue = RemoteConfigManager.getValue(RemoteConfigData.HELLO_LONG)
        val doubleValue = RemoteConfigManager.getValue(RemoteConfigData.HELLO_DOUBLE)

        println("booleanValue : $booleanValue")
        println("stringValue : $stringValue")
        println("longValue : $longValue")
        println("doubleValue : $doubleValue")

        assertThat(booleanValue).isNotNull()
        assertThat(stringValue).isNotNull()
        assertThat(longValue).isNotNull()
        assertThat(doubleValue).isNotNull()

        val forceUpdate = RemoteConfigData.isForceUpdate()
        val forceUpdateVersion = RemoteConfigData.getForceUpdateVersion()
        val noticeMsg = RemoteConfigData.getNoticeMsg()
        val marketUrl = RemoteConfigData.getMarketUrl()

        println("forceUpdate : $forceUpdate")
        println("forceUpdateVersion : $forceUpdateVersion")
        println("noticeMsg : $noticeMsg")
        println("marketUrl : $marketUrl")

        assertThat(forceUpdate).isNotNull()
        assertThat(forceUpdateVersion).isNotNull()
        assertThat(noticeMsg).isNotNull()
        assertThat(marketUrl).isNotNull()

    }

}
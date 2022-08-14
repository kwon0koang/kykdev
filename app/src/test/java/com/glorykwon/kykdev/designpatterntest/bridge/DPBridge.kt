package com.glorykwon.kykdev.designpatterntest.bridge

import com.glorykwon.kykdev.designpatterntest.devices.Tv
import com.glorykwon.kykdev.designpatterntest.devices.tv_display.Amoled
import com.glorykwon.kykdev.designpatterntest.devices.tv_display.Lcd
import com.glorykwon.kykdev.designpatterntest.devices.tv_display.Led
import com.glorykwon.kykdev.designpatterntest.devices.tv_display.Oled
import org.junit.Test

/**
 * 브릿지 패턴
 * 추상적인 것과 구체적인 것을 분리하여 연결하는 패턴
 */
class DPBridge {

    @Test
    fun test() {
        Tv(mDisplay = Lcd())
        Tv(mDisplay = Led())
        Tv(mDisplay = Oled())
        Tv(mDisplay = Amoled())
    }

}

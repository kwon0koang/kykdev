package com.glorykwon.kykdev.ui.designpatterntest.factory

import com.glorykwon.kykdev.ui.designpatterntest.devices.*

/**
 * 추상 팩토리 메소드 패턴
 */
class AbstractDeviceFactory(val deviceFactory: DeviceFactory2) {

    fun createDevice(): Device {
        return deviceFactory.createDevice()
    }

}
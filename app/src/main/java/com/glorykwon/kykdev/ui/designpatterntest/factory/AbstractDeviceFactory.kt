package com.glorykwon.kykdev.ui.designpatterntest.factory

import com.glorykwon.kykdev.ui.designpatterntest.devices.*

/**
 * 하나의 기기 공장에서 타입 별로 기기를 생성
 */
class AbstractDeviceFactory(val deviceFactory: DeviceFactory2) {

    fun createDevice(): Device {
        return deviceFactory.createDevice()
    }

}
package com.glorykwon.kykdev.ui.designpatterntest.factory

import com.glorykwon.kykdev.ui.designpatterntest.devices.*

/**
 * 하나의 기기 팩토리에서 타입 별로 기기를 생성
 */
class DeviceFactory {

    fun createDevice(deviceType: DeviceType): Device {
        return when(deviceType) {
            DeviceType.TV -> Tv()
            DeviceType.AIR_CONDITIONER -> AirConditioner()
            DeviceType.AIR_CLEANER -> AirCleaner()
        }
    }

}
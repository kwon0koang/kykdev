package com.glorykwon.kykdev.ui.designpatterntest.factory

import com.glorykwon.kykdev.ui.designpatterntest.devices.*

class DeviceFactory {

    fun createDevice(deviceType: DeviceType): Device {
        return when(deviceType) {
            DeviceType.TV -> Tv()
            DeviceType.AIR_CONDITIONER -> AirConditioner()
            DeviceType.AIR_CLEANER -> AirCleaner()
        }
    }

}
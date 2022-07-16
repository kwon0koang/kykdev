package com.glorykwon.kykdev.ui.designpatterntest.factory

import com.glorykwon.kykdev.ui.designpatterntest.devices.*

/**
 * 각각의 기기를 생산하는 팩토리 생성
 */
interface DeviceFactory2 {
    fun createDevice(): Device
}

class TvFactory: DeviceFactory2 {
    override fun createDevice(): Device {
        return Tv()
    }
}
class AirConditionerFactory: DeviceFactory2 {
    override fun createDevice(): Device {
        return AirConditioner()
    }
}
class AirCleanerFactory: DeviceFactory2 {
    override fun createDevice(): Device {
        return AirCleaner()
    }
}
package com.glorykwon.kykdev.ui.designpatterntest.composite

import com.glorykwon.kykdev.ui.designpatterntest.devices.AirCleaner
import com.glorykwon.kykdev.ui.designpatterntest.devices.AirConditioner
import com.glorykwon.kykdev.ui.designpatterntest.devices.Tv

/**
 * 컴포짓 패턴
 * 그룹 전체와 개별 객체를 동일하게 처리할 수 있음
 */
fun main() {

    // 컴포짓 패턴 미사용
    Tv().on()
    AirConditioner().on()
    AirCleaner().on()

    // 컴포짓 패턴 사용
    DeviceStorage().apply {
        addDevice(Tv())
        addDevice(AirConditioner())
        addDevice(AirCleaner())
    }.run {
        on()
    }

}
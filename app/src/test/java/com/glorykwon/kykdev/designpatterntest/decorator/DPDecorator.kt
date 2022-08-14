package com.glorykwon.kykdev.designpatterntest.decorator

import com.glorykwon.kykdev.designpatterntest.devices.AirCleaner
import com.glorykwon.kykdev.designpatterntest.devices.AirConditioner
import com.glorykwon.kykdev.designpatterntest.devices.Device
import com.glorykwon.kykdev.designpatterntest.devices.Tv
import org.junit.Test

/**
 * 데코레이터 패턴
 * 기존 코드를 변경하지 않고 부가 기능을 추가할 수 있음
 * 컴파일 타임이 아닌 런타임에 동적으로 기능 변경 가능
 */
class DPDecorator {

    @Test
    fun test() {
        val devices = listOf(Tv(), Tv(), Tv(), AirConditioner(), AirConditioner(), AirCleaner())
        var filteredDevices: List<Device>

        println(devices.map { device -> device.name }.joinToString())

        // tv만 필터링
        filteredDevices = TvFilteringDecorator(devices).getDevices()!!
        println(filteredDevices.map { device -> device.name }.joinToString())

        // 에어컨만 필터링
        filteredDevices = AirConditionerFilteringDecorator(devices).getDevices()!!
        println(filteredDevices.map { device -> device.name }.joinToString())

        // tv, 에어컨 모두 필터링
        filteredDevices = TvFilteringDecorator(devices).getDevices()!!
        filteredDevices = AirConditionerFilteringDecorator(filteredDevices).getDevices()!!
        println(filteredDevices.map { device -> device.name }.joinToString())
    }

}
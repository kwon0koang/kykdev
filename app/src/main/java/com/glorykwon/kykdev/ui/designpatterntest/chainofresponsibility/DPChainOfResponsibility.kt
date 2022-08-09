package com.glorykwon.kykdev.ui.designpatterntest.chainofresponsibility

import com.glorykwon.kykdev.ui.designpatterntest.devices.Device
import com.glorykwon.kykdev.ui.designpatterntest.devices.Tv

/**
 * 책임 연쇄 패턴
 */
abstract class CreateService(var nextService: CreateService?) {
    open fun createDevice(device: Device) {
        nextService?.createDevice(device)
    }
}

class VerifyCreateDeviceService(nextService: CreateService? = null): CreateService(nextService) {
    override fun createDevice(device: Device) {
        println("${device.name} 만들기 전 검증합니다.")
        super.createDevice(device)
    }
}

class LogCreateDeviceService(nextService: CreateService? = null): CreateService(nextService) {
    override fun createDevice(device: Device) {
        println("${device.name} 만들기 전 로깅합니다.")
        super.createDevice(device)
    }
}

class CreateDeviceService(nextService: CreateService? = null): CreateService(nextService) {
    override fun createDevice(device: Device) {
        println("${device.name} 만듭니다.")
        super.createDevice(device)
    }
}

fun main() {
    // 책임 연쇄 패턴 적용 전
    val tv = Tv()
    VerifyCreateDeviceService().createDevice(tv)        // 검증하려면
    LogCreateDeviceService().createDevice(tv)           // 로깅하려면
    CreateDeviceService().createDevice(tv)
    // 검증과 로깅 둘 다 하려면 ?

    // 책임 연쇄 패턴 적용 후
    // 검증하고 로깅하고 만듬. 여러개 연결 가능
    VerifyCreateDeviceService(LogCreateDeviceService(CreateDeviceService()))
        .createDevice(tv)
}
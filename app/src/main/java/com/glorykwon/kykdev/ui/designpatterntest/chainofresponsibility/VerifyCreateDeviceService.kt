package com.glorykwon.kykdev.ui.designpatterntest.chainofresponsibility

import com.glorykwon.kykdev.ui.designpatterntest.devices.Device
import timber.log.Timber

class VerifyCreateDeviceService(nextService: CreateService? = null): CreateService(nextService) {

    override fun createDevice(device: Device) {
        Timber.d("${device.name}을 검증합니다.")
        super.createDevice(device)
    }

}



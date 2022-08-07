package com.glorykwon.kykdev.ui.designpatterntest.chainofresponsibility

import com.glorykwon.kykdev.ui.designpatterntest.devices.Device
import timber.log.Timber

class LogCreateDeviceService(nextService: CreateService? = null): CreateService(nextService) {

    override fun createDevice(device: Device) {
        Timber.d("${device.getName()}읆 만들기 전 로깅합니다.")
        super.createDevice(device)
    }

}



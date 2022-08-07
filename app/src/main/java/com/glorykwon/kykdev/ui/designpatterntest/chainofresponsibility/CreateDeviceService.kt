package com.glorykwon.kykdev.ui.designpatterntest.chainofresponsibility

import com.glorykwon.kykdev.ui.designpatterntest.devices.Device
import timber.log.Timber

class CreateDeviceService(nextService: CreateService? = null): CreateService(nextService) {

    override fun createDevice(device: Device) {
        Timber.d("${device.getName()}을 만듭니다.")
        super.createDevice(device)
    }

}



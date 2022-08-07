package com.glorykwon.kykdev.ui.designpatterntest.chainofresponsibility

import com.glorykwon.kykdev.ui.designpatterntest.devices.Device

abstract class CreateService(var nextService: CreateService?) {

    open fun createDevice(device: Device) {
        nextService?.createDevice(device)
    }

}



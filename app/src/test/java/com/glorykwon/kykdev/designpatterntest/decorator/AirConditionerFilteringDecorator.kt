package com.glorykwon.kykdev.designpatterntest.decorator

import android.widget.Toast
import com.glorykwon.kykdev.MainApplication
import com.glorykwon.kykdev.designpatterntest.devices.AirConditioner
import com.glorykwon.kykdev.designpatterntest.devices.Device

class AirConditionerFilteringDecorator(override var name: String? = null) : Device {

    private var mDevices: List<Device>? = null
    fun getDevices() = mDevices

    constructor(mDevices: List<Device>) : this() {
        this.mDevices = mDevices.filter { device -> device !is AirConditioner }
    }

    fun getDeviceNames() = mDevices?.map { device -> device.name }?.joinToString() ?: ""

    override fun on() {
        Toast.makeText(MainApplication.getActivityContext(), "on ${getDeviceNames()}", Toast.LENGTH_SHORT).show()
    }

    override fun off() {
        Toast.makeText(MainApplication.getActivityContext(), "off ${getDeviceNames()}", Toast.LENGTH_SHORT).show()
    }

}
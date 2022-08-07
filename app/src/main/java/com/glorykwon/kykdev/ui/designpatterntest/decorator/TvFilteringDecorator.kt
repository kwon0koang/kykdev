package com.glorykwon.kykdev.ui.designpatterntest.decorator

import android.widget.Toast
import com.glorykwon.kykdev.MainApplication
import com.glorykwon.kykdev.ui.designpatterntest.devices.Device
import com.glorykwon.kykdev.ui.designpatterntest.devices.Tv

class TvFilteringDecorator(override var name: String? = null) : Device {

    private var mDevices: List<Device>? = null
    fun getDevices() = mDevices

    constructor(mDevices: List<Device>) : this() {
        this.mDevices = mDevices.filter { device -> device !is Tv }
    }

    fun getDeviceNames() = mDevices?.map { device -> device.name }?.joinToString() ?: ""

    override fun on() {
        Toast.makeText(MainApplication.getActivityContext(), "on ${getDeviceNames()}", Toast.LENGTH_SHORT).show()
    }

    override fun off() {
        Toast.makeText(MainApplication.getActivityContext(), "off ${getDeviceNames()}", Toast.LENGTH_SHORT).show()
    }

}
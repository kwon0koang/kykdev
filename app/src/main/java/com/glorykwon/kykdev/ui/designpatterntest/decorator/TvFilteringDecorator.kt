package com.glorykwon.kykdev.ui.designpatterntest.decorator

import android.widget.Toast
import com.glorykwon.kykdev.MainApplication
import com.glorykwon.kykdev.ui.designpatterntest.devices.Device
import com.glorykwon.kykdev.ui.designpatterntest.devices.Tv

class TvFilteringDecorator: Device {

    private var mDevices: List<Device>? = null
    fun getDevices() = mDevices

    constructor(mDevices: List<Device>) {
        this.mDevices = mDevices.filter { device -> device !is Tv }
    }

    override fun getName(): String = mDevices?.map { device -> device.getName() }?.joinToString() ?: ""

    override fun on() {
        Toast.makeText(MainApplication.getActivityContext(), "on ${getName()}", Toast.LENGTH_SHORT).show()
    }

    override fun off() {
        Toast.makeText(MainApplication.getActivityContext(), "off ${getName()}", Toast.LENGTH_SHORT).show()
    }

}
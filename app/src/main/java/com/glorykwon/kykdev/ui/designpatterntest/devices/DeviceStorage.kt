package com.glorykwon.kykdev.ui.designpatterntest.devices

import android.widget.Toast
import com.glorykwon.kykdev.MainApplication
import com.glorykwon.kykdev.ui.designpatterntest.command.Command
import com.glorykwon.kykdev.ui.designpatterntest.devices.tv_display.Display

class DeviceStorage: Device {

    private var mDevices: ArrayList<Device>? = null

    override fun getName(): String = mDevices?.map { device -> device.getName() }?.joinToString() ?: ""

    override fun on() {
        Toast.makeText(MainApplication.getActivityContext(), "on ${getName()}", Toast.LENGTH_SHORT).show()
    }

    override fun off() {
        Toast.makeText(MainApplication.getActivityContext(), "off ${getName()}", Toast.LENGTH_SHORT).show()
    }

    fun addDevices(devices: List<Device>) {
        if(mDevices == null) {
            mDevices = arrayListOf()
        }

        mDevices?.addAll(devices)
    }
    
    fun addDevices(device: Device) {
        if(mDevices == null) {
            mDevices = arrayListOf()
        }

        mDevices?.add(device)
    }

}
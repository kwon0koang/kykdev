package com.glorykwon.kykdev.designpatterntest.composite

import android.widget.Toast
import com.glorykwon.kykdev.MainApplication
import com.glorykwon.kykdev.designpatterntest.devices.Device

class DeviceStorage(override var name: String? = null) : Device {

    private var mDevices: ArrayList<Device>? = null

    fun getDeviceNames() = mDevices?.map { device -> device.name }?.joinToString() ?: ""

    override fun on() {
        Toast.makeText(MainApplication.getActivityContext(), "on ${getDeviceNames()}", Toast.LENGTH_SHORT).show()
    }

    override fun off() {
        Toast.makeText(MainApplication.getActivityContext(), "off ${getDeviceNames()}", Toast.LENGTH_SHORT).show()
    }

    fun addDevice(device: Device) {
        if(mDevices == null) {
            mDevices = arrayListOf()
        }

        mDevices?.add(device)
    }

}
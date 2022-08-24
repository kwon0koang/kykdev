package com.glorykwon.kykdev.designpatterntest.composite

import com.glorykwon.kykdev.designpatterntest.devices.Device
import timber.log.Timber

class DeviceStorage(override var name: String? = null) : Device {

    private var mDevices: ArrayList<Device>? = null

    fun getDeviceNames() = mDevices?.map { device -> device.name }?.joinToString() ?: ""

    override fun on() {
        Timber.d("on ${getDeviceNames()}")
    }

    override fun off() {
        Timber.d("off ${getDeviceNames()}")
    }

    fun addDevice(device: Device) {
        if(mDevices == null) {
            mDevices = arrayListOf()
        }

        mDevices?.add(device)
    }

}
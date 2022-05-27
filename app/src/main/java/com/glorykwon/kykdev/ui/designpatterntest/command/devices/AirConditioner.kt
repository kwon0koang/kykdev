package com.glorykwon.kykdev.ui.designpatterntest.command.devices

import android.widget.Toast
import com.glorykwon.kykdev.MainApplication
import com.glorykwon.kykdev.ui.designpatterntest.command.Command

class AirConditioner: Device {

    override fun on() {
        Toast.makeText(MainApplication.getActivityContext(), "AirConditioner on", Toast.LENGTH_SHORT).show()
    }

    override fun off() {
        Toast.makeText(MainApplication.getActivityContext(), "AirConditioner off", Toast.LENGTH_SHORT).show()
    }

    fun turboAir() {
        Toast.makeText(MainApplication.getActivityContext(), "AirConditioner turbo air", Toast.LENGTH_SHORT).show()
    }

    class OnCommand(val device: AirConditioner) : Command {
        override fun execute() {
            device.on()
        }
    }

    class OffCommand(val device: AirConditioner) : Command {
        override fun execute() {
            device.off()
        }
    }

    class TurboAirCommand(val device: AirConditioner) : Command {
        override fun execute() {
            device.turboAir()
        }
    }

}
package com.glorykwon.kykdev.designpatterntest.devices

import android.widget.Toast
import com.glorykwon.kykdev.MainApplication
import com.glorykwon.kykdev.designpatterntest.command.Command

class AirConditioner(override var name: String? = "AirConditioner") : Device {

    override fun on() {
        Toast.makeText(MainApplication.getActivityContext(), "on $name", Toast.LENGTH_SHORT).show()
    }

    override fun off() {
        Toast.makeText(MainApplication.getActivityContext(), "off $name", Toast.LENGTH_SHORT).show()
    }

    fun turboAir() {
        Toast.makeText(MainApplication.getActivityContext(), "$name turbo air", Toast.LENGTH_SHORT).show()
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
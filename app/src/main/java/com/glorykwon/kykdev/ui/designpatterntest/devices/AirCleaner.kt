package com.glorykwon.kykdev.ui.designpatterntest.devices

import android.widget.Toast
import com.glorykwon.kykdev.MainApplication
import com.glorykwon.kykdev.ui.designpatterntest.command.Command

class AirCleaner(override var name: String? = "AirCleaner") : Device {

    override fun on() {
        Toast.makeText(MainApplication.getActivityContext(), "on $name", Toast.LENGTH_SHORT).show()
    }

    override fun off() {
        Toast.makeText(MainApplication.getActivityContext(), "off $name", Toast.LENGTH_SHORT).show()
    }

    fun turboClean() {
        Toast.makeText(MainApplication.getActivityContext(), "$name turbo clean", Toast.LENGTH_SHORT).show()
    }

    class OnCommand(val device: AirCleaner) : Command {
        override fun execute() {
            device.on()
        }
    }

    class OffCommand(val device: AirCleaner) : Command {
        override fun execute() {
            device.off()
        }
    }

    class TurboCleanCommand(val device: AirCleaner) : Command {
        override fun execute() {
            device.turboClean()
        }
    }

}
package com.glorykwon.kykdev.designpatterntest.devices

import com.glorykwon.kykdev.designpatterntest.command.Command
import timber.log.Timber

class AirConditioner(override var name: String? = "AirConditioner") : Device {

    override fun on() {
        Timber.d("on $name")
    }

    override fun off() {
        Timber.d("off $name")
    }

    fun turboAir() {
        Timber.d("$name turbo air")
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
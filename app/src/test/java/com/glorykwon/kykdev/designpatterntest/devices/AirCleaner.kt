package com.glorykwon.kykdev.designpatterntest.devices

import com.glorykwon.kykdev.designpatterntest.command.Command
import timber.log.Timber

class AirCleaner(override var name: String? = "AirCleaner") : Device {

    override fun on() {
        Timber.d("on $name")
    }

    override fun off() {
        Timber.d("off $name")
    }

    fun turboClean() {
        Timber.d("$name turbo clean")
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
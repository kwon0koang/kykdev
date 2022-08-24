package com.glorykwon.kykdev.designpatterntest.devices

import com.glorykwon.kykdev.designpatterntest.command.Command
import com.glorykwon.kykdev.designpatterntest.devices.tv_display.Display
import timber.log.Timber

class Tv(
    override var name: String? = "Tv",
    private var mDisplay: Display? = null
) : Device {

    override fun on() {
        Timber.d("on $name")
    }

    override fun off() {
        Timber.d("off $name")
    }

    fun changeChannel() {
        Timber.d("$name change channel")
    }

    fun getScreen() {
        Timber.d("screen is ${mDisplay?.getName()}")
    }

    class OnCommand(val device: Tv) : Command {
        override fun execute() {
            device.on()
        }
    }

    class OffCommand(val device: Tv) : Command {
        override fun execute() {
            device.off()
        }
    }

    class ChangeChannelCommand(val device: Tv) : Command {
        override fun execute() {
            device.changeChannel()
        }
    }

}
package com.glorykwon.kykdev.ui.designpatterntest.devices

import android.widget.Toast
import com.glorykwon.kykdev.MainApplication
import com.glorykwon.kykdev.ui.designpatterntest.command.Command
import com.glorykwon.kykdev.ui.designpatterntest.devices.tv_display.Display

class Tv: Device {

    private var mDisplay: Display? = null

    constructor(mDisplay: Display? = null) {
        this.mDisplay = mDisplay
    }

    override fun on() {
        Toast.makeText(MainApplication.getActivityContext(), "on ${mDisplay?.getName()}Tv", Toast.LENGTH_SHORT).show()
    }

    override fun off() {
        Toast.makeText(MainApplication.getActivityContext(), "off ${mDisplay?.getName()}Tv", Toast.LENGTH_SHORT).show()
    }

    fun changeChannel() {
        Toast.makeText(MainApplication.getActivityContext(), "${mDisplay?.getName()}Tv change channel", Toast.LENGTH_SHORT).show()
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
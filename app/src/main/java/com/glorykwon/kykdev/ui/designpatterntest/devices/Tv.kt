package com.glorykwon.kykdev.ui.designpatterntest.devices

import android.widget.Toast
import com.glorykwon.kykdev.MainApplication
import com.glorykwon.kykdev.ui.designpatterntest.command.Command
import com.glorykwon.kykdev.ui.designpatterntest.devices.tv_display.Display

class Tv(
    override var name: String? = "Tv",
    private var mDisplay: Display? = null
) : Device {

    override fun on() {
        Toast.makeText(MainApplication.getActivityContext(), "on $name", Toast.LENGTH_SHORT).show()
    }

    override fun off() {
        Toast.makeText(MainApplication.getActivityContext(), "off $name", Toast.LENGTH_SHORT).show()
    }

    fun changeChannel() {
        Toast.makeText(MainApplication.getActivityContext(), "$name change channel", Toast.LENGTH_SHORT).show()
    }

    fun getScreen() {
        Toast.makeText(MainApplication.getActivityContext(), "screen is ${mDisplay?.getName()}", Toast.LENGTH_SHORT).show()
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
package com.glorykwon.kykdev.ui.designpatterntest.devices

import android.widget.Toast
import com.glorykwon.kykdev.MainApplication
import com.glorykwon.kykdev.ui.designpatterntest.command.Command

class Tv: Device {

    override fun on() {
        Toast.makeText(MainApplication.getActivityContext(), "on Tv", Toast.LENGTH_SHORT).show()
    }

    override fun off() {
        Toast.makeText(MainApplication.getActivityContext(), "off Tv", Toast.LENGTH_SHORT).show()
    }

    fun changeChannel() {
        Toast.makeText(MainApplication.getActivityContext(), "Tv change channel", Toast.LENGTH_SHORT).show()
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
package com.glorykwon.kykdev.ui.designpatterntest.command.commands

import android.widget.Toast
import com.glorykwon.kykdev.MainApplication
import timber.log.Timber

class AirConditionerOnCommand : Command {

    override fun execute() {
        Timber.d("AirConditioner on")
        Toast.makeText(MainApplication.getActivityContext(), "AirConditioner on", Toast.LENGTH_SHORT)
    }

}
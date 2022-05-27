package com.glorykwon.kykdev.ui.designpatterntest.command.commands

import android.widget.Toast
import com.glorykwon.kykdev.MainApplication
import timber.log.Timber

class AirCleanerOnCommand : Command {

    override fun execute() {
        Timber.d("AirCleaner on")
        Toast.makeText(MainApplication.getActivityContext(), "AirCleaner on", Toast.LENGTH_SHORT)
    }

}
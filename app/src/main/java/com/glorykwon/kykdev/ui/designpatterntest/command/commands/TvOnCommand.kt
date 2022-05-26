package com.glorykwon.kykdev.ui.designpatterntest.command.commands

import android.widget.Toast
import com.glorykwon.kykdev.MainApplication
import timber.log.Timber

class TvOnCommand : Command {

    override fun execute() {
        Timber.d("Tv on")
        Toast.makeText(MainApplication.getActivityContext(), "Tv on", Toast.LENGTH_SHORT)
    }

}
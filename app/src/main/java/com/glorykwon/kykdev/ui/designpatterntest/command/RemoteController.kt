package com.glorykwon.kykdev.ui.designpatterntest.command

import com.glorykwon.kykdev.ui.designpatterntest.command.commands.Command

class RemoteController {

    private lateinit var mCommand: Command

    fun setCommand(command: Command) {
        mCommand = command
    }

    fun execute() {
        if(this::mCommand.isInitialized)
            mCommand.execute()
    }

}
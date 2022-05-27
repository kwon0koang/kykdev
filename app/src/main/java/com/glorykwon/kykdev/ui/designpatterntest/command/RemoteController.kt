package com.glorykwon.kykdev.ui.designpatterntest.command

class RemoteController {

    private var mCommand: Command? = null

    fun setCommand(command: Command) {
        mCommand = command
    }

    fun execute() {
        mCommand?.execute()
    }

}
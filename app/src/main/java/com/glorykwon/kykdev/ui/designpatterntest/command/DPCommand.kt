package com.glorykwon.kykdev.ui.designpatterntest.command

import com.glorykwon.kykdev.ui.designpatterntest.devices.AirCleaner
import com.glorykwon.kykdev.ui.designpatterntest.devices.AirConditioner
import com.glorykwon.kykdev.ui.designpatterntest.devices.Tv

/**
 * 커맨드 패턴
 * 요청을 캡슐화하여 invoker와 receiver를 분리
 */
interface Command {
    fun execute()
}

class RemoteController {
    private var mCommand: Command? = null

    fun setCommand(command: Command) {
        mCommand = command
    }

    fun execute() {
        mCommand?.execute()
    }
}

fun main() {

    RemoteController().apply {
        setCommand(Tv.OnCommand(Tv()))
    }.execute()

    RemoteController().apply {
        setCommand(Tv.OffCommand(Tv()))
    }.execute()

    RemoteController().apply {
        setCommand(Tv.ChangeChannelCommand(Tv()))
    }.execute()

    RemoteController().apply {
        setCommand(AirConditioner.OnCommand(AirConditioner()))
    }.execute()

    RemoteController().apply {
        setCommand(AirConditioner.OffCommand(AirConditioner()))
    }.execute()

    RemoteController().apply {
        setCommand(AirConditioner.TurboAirCommand(AirConditioner()))
    }.execute()

    RemoteController().apply {
        setCommand(AirCleaner.OnCommand(AirCleaner()))
    }.execute()

    RemoteController().apply {
        setCommand(AirCleaner.OffCommand(AirCleaner()))
    }.execute()

    RemoteController().apply {
        setCommand(AirCleaner.TurboCleanCommand(AirCleaner()))
    }.execute()

}
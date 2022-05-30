package com.glorykwon.kykdev.ui.designpatterntest.command

import androidx.lifecycle.ViewModel
import com.glorykwon.kykdev.ui.designpatterntest.command.devices.AirCleaner
import com.glorykwon.kykdev.ui.designpatterntest.command.devices.AirConditioner
import com.glorykwon.kykdev.ui.designpatterntest.command.devices.Tv

class DesignPatternCommandViewModel : ViewModel() {

    //invoker
    var mRemoteController: RemoteController? = null

    //receiver
    var mTv: Tv? = null
    var mAirConditioner: AirConditioner? = null
    var mAirCleaner: AirCleaner? = null

}
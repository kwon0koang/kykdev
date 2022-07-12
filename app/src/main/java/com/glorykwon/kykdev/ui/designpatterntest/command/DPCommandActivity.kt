package com.glorykwon.kykdev.ui.designpatterntest.command

import android.os.Bundle
import com.glorykwon.kykdev.common.analytics.AnalyticsEventConst
import com.glorykwon.kykdev.common.analytics.AnalyticsManager
import com.glorykwon.kykdev.databinding.DesignPatternCommandActivityBinding
import com.glorykwon.kykdev.ui.BaseActivity
import com.glorykwon.kykdev.ui.designpatterntest.devices.AirCleaner
import com.glorykwon.kykdev.ui.designpatterntest.devices.AirConditioner
import com.glorykwon.kykdev.ui.designpatterntest.devices.Tv
import com.glorykwon.kykdev.util.kt.safeLet

class DPCommandActivity : BaseActivity() {

    private val mBinding by lazy { DesignPatternCommandActivityBinding.inflate(layoutInflater) }

    //invoker
    var mRemoteController: RemoteController? = null

    //receiver
    var mTv: Tv? = null
    var mAirConditioner: AirConditioner? = null
    var mAirCleaner: AirCleaner? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(mBinding.root)

        initView()      //뷰 초기화
        initObserver()  //옵저버 초기화
    }

    override fun onResume() {
        super.onResume()
        initData()      //데이터 초기화
        AnalyticsManager.logEvent(AnalyticsEventConst.enterDesignPatternCommand)
    }

    /**
     * 뷰 초기화
     */
    private fun initView() {

        //invoker
        mRemoteController = RemoteController()

        //receiver
        mTv = Tv()
        mAirConditioner = AirConditioner()
        mAirCleaner = AirCleaner()

        mBinding.btnTvOn.setOnClickListener {
            safeLet(mRemoteController, mTv) { remoteController, device ->
                remoteController.setCommand(Tv.OnCommand(device))
            }
        }

        mBinding.btnTvChangeChannel.setOnClickListener {
            safeLet(mRemoteController, mTv) { remoteController, device ->
                remoteController.setCommand(Tv.ChangeChannelCommand(device))
            }
        }

        mBinding.btnAirconditionerOn.setOnClickListener {
            safeLet(mRemoteController, mAirConditioner) { remoteController, device ->
                remoteController.setCommand(AirConditioner.OnCommand(device))
            }
        }

        mBinding.btnAirconditionerTurboAir.setOnClickListener {
            safeLet(mRemoteController, mAirConditioner) { remoteController, device ->
                remoteController.setCommand(AirConditioner.TurboAirCommand(device))
            }
        }

        mBinding.btnAircleanerOn.setOnClickListener {
            safeLet(mRemoteController, mAirCleaner) { remoteController, device ->
                remoteController.setCommand(AirCleaner.OnCommand(device))
            }
        }

        mBinding.btnAircleanerTurboClean.setOnClickListener {
            safeLet(mRemoteController, mAirCleaner) { remoteController, device ->
                remoteController.setCommand(AirCleaner.TurboCleanCommand(device))
            }
        }

        mBinding.btnExecute.setOnClickListener {
            mRemoteController?.execute()
        }

    }

    /**
     * 옵저버 초기화
     */
    private fun initObserver() {
    }

    /**
     * 데이터 초기화
     */
    private fun initData() {
    }

}
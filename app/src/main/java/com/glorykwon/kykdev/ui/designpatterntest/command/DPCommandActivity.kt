package com.glorykwon.kykdev.ui.designpatterntest.command

import android.os.Bundle
import com.glorykwon.kykdev.common.analytics.AnalyticsEventConst
import com.glorykwon.kykdev.common.analytics.AnalyticsManager
import com.glorykwon.kykdev.databinding.DesignPatternCommandActivityBinding
import com.glorykwon.kykdev.ui.BaseActivity
import com.glorykwon.kykdev.ui.designpatterntest.devices.AirCleaner
import com.glorykwon.kykdev.ui.designpatterntest.devices.AirConditioner
import com.glorykwon.kykdev.ui.designpatterntest.devices.Tv

/**
 * 커맨드 패턴
 * 요청을 캡슐화하여 invoker와 receiver를 분리
 */
class DPCommandActivity : BaseActivity() {

    private val mBinding by lazy { DesignPatternCommandActivityBinding.inflate(layoutInflater) }

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

        mBinding.btnTvOn.setOnClickListener {
            RemoteController().apply {
                setCommand(Tv.OnCommand(Tv()))
            }.execute()
        }

        mBinding.btnTvOff.setOnClickListener {
            RemoteController().apply {
                setCommand(Tv.OffCommand(Tv()))
            }.execute()
        }

        mBinding.btnTvChangeChannel.setOnClickListener {
            RemoteController().apply {
                setCommand(Tv.ChangeChannelCommand(Tv()))
            }.execute()
        }

        mBinding.btnAirconditionerOn.setOnClickListener {
            RemoteController().apply {
                setCommand(AirConditioner.OnCommand(AirConditioner()))
            }.execute()
        }

        mBinding.btnAirconditionerOff.setOnClickListener {
            RemoteController().apply {
                setCommand(AirConditioner.OffCommand(AirConditioner()))
            }.execute()
        }

        mBinding.btnAirconditionerTurboAir.setOnClickListener {
            RemoteController().apply {
                setCommand(AirConditioner.TurboAirCommand(AirConditioner()))
            }.execute()
        }

        mBinding.btnAircleanerOn.setOnClickListener {
            RemoteController().apply {
                setCommand(AirCleaner.OnCommand(AirCleaner()))
            }.execute()
        }

        mBinding.btnAircleanerOff.setOnClickListener {
            RemoteController().apply {
                setCommand(AirCleaner.OffCommand(AirCleaner()))
            }.execute()
        }

        mBinding.btnAircleanerTurboClean.setOnClickListener {
            RemoteController().apply {
                setCommand(AirCleaner.TurboCleanCommand(AirCleaner()))
            }.execute()
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
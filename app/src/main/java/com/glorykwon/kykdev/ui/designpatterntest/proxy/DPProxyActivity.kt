package com.glorykwon.kykdev.ui.designpatterntest.proxy

import android.os.Bundle
import com.glorykwon.kykdev.common.analytics.AnalyticsEventConst
import com.glorykwon.kykdev.common.analytics.AnalyticsManager
import com.glorykwon.kykdev.databinding.DesignPatternCompositeActivityBinding
import com.glorykwon.kykdev.databinding.DesignPatternProxyActivityBinding
import com.glorykwon.kykdev.ui.BaseActivity
import com.glorykwon.kykdev.ui.designpatterntest.devices.AirCleaner
import com.glorykwon.kykdev.ui.designpatterntest.devices.AirConditioner
import com.glorykwon.kykdev.ui.designpatterntest.composite.DeviceStorage
import com.glorykwon.kykdev.ui.designpatterntest.devices.Tv

/**
 * 프록시 패턴
 * 특정 객체에 대한 접근을 제어하거나 기능을 추가할 수 있음
 */
class DPProxyActivity : BaseActivity() {

    private val mBinding by lazy { DesignPatternProxyActivityBinding.inflate(layoutInflater) }

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

        mBinding.btnStartGame.setOnClickListener {
            // 프록시 패턴 미사용
            DefaultGameService().startGame()

            // 프록시 패턴 사용
            GameServiceProxy(DefaultGameService()).startGame()
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
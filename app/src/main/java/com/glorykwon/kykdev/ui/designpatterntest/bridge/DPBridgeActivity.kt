package com.glorykwon.kykdev.ui.designpatterntest.bridge

import android.os.Bundle
import com.glorykwon.kykdev.common.analytics.AnalyticsEventConst
import com.glorykwon.kykdev.common.analytics.AnalyticsManager
import com.glorykwon.kykdev.databinding.DesignPatternBridgeActivityBinding
import com.glorykwon.kykdev.ui.BaseActivity
import com.glorykwon.kykdev.ui.designpatterntest.devices.Tv
import com.glorykwon.kykdev.ui.designpatterntest.devices.tv_display.Amoled
import com.glorykwon.kykdev.ui.designpatterntest.devices.tv_display.Lcd
import com.glorykwon.kykdev.ui.designpatterntest.devices.tv_display.Led
import com.glorykwon.kykdev.ui.designpatterntest.devices.tv_display.Oled

/**
 * 브릿지 패턴
 * 추상적인 것과 구체적인 것을 분리하여 연결하는 패턴
 */
class DPBridgeActivity : BaseActivity() {

    private val mBinding by lazy { DesignPatternBridgeActivityBinding.inflate(layoutInflater) }

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

        mBinding.btnLcdTvOn.setOnClickListener {
            Tv(Lcd()).on()
        }
        mBinding.btnLedTvOn.setOnClickListener {
            Tv(Led()).on()
        }
        mBinding.btnOledTvOn.setOnClickListener {
            Tv(Oled()).on()
        }
        mBinding.btnAmoledTvOn.setOnClickListener {
            Tv(Amoled()).on()
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
package com.glorykwon.kykdev.ui.designpatterntest.composite

import android.os.Bundle
import com.glorykwon.kykdev.common.analytics.AnalyticsEventConst
import com.glorykwon.kykdev.common.analytics.AnalyticsManager
import com.glorykwon.kykdev.databinding.DesignPatternCompositeActivityBinding
import com.glorykwon.kykdev.ui.BaseActivity
import com.glorykwon.kykdev.ui.designpatterntest.devices.AirCleaner
import com.glorykwon.kykdev.ui.designpatterntest.devices.AirConditioner
import com.glorykwon.kykdev.ui.designpatterntest.devices.Tv

/**
 * 컴포짓 패턴
 * 그룹 전체와 개별 객체를 동일하게 처리할 수 있음
 */
class DPCompositeActivity : BaseActivity() {

    private val mBinding by lazy { DesignPatternCompositeActivityBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(mBinding.root)

        initView()      //뷰 초기화
        initObserver()  //옵저버 초기화
    }

    override fun onResume() {
        super.onResume()
        initData()      //데이터 초기화
    }

    /**
     * 뷰 초기화
     */
    private fun initView() {

        mBinding.btnTvOn.setOnClickListener {
            Tv().on()
        }

        mBinding.btnAirconditionerOn.setOnClickListener {
            AirConditioner().on()
        }

        mBinding.btnAircleanerOn.setOnClickListener {
            AirCleaner().on()
        }

        mBinding.btnAllDevicesOn.setOnClickListener {
            DeviceStorage().apply {
                addDevices(Tv())
                addDevices(AirConditioner())
                addDevices(AirCleaner())
            }.run {
                on()
            }
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
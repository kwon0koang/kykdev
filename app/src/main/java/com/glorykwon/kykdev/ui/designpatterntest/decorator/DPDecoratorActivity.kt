package com.glorykwon.kykdev.ui.designpatterntest.decorator

import android.os.Bundle
import android.widget.Toast
import com.glorykwon.kykdev.common.analytics.AnalyticsEventConst
import com.glorykwon.kykdev.common.analytics.AnalyticsManager
import com.glorykwon.kykdev.databinding.DesignPatternDecoratorActivityBinding
import com.glorykwon.kykdev.ui.BaseActivity
import com.glorykwon.kykdev.ui.designpatterntest.devices.*
import com.glorykwon.kykdev.ui.designpatterntest.devices.tv_display.Amoled

/**
 * 데코레이터 패턴
 * 기존 코드를 변경하지 않고 부가 기능을 추가할 수 있음
 * 컴파일 타임이 아닌 런타임에 동적으로 기능 변경 가능
 */
class DPDecoratorActivity : BaseActivity() {

    private val mBinding by lazy { DesignPatternDecoratorActivityBinding.inflate(layoutInflater) }

    val mDevices = listOf(Tv(), Tv(), Tv(), AirConditioner(), AirConditioner(), AirCleaner())

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

        mBinding.btnAllDevicesOn.setOnClickListener {
            val msg = mDevices.map { device -> device.getName() }.joinToString()
            Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
        }

        mBinding.btnFilteringTv.setOnClickListener {
            var devices = TvFilteringDecorator(mDevices).getDevices() ?: emptyList()
            val msg = devices.map { device -> device.getName() }.joinToString()
            Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
        }

        mBinding.btnFilteringAirconditioner.setOnClickListener {
            var devices = AirConditionerFilteringDecorator(mDevices).getDevices() ?: emptyList()
            val msg = devices.map { device -> device.getName() }.joinToString()
            Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
        }

        mBinding.btnFilteringTvAndAirconditioner.setOnClickListener {
            var devices = mDevices
            devices = TvFilteringDecorator(devices).getDevices() ?: emptyList()
            devices = AirConditionerFilteringDecorator(devices).getDevices() ?: emptyList()
            val msg = devices.map { device -> device.getName() }.joinToString()
            Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
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
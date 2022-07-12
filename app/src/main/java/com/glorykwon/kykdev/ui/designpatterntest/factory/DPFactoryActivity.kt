package com.glorykwon.kykdev.ui.designpatterntest.factory

import android.os.Bundle
import com.glorykwon.kykdev.databinding.DesignPatternFactoryActivityBinding
import com.glorykwon.kykdev.ui.BaseActivity
import com.glorykwon.kykdev.ui.designpatterntest.devices.DeviceType

class DPFactoryActivity : BaseActivity() {

    private val mBinding by lazy { DesignPatternFactoryActivityBinding.inflate(layoutInflater) }

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
            DeviceFactory().createDevice(DeviceType.TV).on()
        }

        mBinding.btnAirconditionerOn.setOnClickListener {
            DeviceFactory().createDevice(DeviceType.AIR_CONDITIONER).on()
        }

        mBinding.btnAircleanerOn.setOnClickListener {
            DeviceFactory().createDevice(DeviceType.AIR_CLEANER).on()
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
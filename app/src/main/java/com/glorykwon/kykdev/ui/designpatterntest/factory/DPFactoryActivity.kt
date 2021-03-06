package com.glorykwon.kykdev.ui.designpatterntest.factory

import android.os.Bundle
import com.glorykwon.kykdev.databinding.DesignPatternFactoryActivityBinding
import com.glorykwon.kykdev.ui.BaseActivity
import com.glorykwon.kykdev.ui.designpatterntest.devices.DeviceType

/**
 * 팩토리 메소드 패턴, 추상 팩토리 메소드 패턴
 * 구체적으로 어떤 인스턴스륾 만들지는 서브 클래스가 정함
 *      DeviceFactory : 하나의 기기 팩토리에서 타입 별로 기기를 생성
 *      DeviceFactory2 : 각각의 기기를 생산하는 팩토리 생성
 *      AbstractDeviceFactory : 추상 팩토리 메소드 패턴
 */
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

        mBinding.btnTvOn02.setOnClickListener {
            TvFactory().createDevice().on()
        }

        mBinding.btnAirconditionerOn02.setOnClickListener {
            AirConditionerFactory().createDevice().on()
        }

        mBinding.btnAircleanerOn02.setOnClickListener {
            AirCleanerFactory().createDevice().on()
        }

        mBinding.btnTvOn03.setOnClickListener {
            AbstractDeviceFactory(TvFactory()).createDevice().on()
        }

        mBinding.btnAirconditionerOn03.setOnClickListener {
            AbstractDeviceFactory(AirConditionerFactory()).createDevice().on()
        }

        mBinding.btnAircleanerOn03.setOnClickListener {
            AbstractDeviceFactory(AirCleanerFactory()).createDevice().on()
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
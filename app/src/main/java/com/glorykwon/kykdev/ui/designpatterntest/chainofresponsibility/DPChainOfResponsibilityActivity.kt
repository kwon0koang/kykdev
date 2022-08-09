package com.glorykwon.kykdev.ui.designpatterntest.chainofresponsibility

import android.os.Bundle
import com.glorykwon.kykdev.databinding.DesignPatternChainOfResponsibilityActivityBinding
import com.glorykwon.kykdev.ui.BaseActivity
import com.glorykwon.kykdev.ui.designpatterntest.devices.Tv

/**
 * 책임 연쇄 패턴
 */
class DPChainOfResponsibilityActivity : BaseActivity() {

    private val mBinding by lazy { DesignPatternChainOfResponsibilityActivityBinding.inflate(layoutInflater) }

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

        mBinding.btnCreateTv.setOnClickListener {
            // 책임 연쇄 패턴 적용 전
            val tv = Tv()
            CreateDeviceService().createDevice(tv)
            VerifyCreateDeviceService().createDevice(tv)        // 검증하려면
            LogCreateDeviceService().createDevice(tv)           // 로깅하려면
            // 검증과 로깅 둘 다 하려면 ?

            // 책임 연쇄 패턴 적용 후
            // 검증하고 로깅하고 만듬. 여러개 연결 가능
            VerifyCreateDeviceService(LogCreateDeviceService(CreateDeviceService()))
                .createDevice(tv)
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
package com.glorykwon.kykdev.ui.designpatterntest

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.glorykwon.kykdev.common.analytics.AnalyticsEventConst
import com.glorykwon.kykdev.common.analytics.AnalyticsManager
import com.glorykwon.kykdev.databinding.DesignPatternTestFragmentBinding
import com.glorykwon.kykdev.ui.BaseFragment
import com.glorykwon.kykdev.ui.designpatterntest.command.DPCommandActivity
import com.glorykwon.kykdev.ui.designpatterntest.composite.DPCompositeActivity
import com.glorykwon.kykdev.ui.designpatterntest.decorator.DPDecoratorActivity
import com.glorykwon.kykdev.ui.designpatterntest.factory.DPFactoryActivity
import com.glorykwon.kykdev.ui.designpatterntest.proxy.DPProxyActivity

class DesignPatternTestFragment : BaseFragment() {

    private val mBinding by lazy { DesignPatternTestFragmentBinding.inflate(layoutInflater) }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {

        initView()      //뷰 초기화
        initObserver()  //옵저버 초기화

        return mBinding.root
    }

    override fun onResume() {
        super.onResume()
        initData()      //데이터 초기화
        AnalyticsManager.logEvent(AnalyticsEventConst.enterDesignPatternMain)
    }

    /**
     * 뷰 초기화
     */
    private fun initView() {

        mBinding.btnCommand.setOnClickListener {
            val intent = Intent(context, DPCommandActivity::class.java)
            startActivity(intent)
        }

        mBinding.btnFactory.setOnClickListener {
            val intent = Intent(context, DPFactoryActivity::class.java)
            startActivity(intent)
        }

        mBinding.btnComposite.setOnClickListener {
            val intent = Intent(context, DPCompositeActivity::class.java)
            startActivity(intent)
        }

        mBinding.btnDecorator.setOnClickListener {
            val intent = Intent(context, DPDecoratorActivity::class.java)
            startActivity(intent)
        }

        mBinding.btnProxy.setOnClickListener {
            val intent = Intent(context, DPProxyActivity::class.java)
            startActivity(intent)
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
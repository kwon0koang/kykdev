package com.glorykwon.kykdev.ui.uitest

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.glorykwon.kykdev.databinding.ActivityUiTestBinding
import com.glorykwon.kykdev.ui.BaseFragment

/**
 * todo
 */
class UiTestFragment : BaseFragment() {

    private val mBinding by lazy { ActivityUiTestBinding.inflate(layoutInflater) }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {

        initView()      //뷰 초기화
        initObserver()  //옵저버 초기화

        return mBinding.root
    }

    override fun onResume() {
        super.onResume()
        initData()      //데이터 초기화
    }

    /**
     * 뷰 초기화
     */
    private fun initView() {
        mBinding.btnEdittextToTextview.setOnClickListener {
            mBinding.txtUiTest.text = mBinding.etUiTest.text
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
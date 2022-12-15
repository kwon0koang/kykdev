package com.glorykwon.kykdev.ui.uitest

import android.os.Bundle
import com.glorykwon.kykdev.databinding.ActivityUiTestBinding
import com.glorykwon.kykdev.ui.BaseActivity

/**
 * https://developer.android.com/training/testing/ui-testing/espresso-testing?hl=ko#additional-resources-codelabs
 */
class UiTestActivity : BaseActivity() {

    private val mBinding by lazy { ActivityUiTestBinding.inflate(layoutInflater) }

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
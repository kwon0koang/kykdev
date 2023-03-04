package com.glorykwon.kykdev.ui.flowtest

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.core.view.isVisible
import com.glorykwon.kykdev.common.dialog.CommonDialogFragment
import com.glorykwon.kykdev.databinding.ActivityFlowTestBinding
import com.glorykwon.kykdev.ui.BaseActivity
import com.glorykwon.kykdev.ui.webviewtest.WebViewTestActivity
import com.glorykwon.kykdev.util.kt.collectLatestWithLifeCycle
import timber.log.Timber

/**
 * Test flow
 */
class FlowTestActivity : BaseActivity() {

    private val mBinding by lazy { ActivityFlowTestBinding.inflate(layoutInflater) }
    private val mViewModel by viewModels<FlowTestViewModel>()

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

        mBinding.btnStartCountLivedata.setOnClickListener {
            mViewModel.startCountLiveData()
        }

        mBinding.btnStartCountFlow.setOnClickListener {
            mViewModel.startCountFlow()
        }

        mBinding.btnCallNetworkProcess.setOnClickListener {
            mViewModel.callNetworkProcess()
        }

        mBinding.btnCallActivity.setOnClickListener {
            val intent = Intent(this, WebViewTestActivity::class.java)
            startActivity(intent)
        }
    }

    /**
     * 옵저버 초기화
     */
    private fun initObserver() {

        mViewModel.countLiveData.observe(this) {
            Timber.d("mViewModel.countLiveData.observe / $it")
            mBinding.btnStartCountLivedata.text = "Start count livedata : ${it}"
        }

        mViewModel.countFlow.collectLatestWithLifeCycle(this) {
            Timber.d("mViewModel.countFlow.onEach / $it")
            mBinding.btnStartCountFlow.text = "Start count flow : ${it}"
        }

        /**
         * ========================================================================================
         */

        mViewModel.networkProcessValue01.collectLatestWithLifeCycle(this) {
            mBinding.txtNetworkProcessValue01.text = it
        }

        mViewModel.networkProcessValue02.collectLatestWithLifeCycle(this) {
            mBinding.txtNetworkProcessValue02.text = it
        }

        mViewModel.networkProcessValue03.collectLatestWithLifeCycle(this) {
            mBinding.txtNetworkProcessValue03.text = it
        }

        mViewModel.isShowProgress.collectLatestWithLifeCycle(this) {
            mBinding.progressBar.isVisible = it
        }

        mViewModel.errorFlow.collectLatestWithLifeCycle(this) {
            if (it.isNullOrEmpty()) return@collectLatestWithLifeCycle
            CommonDialogFragment(
                content = it,
                confirmText = "확인",
            ).show(supportFragmentManager, null)
        }
    }

    /**
     * 데이터 초기화
     */
    private fun initData() {
    }

}
package com.glorykwon.kykdev.ui.flowtest

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.core.view.isVisible
import androidx.core.widget.addTextChangedListener
import com.glorykwon.kykdev.common.dialog.CommonDialogFragment
import com.glorykwon.kykdev.databinding.ActivityFlowTestBinding
import com.glorykwon.kykdev.ui.BaseActivity
import com.glorykwon.kykdev.ui.webviewtest.WebViewTestActivity
import com.glorykwon.kykdev.util.kt.launchRepeatOnStarted
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.onEach
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

        mBinding.etStatus1.addTextChangedListener {
            mViewModel.updateStatus1(it.toString())
        }

        mBinding.etStatus2.addTextChangedListener {
            mViewModel.updateStatus2(it.toString())
        }

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

        mBinding.btnTestLoading.setOnClickListener {
            mViewModel.funcDelay3000()
            mViewModel.funcDelay1000()
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

        launchRepeatOnStarted {
            mViewModel.countFlow.onEach {
                Timber.d("mViewModel.countFlow.onEach / $it")
                mBinding.btnStartCountFlow.text = "Start count flow : ${it}"
            }.catch { cause -> Timber.e("$cause") }.collect()
        }

        launchRepeatOnStarted {
            mViewModel.status1.onEach {
                Timber.d("mViewModel.status1.onEach / $it")
                mBinding.txtStatus1.text = it
            }.catch { cause -> Timber.e("$cause") }.collect()
        }

        launchRepeatOnStarted {
            mViewModel.status2.onEach {
                Timber.d("mViewModel.status2.onEach / $it")
                mBinding.txtStatus2.text = it
            }.catch { cause -> Timber.e("$cause") }.collect()
        }

        launchRepeatOnStarted {
            mViewModel.networkProcessValue01.onEach {
                mBinding.txtNetworkProcessValue01.text = it
            }.catch { cause -> Timber.e("$cause") }.collect()
        }

        launchRepeatOnStarted {
            mViewModel.networkProcessValue02.onEach {
                mBinding.txtNetworkProcessValue02.text = it
            }.catch { cause -> Timber.e("$cause") }.collect()
        }

        launchRepeatOnStarted {
            mViewModel.networkProcessValue03.onEach {
                mBinding.txtNetworkProcessValue03.text = it
            }.catch { cause -> Timber.e("$cause") }.collect()
        }

        launchRepeatOnStarted {
            mViewModel.isShowProgress.onEach {
                mBinding.progressBar.isVisible = it
            }.catch { cause -> Timber.e("$cause") }.collect()
        }

        launchRepeatOnStarted {
            mViewModel.errorFlow.onEach { msg ->
                if (msg.isNullOrEmpty()) return@onEach
                CommonDialogFragment(
                    content = msg,
                    confirmText = "확인",
                ).show(supportFragmentManager, null)
            }.catch { cause -> Timber.e("$cause") }.collect()
        }
    }

    /**
     * 데이터 초기화
     */
    private fun initData() {
    }

}
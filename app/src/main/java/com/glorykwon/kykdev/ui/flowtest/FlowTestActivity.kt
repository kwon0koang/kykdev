package com.glorykwon.kykdev.ui.flowtest

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.core.widget.addTextChangedListener
import com.glorykwon.kykdev.databinding.ActivityFlowTestBinding
import com.glorykwon.kykdev.ui.BaseActivity
import com.glorykwon.kykdev.ui.webviewtest.WebViewTestActivity
import com.glorykwon.kykdev.util.kt.launchRepeatOnStarted
import kotlinx.coroutines.flow.*
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

        launchRepeatOnStarted {
            mViewModel.countFlow
                .onEach {
                    Timber.d("mViewModel.countFlow.onEach / $it")
                    mBinding.btnStartCountFlow.text = "Start count flow : ${it}"
                }
                .onCompletion { cause -> if (cause == null) "Done" else "Failed" }
                .catch { cause -> Timber.e("$cause") }
                .collect()
        }

        launchRepeatOnStarted {
            mViewModel.status1
                .debounce(1000)
                .onEach {
                    Timber.d("mViewModel.status1.onEach / $it")
                    mBinding.txtStatus1.text = it
                }
                .catch { cause -> Timber.e("$cause") }
                .collect()
        }

        launchRepeatOnStarted {
            mViewModel.status2
                .debounce(1000)
                .onEach {
                    Timber.d("mViewModel.status2.onEach / $it")
                    mBinding.txtStatus2.text = it
                }
                .catch { cause -> Timber.e("$cause") }
                .collect()
        }

    }

    /**
     * 데이터 초기화
     */
    private fun initData() {
    }

}
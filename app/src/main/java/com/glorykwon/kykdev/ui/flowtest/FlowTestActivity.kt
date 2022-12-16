package com.glorykwon.kykdev.ui.flowtest

import android.os.Bundle
import androidx.activity.viewModels
import com.glorykwon.kykdev.databinding.ActivityFlowTestBinding
import com.glorykwon.kykdev.ui.BaseActivity
import com.glorykwon.kykdev.util.kt.launchRepeatOnStarted
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onEach
import timber.log.Timber

/**
 * todo
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
    }

    /**
     * 옵저버 초기화
     */
    private fun initObserver() {

        mViewModel.countLiveData.observe(this) {
            val text = "Start count livedata : ${it}"
            mBinding.btnStartCountLivedata.text = text
            println(text)
        }

        launchRepeatOnStarted {
            mViewModel.countFlow
                .onEach {
                    val text = "Start count flow : ${it}"
                    mBinding.btnStartCountFlow.text = text
                    println(text)
                }
                .onCompletion { cause -> if (cause == null) "Done" else "Failed" }
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
package com.glorykwon.kykdev.template

import android.os.Bundle
import androidx.activity.viewModels
import com.glorykwon.kykdev.common.NetworkResult
import com.glorykwon.kykdev.databinding.ActivityMainBinding
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
class TemplateActivity : BaseActivity() {

    private val mBinding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    private val mViewModel by viewModels<TemplateViewModel>()

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
    }

    /**
     * 옵저버 초기화
     */
    private fun initObserver() {

        mViewModel.todoLiveData.observe(this) {
            when(it) {
                is NetworkResult.Loading -> {}
                is NetworkResult.Success -> {}
                is NetworkResult.Error -> {}
            }
        }

        launchRepeatOnStarted {
            mViewModel.todoFlow
                .onEach { networkResult ->
                    when(networkResult) {
                        is NetworkResult.Loading -> {}
                        is NetworkResult.Success -> {}
                        is NetworkResult.Error -> {}
                    }
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
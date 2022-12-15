package com.glorykwon.kykdev.template

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.glorykwon.kykdev.common.NetworkResult
import com.glorykwon.kykdev.databinding.MainFragmentBinding
import com.glorykwon.kykdev.ui.BaseFragment
import com.glorykwon.kykdev.util.kt.launchRepeatOnStarted
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onEach
import timber.log.Timber

/**
 * todo
 */
class TemplateFragment : BaseFragment() {

    private val mBinding by lazy { MainFragmentBinding.inflate(layoutInflater) }
    private val mViewModel by viewModels<TemplateViewModel>()

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
    }

    /**
     * 옵저버 초기화
     */
    private fun initObserver() {

        mViewModel.todoLiveData.observe(viewLifecycleOwner) {
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
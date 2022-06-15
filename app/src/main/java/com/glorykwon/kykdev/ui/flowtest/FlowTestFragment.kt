package com.glorykwon.kykdev.ui.flowtest

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import com.glorykwon.kykdev.databinding.FlowTestFragmentBinding
import com.glorykwon.kykdev.ui.BaseFragment
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import timber.log.Timber

class FlowTestFragment : BaseFragment() {

    private val mBinding by lazy { FlowTestFragmentBinding.inflate(layoutInflater) }
    private val mViewModel by viewModels<FlowTestViewModel>()

    private lateinit var mAdapter: FlowTestAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {

        initView()      //뷰 초기화

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
        showSkeleton()

        //어댑터 셋팅
        context?.let { context ->
            mAdapter = FlowTestAdapter(context, mViewModel) { dto ->
                Toast.makeText(context, dto.title, Toast.LENGTH_SHORT).show()
            }
        }
        mBinding.rvFlowTest.adapter = mAdapter

        lifecycleScope.launch {
            mAdapter.loadStateFlow.collectLatest { loadStates ->
                val refresh = loadStates.refresh
//                when(refresh) {
//                    is LoadState.Loading -> mProgress.show()
//                    is LoadState.NotLoading, is LoadState.Error -> mProgress.hide()
//                }
                if (refresh is LoadState.NotLoading) {
                    Timber.d("itemCount: ${mAdapter.itemCount}")
                    if(mAdapter.itemCount != 0)
                        hideSkeleton()
                }
            }
        }

    }

    /**
     * 데이터 초기화
     */
    private fun initData() {
        //데이터 조회
        lifecycleScope.launch {
            mViewModel.getTestData()?.collectLatest { pagingDataList ->
                mAdapter?.submitData(pagingDataList)
            }
        }
    }

    private fun showSkeleton() = setSkeleton(true)
    private fun hideSkeleton() = setSkeleton(false)
    private fun setSkeleton(isLoading: Boolean) {
        if (isLoading) {
            mBinding.layoutShimmer.startShimmer()
        } else {
            mBinding.layoutShimmer.stopShimmer()
        }

        mBinding.layoutShimmer.isVisible = isLoading
        mBinding.rvFlowTest.isVisible = !isLoading
    }

}
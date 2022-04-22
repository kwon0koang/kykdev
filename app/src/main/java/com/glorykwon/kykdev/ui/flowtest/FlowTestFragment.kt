package com.glorykwon.kykdev.ui.flowtest

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import com.glorykwon.kykdev.databinding.FlowTestFragmentBinding
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class FlowTestFragment : Fragment() {

    companion object {
        fun newInstance() = FlowTestFragment()
    }

    private val mBinding by lazy { FlowTestFragmentBinding.inflate(layoutInflater) }
    private val mViewModel by viewModels<FlowTestViewModel>()

    private lateinit var mAdapter: FlowTestAdapter

    private var mNextKey = 1

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {

        initView()      //뷰 초기화

        return mBinding.apply{

        }.root
    }

    override fun onResume() {
        super.onResume()
        initData()      //데이터 초기화
    }

    /**
     * 뷰 초기화
     */
    private fun initView() {
        //어댑터 셋팅
        mAdapter = FlowTestAdapter{ testDto ->
            Toast.makeText(activity, testDto.value01, Toast.LENGTH_SHORT).show()
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
//                    Toast.makeText(activity, "itemCount:${mAdapter.itemCount}", Toast.LENGTH_SHORT).show()
                }
            }
        }

    }

    /**
     * 데이터 초기화
     */
    private fun initData() {
        //더미 데이터 조회
        lifecycleScope.launch {
            mViewModel.fetchDummyData()?.collectLatest { pagingDataList ->
                mAdapter?.submitData(pagingDataList)
            }
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
    }

}
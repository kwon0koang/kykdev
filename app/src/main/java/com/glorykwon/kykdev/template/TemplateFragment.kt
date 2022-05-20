package com.glorykwon.kykdev.template

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.glorykwon.kykdev.common.NetworkResult
import com.glorykwon.kykdev.databinding.MainFragmentBinding

class TemplateFragment : Fragment() {

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

        mViewModel.todo.observe(viewLifecycleOwner) {
            when(it) {
                is NetworkResult.Loading -> {}
                is NetworkResult.Success -> {}
                is NetworkResult.Error -> {}
            }
        }

    }

    /**
     * 데이터 초기화
     */
    private fun initData() {
    }

}
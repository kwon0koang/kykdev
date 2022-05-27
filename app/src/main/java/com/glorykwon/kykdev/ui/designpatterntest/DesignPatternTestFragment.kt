package com.glorykwon.kykdev.ui.designpatterntest

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.glorykwon.kykdev.R
import com.glorykwon.kykdev.common.NetworkResult
import com.glorykwon.kykdev.databinding.DesignPatternTestFragmentBinding
import com.glorykwon.kykdev.databinding.MainFragmentBinding
import com.glorykwon.kykdev.ui.BaseFragment

class DesignPatternTestFragment : BaseFragment() {

    private val mBinding by lazy { DesignPatternTestFragmentBinding.inflate(layoutInflater) }

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

        mBinding.btnCommand.setOnClickListener {
            findNavController().navigate(R.id.action_designPatternTestFragment_to_designPatternCommandFragment)
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
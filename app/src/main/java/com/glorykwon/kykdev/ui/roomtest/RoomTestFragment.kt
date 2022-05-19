package com.glorykwon.kykdev.ui.roomtest

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.glorykwon.kykdev.databinding.RoomTestFragmentBinding
import com.glorykwon.kykdev.ui.BaseFragment

class RoomTestFragment : BaseFragment() {

    private val mBinding by lazy { RoomTestFragmentBinding.inflate(layoutInflater) }
    private val mViewModel by viewModels<RoomTestViewModel>()

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

    }

    /**
     * 데이터 초기화
     */
    private fun initData() {
    }

}
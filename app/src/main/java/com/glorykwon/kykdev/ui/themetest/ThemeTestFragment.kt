package com.glorykwon.kykdev.ui.themetest

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.glorykwon.kykdev.common.NetworkResult
import com.glorykwon.kykdev.common.ThemeManager
import com.glorykwon.kykdev.databinding.ThemeTestFragmentBinding
import com.glorykwon.kykdev.ui.BaseFragment

class ThemeTestFragment : BaseFragment() {

    private val mBinding by lazy { ThemeTestFragmentBinding.inflate(layoutInflater) }

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
        mBinding.btnLight.setOnClickListener {
            ThemeManager.applyTheme(ThemeManager.ThemeMode.LIGHT)
        }

        mBinding.btnDark.setOnClickListener {
            ThemeManager.applyTheme(ThemeManager.ThemeMode.DARK)
        }

        mBinding.btnDefault.setOnClickListener {
            ThemeManager.applyTheme(ThemeManager.ThemeMode.DEFAULT)
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
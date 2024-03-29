package com.glorykwon.kykdev.ui

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.glorykwon.kykdev.R
import com.glorykwon.kykdev.common.NetworkResult
import com.glorykwon.kykdev.common.analytics.AnalyticsEventConst
import com.glorykwon.kykdev.common.analytics.AnalyticsManager
import com.glorykwon.kykdev.common.api.RetrofitTestDto
import com.glorykwon.kykdev.common.dynamiclink.DynamicLinkActivity
import com.glorykwon.kykdev.common.remoteconfig.RemoteConfigData
import com.glorykwon.kykdev.common.remoteconfig.RemoteConfigManager
import com.glorykwon.kykdev.databinding.FragmentMainBinding
import com.glorykwon.kykdev.template.TemplateBSFragment
import com.glorykwon.kykdev.ui.flowtest.FlowTestActivity
import com.glorykwon.kykdev.ui.uitest.UiTestActivity
import com.glorykwon.kykdev.ui.verticalseekbartest.VerticalSeekbarTestActivity
import com.glorykwon.kykdev.ui.webviewtest.WebViewTestActivity
import timber.log.Timber

class MainFragment : BaseFragment() {

    private val mBinding by lazy { FragmentMainBinding.inflate(layoutInflater) }
    private val mViewModel by viewModels<MainViewModel>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {

        initView()      //뷰 초기화
        initObserver()  //옵저버 초기화

        return mBinding.root
    }

    override fun onResume() {
        super.onResume()
        initData()      //데이터 초기화
        AnalyticsManager.logEvent(AnalyticsEventConst.enterMain)
    }

    /**
     * 뷰 초기화
     */
    private fun initView() {

        // A/B 테스트 버튼 색 변경
        if(RemoteConfigManager.getValue(RemoteConfigData.COLORFUL_AB_TEST_BUTTON) == true) {
            mBinding.btnAbTest.setBackgroundColor(Color.GREEN)
        }

        mBinding.btnRetrofitTest.setOnClickListener {
            mViewModel.retrofitTest()
            AnalyticsManager.logEvent(AnalyticsEventConst.clickRetrofitTest)
        }

        mBinding.btnRxpermissionTest.setOnClickListener {
            mViewModel.rxPermissionTest()
            AnalyticsManager.logEvent(AnalyticsEventConst.clickRxPermissionTest)
        }

        mBinding.btnRxjavaTest.setOnClickListener {
            findNavController().navigate(R.id.action_mainFragment_to_rxJavaTestFragment)
        }

        mBinding.btnFlowTest.setOnClickListener {
            val intent = Intent(context, FlowTestActivity::class.java)
            startActivity(intent)
        }

        mBinding.btnPagingTest.setOnClickListener {
            findNavController().navigate(R.id.action_mainFragment_to_pagingTestFragment)
        }

        mBinding.btnWorkManagerTest.setOnClickListener {
            findNavController().navigate(R.id.action_mainFragment_to_workManagerTestFragment)
        }

        mBinding.btnRealmTest.setOnClickListener {
            findNavController().navigate(R.id.action_mainFragment_to_realmTestFragment)
        }

        mBinding.btnRoomTest.setOnClickListener {
            findNavController().navigate(R.id.action_mainFragment_to_roomTestFragment)
        }

        mBinding.btnDynamicLinkTest.setOnClickListener {
            DynamicLinkActivity.shareDynamicLink()
        }

        mBinding.btnThemeTest.setOnClickListener {
            findNavController().navigate(R.id.action_mainFragment_to_themeTestFragment)
        }

        mBinding.btnRemoteConfigTest.setOnClickListener {
            val booleanValue = RemoteConfigManager.getValue(RemoteConfigData.HELLO_BOOLEAN)
            val stringValue = RemoteConfigManager.getValue(RemoteConfigData.HELLO_STRING)
            val longValue = RemoteConfigManager.getValue(RemoteConfigData.HELLO_LONG)
            val doubleValue = RemoteConfigManager.getValue(RemoteConfigData.HELLO_DOUBLE)

            println("booleanValue : $booleanValue")
            println("stringValue : $stringValue")
            println("longValue : $longValue")
            println("doubleValue : $doubleValue")

            val forceUpdate = RemoteConfigData.isForceUpdate()
            val forceUpdateVersion = RemoteConfigData.getForceUpdateVersion()
            val noticeMsg = RemoteConfigData.getNoticeMsg()
            val marketUrl = RemoteConfigData.getMarketUrl()

            println("forceUpdate : $forceUpdate")
            println("forceUpdateVersion : $forceUpdateVersion")
            println("noticeMsg : $noticeMsg")
            println("marketUrl : $marketUrl")
        }

        mBinding.btnAbTest.setOnClickListener {
            Toast.makeText(context, "click a/b test button", Toast.LENGTH_SHORT).show()

            AnalyticsManager.logEvent(AnalyticsEventConst.clickAbTestButton)
        }

        mBinding.btnUiTest.setOnClickListener {
            val intent = Intent(context, UiTestActivity::class.java)
            startActivity(intent)
        }

        mBinding.btnWebviewTest.setOnClickListener {
            val intent = Intent(context, WebViewTestActivity::class.java)
            startActivity(intent)
        }

        mBinding.btnBottomSheetFragmentTest.setOnClickListener {
            TemplateBSFragment(
                mTextTitle = "제목제목제목제목제목제목제목제목제목제목제목제목제목제목제목제목제목제목제목제목제목제목제목",
                mTextContent = "내용내용내용내용내용내용내용내용내용내용내용내용내용내용내용내용내용내용내용내용내용내용내용내용내용내용내용내용내용내용"
            ).show(childFragmentManager, null)
        }

        mBinding.btnVerticalSeekbarTest.setOnClickListener {
            val intent = Intent(context, VerticalSeekbarTestActivity::class.java)
            startActivity(intent)
        }

    }

    /**
     * 옵저버 초기화
     */
    private fun initObserver() {

        //Retrofit test
        mViewModel.retrofitTest.observe(viewLifecycleOwner) {
            it.getContentIfNotHandled()?.let{
                when(it) {
                    is NetworkResult.Loading -> {}
                    is NetworkResult.Success -> {
                        val dtoList = it.data as List<RetrofitTestDto>
                        Toast.makeText(context, dtoList.toString(), Toast.LENGTH_SHORT).show()
                        Timber.d("retrofitTest / dtoList:$dtoList")

                        AnalyticsManager.logEvent(AnalyticsEventConst.successRetrofitTest)
                    }
                    is NetworkResult.Error -> {
                        Toast.makeText(context, it.toString(), Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }

        //RxPermission test
        mViewModel.rxPermissionTest.observe(viewLifecycleOwner) {
            it.getContentIfNotHandled()?.let{
                when(it) {
                    is NetworkResult.Loading -> {}
                    is NetworkResult.Success -> {
                        val result = it.data
                        Toast.makeText(context, result.toString(), Toast.LENGTH_SHORT).show()

                        AnalyticsManager.logEvent(AnalyticsEventConst.successRxPermissionTest)
                    }
                    is NetworkResult.Error -> {
                        Toast.makeText(context, it.toString(), Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }

    }

    /**
     * 데이터 초기화
     */
    private fun initData() {
    }

}
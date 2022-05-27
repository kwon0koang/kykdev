package com.glorykwon.kykdev.ui

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.glorykwon.kykdev.R
import com.glorykwon.kykdev.common.NetworkResult
import com.glorykwon.kykdev.common.api.RetrofitTestDto
import com.glorykwon.kykdev.common.dynamiclink.DynamicLinkActivity
import com.glorykwon.kykdev.databinding.MainFragmentBinding
import com.glorykwon.kykdev.ui.designpatterntest.DesignPatternTestActivity
import com.google.firebase.messaging.FirebaseMessaging
import timber.log.Timber

class MainFragment : BaseFragment() {

    private val mBinding by lazy { MainFragmentBinding.inflate(layoutInflater) }
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
    }

    /**
     * 뷰 초기화
     */
    private fun initView() {

        mBinding.btnRetrofitTest.setOnClickListener {
            mViewModel.retrofitTest()
        }

        mBinding.btnRxpermissionTest.setOnClickListener {
            mViewModel.rxPermissionTest()
        }

        mBinding.btnRxjavaTest.setOnClickListener {
            findNavController().navigate(R.id.action_mainFragment_to_rxJavaTestFragment)
        }

        mBinding.btnFlowTest.setOnClickListener {
            findNavController().navigate(R.id.action_mainFragment_to_flowTestFragment)
        }

        mBinding.btnPushTest.setOnClickListener {
            val myToken = FirebaseMessaging.getInstance().getToken().getResult()
            Toast.makeText(context, myToken, Toast.LENGTH_SHORT).show()
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

        mBinding.btnDesignPatternTest.setOnClickListener {
            val intent = Intent(activity, DesignPatternTestActivity::class.java)
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
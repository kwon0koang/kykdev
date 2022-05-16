package com.glorykwon.kykdev.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.glorykwon.kykdev.R
import com.glorykwon.kykdev.api.RetrofitTestDto
import com.glorykwon.kykdev.common.NetworkResult
import com.glorykwon.kykdev.databinding.MainFragmentBinding
import com.google.firebase.messaging.FirebaseMessaging
import timber.log.Timber

class MainFragment : Fragment() {

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

        //Retrofit test
        mBinding.btnRetrofitTest.setOnClickListener {
            mViewModel.retrofitTest()
        }

        //RxPermission test
        mBinding.btnRxpermissionTest.setOnClickListener {
            mViewModel.rxPermissionTest()
        }

        //RxJava test
        mBinding.btnRxjavaTest.setOnClickListener {
            findNavController().navigate(R.id.action_mainFragment_to_rxJavaTestFragment)
        }

        //Flow test
        mBinding.btnFlowTest.setOnClickListener {
            findNavController().navigate(R.id.action_mainFragment_to_flowTestFragment)
        }

        //Push test
        mBinding.btnPushTest.setOnClickListener {
            val myToken = FirebaseMessaging.getInstance().getToken().getResult()
            Toast.makeText(context, myToken, Toast.LENGTH_SHORT).show()
        }

        //WorkManager test
        mBinding.btnWorkManagerTest.setOnClickListener {
            findNavController().navigate(R.id.action_mainFragment_to_workManagerTestFragment)
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
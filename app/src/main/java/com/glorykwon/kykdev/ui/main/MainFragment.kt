package com.glorykwon.kykdev.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.glorykwon.kykdev.R
import com.glorykwon.kykdev.common.MyFirebaseMessagingService
import com.glorykwon.kykdev.common.NetworkResult
import com.glorykwon.kykdev.databinding.MainFragmentBinding
import com.google.firebase.messaging.FirebaseMessaging
import timber.log.Timber

class MainFragment : Fragment() {

    companion object {
        fun newInstance() = MainFragment()
    }

    private val mBinding by lazy { MainFragmentBinding.inflate(layoutInflater) }
    private val mViewModel by viewModels<MainViewModel>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {

        initView()      //뷰 초기화

        return mBinding.apply{
            //Retrofit test
            btnRetrofitTest.setOnClickListener {
                mViewModel.retrofitTest()
            }
            //RxPermission test
            btnRxpermissionTest.setOnClickListener {
                mViewModel.rxPermissionTest()
            }
            //RxJava test
            btnRxjavaTest.setOnClickListener {
                findNavController().navigate(R.id.action_mainFragment_to_rxJavaTestFragment)
            }
            //Flow test
            btnFlowTest.setOnClickListener {
                findNavController().navigate(R.id.action_mainFragment_to_flowTestFragment)
            }
            //Push test
            btnPushTest.setOnClickListener {
                val myToken = FirebaseMessaging.getInstance().getToken().getResult()
                Toast.makeText(context, "myToken\n\n$myToken", Toast.LENGTH_SHORT).show()
            }
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

        //Retrofit test
        mViewModel.retrofitTest.observe(viewLifecycleOwner) {
            it.getContentIfNotHandled()?.let{
                when(it) {
                    is NetworkResult.Loading -> {}
                    is NetworkResult.Success -> {
                        val result = it.data
                        Toast.makeText(context, result.toString(), Toast.LENGTH_SHORT).show()
                    }
                    is NetworkResult.Error -> {}
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
                    is NetworkResult.Error -> {}
                }
            }
        }
        //Push test
        mViewModel.pushTest.observe(viewLifecycleOwner) {
            it.getContentIfNotHandled()?.let{
                when(it) {
                    is NetworkResult.Loading -> {}
                    is NetworkResult.Success -> {
                        val result = it.data
                        Toast.makeText(context, result.toString(), Toast.LENGTH_SHORT).show()
                    }
                    is NetworkResult.Error -> {}
                }
            }
        }

    }

    /**
     * 데이터 초기화
     */
    private fun initData() {
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
    }

}
package com.glorykwon.kykdev.ui.designpatterntest.command

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.glorykwon.kykdev.databinding.DesignPatternCommandFragmentBinding
import com.glorykwon.kykdev.ui.BaseFragment
import com.glorykwon.kykdev.ui.designpatterntest.command.commands.AirCleanerOnCommand
import com.glorykwon.kykdev.ui.designpatterntest.command.commands.AirConditionerOnCommand
import com.glorykwon.kykdev.ui.designpatterntest.command.commands.TvOnCommand

class DesignPatternCommandFragment : BaseFragment() {

    private val mBinding by lazy { DesignPatternCommandFragmentBinding.inflate(layoutInflater) }
    private val mViewModel by viewModels<DesignPatternCommandViewModel>()

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

        mViewModel.mRemoteController = RemoteController()

        mBinding.btnTv.setOnClickListener {
            mViewModel.mRemoteController.setCommand(TvOnCommand())
        }

        mBinding.btnAirconditioner.setOnClickListener {
            mViewModel.mRemoteController.setCommand(AirConditionerOnCommand())
        }

        mBinding.btnAircleaner.setOnClickListener {
            mViewModel.mRemoteController.setCommand(AirCleanerOnCommand())
        }

        mBinding.btnExecute.setOnClickListener {
            mViewModel.mRemoteController.execute()
        }

    }

    /**
     * 옵저버 초기화
     */
    private fun initObserver() {

//        mViewModel.todo.observe(viewLifecycleOwner) {
//            when(it) {
//                is NetworkResult.Loading -> {}
//                is NetworkResult.Success -> {}
//                is NetworkResult.Error -> {}
//            }
//        }

    }

    /**
     * 데이터 초기화
     */
    private fun initData() {
    }

}
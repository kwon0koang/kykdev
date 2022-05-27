package com.glorykwon.kykdev.ui.designpatterntest.command

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.glorykwon.kykdev.databinding.DesignPatternCommandFragmentBinding
import com.glorykwon.kykdev.ui.BaseFragment
import com.glorykwon.kykdev.ui.designpatterntest.command.devices.AirCleaner
import com.glorykwon.kykdev.ui.designpatterntest.command.devices.AirConditioner
import com.glorykwon.kykdev.ui.designpatterntest.command.devices.Tv
import com.glorykwon.kykdev.util.kt.safeLet

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

        mViewModel.mTv = Tv()
        mViewModel.mAirConditioner = AirConditioner()
        mViewModel.mAirCleaner = AirCleaner()

        mBinding.btnTvOn.setOnClickListener {
            safeLet(mViewModel.mRemoteController, mViewModel.mTv) { remoteController, device ->
                remoteController.setCommand(Tv.OnCommand(device))
            }
        }

        mBinding.btnTvChangeChannel.setOnClickListener {
            safeLet(mViewModel.mRemoteController, mViewModel.mTv) { remoteController, device ->
                remoteController.setCommand(Tv.ChangeChannelCommand(device))
            }
        }

        mBinding.btnAirconditionerOn.setOnClickListener {
            safeLet(mViewModel.mRemoteController, mViewModel.mAirConditioner) { remoteController, device ->
                remoteController.setCommand(AirConditioner.OnCommand(device))
            }
        }

        mBinding.btnAirconditionerTurboAir.setOnClickListener {
            safeLet(mViewModel.mRemoteController, mViewModel.mAirConditioner) { remoteController, device ->
                remoteController.setCommand(AirConditioner.TurboAirCommand(device))
            }
        }

        mBinding.btnAircleanerOn.setOnClickListener {
            safeLet(mViewModel.mRemoteController, mViewModel.mAirCleaner) { remoteController, device ->
                remoteController.setCommand(AirCleaner.OnCommand(device))
            }
        }

        mBinding.btnAircleanerTurboClean.setOnClickListener {
            safeLet(mViewModel.mRemoteController, mViewModel.mAirCleaner) { remoteController, device ->
                remoteController.setCommand(AirCleaner.TurboCleanCommand(device))
            }
        }

        mBinding.btnExecute.setOnClickListener {
            mViewModel.mRemoteController?.execute()
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
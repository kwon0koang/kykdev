package com.glorykwon.kykdev.ui.workmanagertest

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import androidx.work.WorkRequest
import com.glorykwon.kykdev.common.TestCoroutineWorker
import com.glorykwon.kykdev.common.TestWorker
import com.glorykwon.kykdev.databinding.WorkmanagerTestFragmentBinding
import com.glorykwon.kykdev.ui.BaseFragment

/**
 * https://developer.android.com/topic/libraries/architecture/workmanager/how-to/define-work?hl=ko
 */
class WorkManagerTestFragment : BaseFragment() {

    private val mBinding by lazy { WorkmanagerTestFragmentBinding.inflate(layoutInflater) }
    private val mViewModel by viewModels<WorkManagerTestViewModel>()

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

        mBinding.btnWorkerTest.setOnClickListener {
            context?.applicationContext?.let { context ->
                val workRequest: WorkRequest = OneTimeWorkRequestBuilder<TestWorker>()
                    .build()

                WorkManager.getInstance(context).enqueue(workRequest)
            }
        }

        mBinding.btnCoroutineWorkerTest.setOnClickListener {
            context?.applicationContext?.let { context ->
                val workRequest: WorkRequest = OneTimeWorkRequestBuilder<TestCoroutineWorker>()
                    .build()

                WorkManager.getInstance(context).enqueue(workRequest)
            }
        }

    }

    /**
     * 데이터 초기화
     */
    private fun initData() {
    }

}
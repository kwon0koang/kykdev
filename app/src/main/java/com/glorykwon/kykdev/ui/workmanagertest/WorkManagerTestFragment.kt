package com.glorykwon.kykdev.ui.workmanagertest

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.OutOfQuotaPolicy
import androidx.work.WorkManager
import androidx.work.WorkRequest
import com.glorykwon.kykdev.common.TestCoroutineWorker
import com.glorykwon.kykdev.common.TestWorker
import com.glorykwon.kykdev.databinding.WorkmanagerTestFragmentBinding
import com.glorykwon.kykdev.ui.BaseFragment

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
            //create WorkRequest
            val workRequest: WorkRequest = OneTimeWorkRequestBuilder<TestWorker>().build()

            //enqueue WorkRequest
            activity?.applicationContext?.let { context ->
                WorkManager.getInstance(context).enqueue(workRequest)
            }
        }

        mBinding.btnCoroutineWorkerTest.setOnClickListener {
            //create WorkRequest
            val workRequest: WorkRequest = OneTimeWorkRequestBuilder<TestCoroutineWorker>()
                .setExpedited(OutOfQuotaPolicy.RUN_AS_NON_EXPEDITED_WORK_REQUEST)       //신속 처리 작업. 할당량이 허용되면 신속 처리 작업이 즉시 백그라운드에서 실행
                .build()

            //enqueue WorkRequest
            activity?.applicationContext?.let { context ->
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
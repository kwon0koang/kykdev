package com.glorykwon.kykdev.ui.roomtest

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.glorykwon.kykdev.common.NetworkResult
import com.glorykwon.kykdev.common.analytics.AnalyticsEventConst
import com.glorykwon.kykdev.common.analytics.AnalyticsManager
import com.glorykwon.kykdev.common.database.room.entity.TodoRoomEntity
import com.glorykwon.kykdev.common.dialog.CommonDialogFragment
import com.glorykwon.kykdev.databinding.RoomTestFragmentBinding
import com.glorykwon.kykdev.ui.BaseFragment

class RoomTestFragment : BaseFragment() {

    private val mBinding by lazy { RoomTestFragmentBinding.inflate(layoutInflater) }
    private val mViewModel by viewModels<RoomTestViewModel>()

    private lateinit var mAdapter: RoomTestListAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {

        initView()      //뷰 초기화
        initObserver()  //옵저버 초기화

        return mBinding.root
    }

    override fun onResume() {
        super.onResume()
        initData()      //데이터 초기화
        AnalyticsManager.logEvent(AnalyticsEventConst.enterRoomTest)
    }

    /**
     * 뷰 초기화
     */
    private fun initView() {

        //어댑터 셋팅
        context?.let { context ->
            mAdapter = RoomTestListAdapter(context, mViewModel) { dto ->
                dto.id?.let { id ->
                    CommonDialogFragment(
                        title = "알림",
                        content = "$id 삭제?",
                        confirmText = "확인",
                        confirmListener = { result ->
                            if(result)
                                mViewModel.deleteItem(id)
                        }
                    ).show(childFragmentManager, null)
                }
            }
        }
        mBinding.rvTodo.adapter = mAdapter

        mBinding.btnInsert.setOnClickListener {
            val id = mBinding.etTest.text.toString()
            val content = "id:$id"
            mViewModel.insertItem(TodoRoomEntity(id, content))

            mBinding.etTest.setText("")
        }

    }

    /**
     * 옵저버 초기화
     */
    private fun initObserver() {

        mViewModel.fetchAllItems.observe(viewLifecycleOwner) {
            when(it) {
                is NetworkResult.Loading -> {}
                is NetworkResult.Success -> {
                    val list = it.data as List<TodoRoomEntity>?
                    list?.let {
                        mAdapter.submitList(it)
                    }
                }
                is NetworkResult.Error -> {}
            }
        }

        mViewModel.insertItem.observe(viewLifecycleOwner) {
            when(it) {
                is NetworkResult.Loading -> {}
                is NetworkResult.Success -> {
                    mViewModel.fetchAllItems()
                }
                is NetworkResult.Error -> {}
            }
        }

        mViewModel.deleteItem.observe(viewLifecycleOwner) {
            when(it) {
                is NetworkResult.Loading -> {}
                is NetworkResult.Success -> {
                    mViewModel.fetchAllItems()
                }
                is NetworkResult.Error -> {}
            }
        }

    }

    /**
     * 데이터 초기화
     */
    private fun initData() {
        mViewModel.fetchAllItems()
    }

}
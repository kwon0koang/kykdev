package com.glorykwon.kykdev.ui.roomtest

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.glorykwon.kykdev.common.NetworkResult
import com.glorykwon.kykdev.database.realm.dao.TodoRealmObject
import com.glorykwon.kykdev.database.room.entity.TodoRoomEntity
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
    }

    /**
     * 뷰 초기화
     */
    private fun initView() {

        //어댑터 셋팅
        context?.let { context ->
            mAdapter = RoomTestListAdapter(context, mViewModel) { dto ->
                dto.id?.let { id -> mViewModel.deleteItem(id) }
            }
        }
        mBinding.rvTodo.adapter = mAdapter

        mBinding.btnInsert.setOnClickListener {
            val id = mBinding.etTest.text.toString()
            val content = "id:$id"
            mViewModel.insertItem(TodoRoomEntity(id, content))
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
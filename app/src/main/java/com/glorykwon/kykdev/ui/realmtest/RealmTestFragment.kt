package com.glorykwon.kykdev.ui.realmtest

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import com.glorykwon.kykdev.database.realm.RealmDbHelper
import com.glorykwon.kykdev.database.realm.dao.TodoRealmObject
import com.glorykwon.kykdev.databinding.RealmTestFragmentBinding
import com.glorykwon.kykdev.ui.BaseFragment

class RealmTestFragment : BaseFragment() {

    private val mBinding by lazy { RealmTestFragmentBinding.inflate(layoutInflater) }
    private val mViewModel by viewModels<RealmTestViewModel>()

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

        mBinding.btnFetch.setOnClickListener {
            var allTodo = ""
            RealmDbHelper.getAllTodo().forEach {
                allTodo += "${it.id} / ${it.content}\n"
            }
            Toast.makeText(context, allTodo, Toast.LENGTH_SHORT).show()
        }

        mBinding.btnInsert.setOnClickListener {
            val id = mBinding.etTest.text.toString()
            val content = "$id content"
            RealmDbHelper.insert(TodoRealmObject(id, content))
        }

        mBinding.btnDelete.setOnClickListener {
            val id = mBinding.etTest.text.toString()
            RealmDbHelper.deleteTodo(id)
        }

    }

    /**
     * 데이터 초기화
     */
    private fun initData() {
    }

}
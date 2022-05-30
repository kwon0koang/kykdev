package com.glorykwon.kykdev.common.dialog

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.glorykwon.kykdev.databinding.CommonDialogFragmentBinding

class CommonDialogFragment(
    val title: String? = null,
    val content: String? = null,
    val confirmText: String? = "확인",
    val confirmListener: (Boolean) -> Any
) : DialogFragment() {

    private val mBinding by lazy { CommonDialogFragmentBinding.inflate(layoutInflater) }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {

        initView()      //뷰 초기화

        return mBinding.root
    }

    override fun onResume() {
        super.onResume()
    }

    /**
     * 뷰 초기화
     */
    private fun initView() {
        title?.let {
            mBinding.txtTitle.text = it
        }

        content?.let {
            mBinding.txtContent.text = it
        }

        confirmText?.let {
            mBinding.btnConfirm.text = it
        }

        mBinding.btnConfirm.setOnClickListener {
            confirmListener?.invoke(true)
            dismiss()
        }
    }

}